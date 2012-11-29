package hotciv.standard;

import hotciv.GameFactory.AbstractGameFactory;
import hotciv.age.AgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.framework.*;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.WorldStrategy;

import java.util.*;

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
    private WorldImpl world = new WorldImpl();  // holds all tiles, cities, units.
    private int roundsPlayed;

    private List<AttacksWonSubscriber> subscribers;


    public GameImpl(AbstractGameFactory gameFactory) {
        subscribers = new ArrayList<AttacksWonSubscriber>();

        // Create all needed Strategies
        ageStrategy = gameFactory.createAgeStrategy();
        winnerStrategy = gameFactory.createWinnerStrategy(this);
        WorldStrategy worldStrategy = gameFactory.createWorldStrategy();
        unitActionStrategy = gameFactory.createUnitActionStrategy();
        attackStrategy = gameFactory.createAttackStrategy();
        workForceStrategy = gameFactory.createWorkForceStrategy();
        populationStrategy = gameFactory.createPopulationStrategy();

        // Make the world strategy setup the world containing the initial layout of Tiles, Cities and Units.
        worldStrategy.setupInitialWorld(world);

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
        ModifiableUnit unitTo = world.getUnitAt(to);
        Tile tileTo = getTileAt(to);

        int distanceToBeMoved = from.distanceTo(to);
        /* Cannot move a unit:
         * - owned by another player
         * - too long a distance
         * - to a mountain- or ocean-tile
         */
        if (!playerInTurn.equals(unitFrom.getOwner())
                || distanceToBeMoved > unitFrom.getMoveCount()
                || tileTo.getTypeString().equals(GameConstants.MOUNTAINS)
                || tileTo.getTypeString().equals(GameConstants.OCEANS)) {
            return false;
        }

        ModifiableUnit unitToBeMoved;
        if (unitTo != null) {
            // Don't move the unit to a position occupied by a unit of the same owner.
            if (unitFrom.getOwner().equals(unitTo.getOwner())) {
                return false;
            }

            ModifiableUnit winningUnit = attackStrategy.outcomeOfBattle(world, from, to);


            if (winningUnit.getOwner() == playerInTurn) {

                for (AttacksWonSubscriber sub : subscribers) {
                    sub.aWonAttack(playerInTurn);
                }
            }

            unitToBeMoved = winningUnit;
        } else {
            unitToBeMoved = unitFrom;
        }
        // Now, move the unit:
        world.setUnitAt(to, unitToBeMoved);
        world.removeUnitAt(from);
        unitFrom.reduceMoveCountBy(distanceToBeMoved);

        ModifiableCity cityTo = world.getCityAt(to);
        if (cityTo != null
                && cityTo.getOwner() != getPlayerInTurn()) {
            cityTo.setOwner(getPlayerInTurn());
        }

        return true;
    }

    public void endOfTurn() {
        // If red is the player in turn, change player in turn to blue and nothing else.
        if (playerInTurn.equals(Player.RED)) {
            playerInTurn = Player.BLUE;
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
            city.produceUnits(world, p);
        }
        // Add to the counter of rounds played in the game.
        roundsPlayed++;
    }


    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        world.getCityAt(p).setWorkForceFocus(balance);
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
        }
    }

    public int getRoundsPlayed(){
        return roundsPlayed;
    }

    public Iterable<Position> getCityPositions() {
        return world.getCityPositions();
    }

    public void addAttacksWonSubscriber(AttacksWonSubscriber sub) {
        if (!subscribers.contains(sub)) {
            subscribers.add(sub);
        }
    }

}