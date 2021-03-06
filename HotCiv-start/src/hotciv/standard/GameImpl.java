package hotciv.standard;

import hotciv.GameFactory.AbstractGameFactory;
import hotciv.age.AgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.control.ControlStrategy;
import hotciv.framework.*;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.LayoutStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of Game. Based on the skeleton implementation from Henrik B Christensen.
 * Additional changes made by Mads Raunbak and Laurids M. Jepsen.
 */

public class GameImpl implements ExtendedGame {
    /*
     * Fields for GameImpl.
     */
    private Player playerInTurn = Player.RED;
    private int age = -4000;   // Game starts in year 4000 BC
    private Player winner = null;
    private AgeStrategy ageStrategy;
    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private AttackStrategy attackStrategy;
    private WorkForceStrategy workForceStrategy;
    private PopulationStrategy populationStrategy;
    private ControlStrategy controlStrategy;
    private World world = new WorldImpl();  // holds all tiles, cities, units.
    private int roundsPlayed;

    private List<AttacksWonSubscriber> subscribers;
    private GameObsComposite observers;


    public GameImpl(AbstractGameFactory gameFactory) {
        subscribers = new ArrayList<AttacksWonSubscriber>();
        observers = new GameObsComposite();

        // Create all needed Strategies
        ageStrategy = gameFactory.createAgeStrategy();
        winnerStrategy = gameFactory.createWinnerStrategy(this);
        LayoutStrategy layoutStrategy = gameFactory.createLayoutStrategy();
        unitActionStrategy = gameFactory.createUnitActionStrategy();
        attackStrategy = gameFactory.createAttackStrategy(new Die(6));
        workForceStrategy = gameFactory.createWorkForceStrategy();
        populationStrategy = gameFactory.createPopulationStrategy();
        controlStrategy = gameFactory.createControlStrategy();

        // Make the world strategy setup the world containing the initial layout of Tiles, Cities and Units.
        layoutStrategy.setupInitialWorld(world);

        // Number of rounds played.
        roundsPlayed = 0;
    }

    public Tile getTileAt(Position p) {
        return world.getTileAt(p);
    }

    public Unit getUnitAt(Position p) {
        return world.getUnitAt(p);
    }

    public City getCityAt(Position p) {
        return world.getCityAt(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        // If there isn't already a winner, determine who the winner is.
        // This is not strictly an invariant accessor, but otherwise this code
        // would have to be in both endOfTurn(), moveUnit(), and performUnitActionAt() to be sure.
        // And it makes sense that this will be called at least once per round.
        if (winner == null) {
            winner = winnerStrategy.winner(this);
        }
        return winner;
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        ModifiableUnit unitFrom = world.getUnitAt(from);
        if (unitFrom == null) {
            return false;
        }
        Tile tileTo = getTileAt(to);
        int distanceToBeMoved = from.distanceTo(to);
        /* Cannot move a unit:
         * - owned by another player
         * - too long a distance
         * - to a mountain- or ocean-tile
         */
        if (!unitFrom.isMovable()
                || !playerInTurn.equals(unitFrom.getOwner())
                || distanceToBeMoved > unitFrom.getMoveCount()
                || tileTo.getTypeString().equals(GameConstants.MOUNTAINS)
                || tileTo.getTypeString().equals(GameConstants.OCEANS)) {
            return false;
        }

        Unit unitTo = world.getUnitAt(to);
        // initially, set the winning unit to the unit that is moved.
        Unit winningUnit = unitFrom;
        if (unitTo != null) {
            // Don't move the unit to a position occupied by a unit of the same owner.
            if (unitFrom.getOwner().equals(unitTo.getOwner())) {
                return false;
            }

            winningUnit = attackStrategy.outcomeOfBattle(world, from, to);

            if (winningUnit.getOwner() == playerInTurn) {
                for (AttacksWonSubscriber sub : subscribers) {
                    sub.aWonAttack(playerInTurn);
                }
            }
            else {
                world.removeUnitAt(from);
            }
        }
        // Now, move the unit (if it has not been removed because a defending unit won a battle).
        world.forceMoveUnit(from, to);
        unitFrom.reduceMoveCountBy(distanceToBeMoved);
        unitFrom.setPosition(to);

        // If there is a city at "to", make sure the owner is now the owner of the winning unit.
        ModifiableCity cityTo = world.getCityAt(to);
        if (cityTo != null) {
            cityTo.setOwner(winningUnit.getOwner());
        }

        // The world has not necessarily changed at both from and to, but notifying the observers
        // here once and for all is the simplest.
        observers.worldChangedAt(from);
        observers.worldChangedAt(to);

        return true;
    }

    public void endOfTurn() {
        // If red is the player in turn, change player in turn to blue and nothing else.
        if (playerInTurn.equals(Player.RED)) {
            playerInTurn = Player.BLUE;
            observers.turnEnds(playerInTurn, age);
            controlStrategy.playRound(this);
            return;
        }

        // If blue is in turn, change player in turn to red and do end-of-round processing.
        playerInTurn = Player.RED;

        // Calculate the age of the game in the next round.
        age = ageStrategy.calculateAge(age);

        // Restore the move count of all the units.
        for (Position p : world.getUnitPositions()) {
            world.getUnitAt(p).restoreMoveCount();
        }

        // For each city, add 6 to the current amount of production, and produce as many units as it can afford.
        for (Position p : world.getCityPositions()) {
            ModifiableCity city = world.getCityAt(p);

            workForceStrategy.gatherFoodAndProduction(world, p);

            populationStrategy.populationGrowth(world, p);

            // produce units!
            city.produceUnits(world, p, observers);
        }
        // Add to the counter of rounds played in the game.
        roundsPlayed++;

        observers.turnEnds(playerInTurn, age);

        controlStrategy.playRound(this);

    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        ModifiableCity c = world.getCityAt(p);
        if (c.getOwner().equals(playerInTurn)) {
            c.setWorkForceFocus(balance);
        }
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        ModifiableCity c = world.getCityAt(p);
        if (c.getOwner().equals(playerInTurn)) {
            c.setProduction(unitType);
        }
    }

    public void performUnitActionAt(Position p) {
        Unit u = getUnitAt(p);
        if (u.getOwner().equals(playerInTurn)) {
            unitActionStrategy.performUnitAction(world, p);
            observers.worldChangedAt(p);
        }
    }

    @Override
    public void addObserver(GameObserver observer) {
        observers.addObserver(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        observers.tileFocusChangedAt(position);
    }

    public int getRoundsPlayed(){
        return roundsPlayed;
    }

    public Iterable<Position> getCityPositions() {
        return world.getCityPositions();
    }

    @Override
    public Iterable<Position> getUnitPositions() {
        return world.getUnitPositions();
    }

    public void addAttacksWonSubscriber(AttacksWonSubscriber sub) {
        if (!subscribers.contains(sub)) {
            subscribers.add(sub);
        }
    }

}