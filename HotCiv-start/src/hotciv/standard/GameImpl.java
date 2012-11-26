package hotciv.standard;

import hotciv.GameFactory.AbstractGameFactory;
import hotciv.age.AgeStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.framework.*;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.WorldStrategy;

import java.util.HashMap;
import java.util.Set;

/**
 * An implementation of Game. Based on the skeleton implementation from Henrik B Christensen.
 * Additional changes made by Mads Raunbak and Laurids M. Jepsen.
 */

public class GameImpl implements Game {
    /*
     * Fields for GameImpl.
     */
    private Player playerInTurn = Player.RED;
    private int age = -4000;   // Game starts in year 4000 BC
    private Player winner = null;
    private AgeStrategy ageStrategy;
    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private HashMap<Player, Integer> attacksWon;
    private AttackStrategy attackStrategy;
    private World world;  // holds all tiles, cities, units.
    private int roundsPlayed;

    public GameImpl(AbstractGameFactory gameFactory) {

        // Create all needed Strategies
        ageStrategy = gameFactory.createAgeStrategy();
        winnerStrategy = gameFactory.createWinnerStrategy();
        WorldStrategy worldStrategy = gameFactory.createWorldStrategy();
        unitActionStrategy = gameFactory.createUnitActionStrategy();
        attackStrategy = gameFactory.createAttackStrategy();

        // Get a world-instance from the world strategy containing the initial layout of Tiles, Cities and Units.
        world = worldStrategy.getWorld();

        // No player has won any attacks at the start of the game.
        attacksWon = new HashMap<Player, Integer>();
        attacksWon.put(Player.RED, 0);
        attacksWon.put(Player.BLUE, 0);

        // Number of rounds played.
        roundsPlayed = 0;

    }

    public Tile getTileAt(Position p) {
        return world.tileMap().get(p);
    }

    public Unit getUnitAt(Position p) {
        return world.unitMap().get(p);
    }

    public City getCityAt(Position p) {
        return world.cityMap().get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        // If there isn't already a winner, determine who the winner is.
        // We know that this is not strictly an invariant accessor,
        // but otherwise this code would have to be in both endOfTurn(), moveUnit(),
        // and performUnitActionAt() to be sure.
        if (winner == null) {
            winner = winnerStrategy.winner(this);
        }
        return winner;
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        Unit unitFrom = getUnitAt(from);
        Unit unitTo = getUnitAt(to);
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

        Unit unitToBeMoved;
        if (unitTo != null) {
            // Don't move the unit to a position occupied by a unit of the same owner.
            if (unitFrom.getOwner().equals(unitTo.getOwner())) {
                return false;
            }

            Unit winningUnit = attackStrategy.outcomeOfBattle(this, from, to);

            if (winningUnit.getOwner() == playerInTurn) {
                attacksWon.put(playerInTurn, attacksWon.get(playerInTurn) + 1);
            }

            unitToBeMoved = winningUnit;
        } else {
            unitToBeMoved = unitFrom;
        }
        // Now, move the unit:
        world.unitMap().put(to, (UnitImpl) unitToBeMoved);
        world.unitMap().remove(from);
        ((UnitImpl) unitFrom).reduceMoveCountBy(distanceToBeMoved);

        CityImpl cityTo = (CityImpl) getCityAt(to);
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
        for (Position p : world.unitMap().keySet()) {
            world.unitMap().get(p).restoreMoveCount();
        }

        // For each city, add 6 to the current amount of production, and produce as many units as it can afford.
        for (Position p : world.cityMap().keySet()) {

            world.cityMap().get(p).increaseAmountOfProduction(6);  // Constant amount of 6 in AlphaCiv.

            // produce units!
            produceUnitsInCityAt(p);
        }
        // Add to the counter of rounds played in the game.
        roundsPlayed++;
    }

    /**
     * A method for producing as many units around a city as it can afford, provided there are enough free tiles.
     * The order in which the free tiles are found has the shape of a clockwise spiralling square,
     * starting from the position of the city and then the position just above it.
     * The city will also have the production cost of the units produced subtracted from its production amount.
     * Precondition: there is a city at position pCity.
     * Added by L&M.
     *
     * @param pCity The position of the city that should produce units.
     */
    private void produceUnitsInCityAt(Position pCity) {
        int nUnitsProduced = 0;  // the number of units produced by this method-call.
        CityImpl city = (CityImpl) getCityAt(pCity);
        String unittype = city.getProduction();

        // If the city has not currently a production set, then there is nothing to produce.
        if (unittype == null) {
            return;
        }

        // Find the cost of the relevant unittype.
        int unitcost;
        try {
            unitcost = GameConstants.COSTMAP.get(unittype);
        } catch (NullPointerException npEx) {
            throw new InvalidUnittypeException(unittype);
        }

        // integer division in Java gets rounded down, which is correct here.
        int nUnitsAffordable = city.getCurrentAmountOfProduction() / unitcost;

        // The number of steps needed to take in current direction before making a turn.
        int stepLimit = 1;

        // Begin at the same position as the city itself
        Position p = pCity;

        // this "Position" represents the direction-vector, eg. (i,j)+(-1,0) --> (i-1,j).
        // the starting direction is North, since the worldmap has origo in the top-left corner
        //      and the row-axis points downwards.
        Vector stepDirection = new Vector(-1, 0);

        // Keep stepping around to find the next free position as long as we are
        //      not yet stepping around in a square bigger than the world map,
        //      and the city can still afford to produce more units.
        while (stepLimit < GameConstants.WORLDSIZE && nUnitsProduced < nUnitsAffordable) {
            int stepsTakenSinceLastTurn = 0;

            // Keep stepping forward in stepDirection until we need to turn a corner.
            while (stepsTakenSinceLastTurn < stepLimit && nUnitsProduced < nUnitsAffordable) {
                // If p is not a position inside the worldmap,
                //      or if there is already a unit of the tile at position p,
                //      of if the tile is a mountain or an ocean,
                //      then skip this position and thus don't create the new unit here.
                if (p.isValidWorldPosition()
                        && getUnitAt(p) == null
                        && !getTileAt(p).getTypeString().equals(GameConstants.MOUNTAINS)
                        && !getTileAt(p).getTypeString().equals(GameConstants.OCEANS)) {

                    UnitImpl u = new UnitImpl(playerInTurn, unittype);
                    world.unitMap().put(p, u);
                    nUnitsProduced++;  // now one more unit has been created.
                }

                // Add the stepping "vector" to the current position to get the next position.
                p = stepDirection.addToPosition(p);

                // Increment stepsTakenSinceLastTurn so we know when we should turn the next corner.
                stepsTakenSinceLastTurn++;
            }

            // Make a turn 90 degrees clockwise.
            stepDirection.rotate90clockwise();

            // Only increment the step-limit when making a turn at either a North-East or South-West corner.
            // An equivalent condition is: (stepDirection.getRowComponent() != 0).
            if (stepDirection.getColumnComponent() == 0) {
                stepLimit++;
            }
        }

        // city needs to pay for the produced units.
        city.reduceAmountOfProduction(nUnitsProduced * unitcost);
    }


    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl c = (CityImpl) getCityAt(p);
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

    /**
     * Get the number of rounds played so far.
     * Added by L&M.
     * @return Number of rounds played
     */
    public int getRoundsPlayed(){
        return roundsPlayed;
    }

    /**
     * Returns a set containing all the positions at which there are cities.
     * Added by L&M.
     */
    public Set<Position> getCityPositions() {
        return world.cityMap().keySet();
    }

    @SuppressWarnings("unchecked")
    public HashMap<Player, Integer> getAttacksWonMap() {
        return (HashMap<Player, Integer>) attacksWon.clone();
    }
}