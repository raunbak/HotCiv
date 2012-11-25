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
 * Skeleton implementation of HotCiv.
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 * <p/>
 * -------------------------
 * Additional changes made by MR and LM
 */

public class GameImpl implements Game {
    /**
     * Fields for GameImpl.
     */
    private Player playerInTurn = Player.RED;
    private int age = -4000;   // Game starts in year 4000 BC
    private Player winner = null;
    private AgeStrategy ageStrategy;
    private WinnerStrategy winnerStrategy;
    private WorldStrategy worldStrategy;
    private UnitActionStrategy unitActionStrategy;
    private HashMap<Player, Integer> attacksWon;
    private AttackStrategy attackStrategy;
    private World world;  // holds all tiles, cities, units.
    private MutatorKey mutatorKey;
    private int roundsPlayed;

    public GameImpl(AbstractGameFactory gameFactory) {

        // Create all needed Strategies
        ageStrategy = gameFactory.createAgeStrategy();
        winnerStrategy = gameFactory.createWinnerStrategy();
        worldStrategy = gameFactory.createWorldStrategy();
        unitActionStrategy = gameFactory.createUnitActionStrategy();
        attackStrategy = gameFactory.createAttackStrategy();

        mutatorKey = worldStrategy.getMutatorKey();

        // Get HashMaps from the world strategy.
        world = new World(worldStrategy.getTileMap(),
                worldStrategy.getCityMap(),
                worldStrategy.getUnitMap(),
                mutatorKey);

        // No player has won any attackts at the start of the game.
        attacksWon = new HashMap<Player, Integer>();
        attacksWon.put(Player.RED, 0);
        attacksWon.put(Player.BLUE, 0);

        // Number of rounds played.
        roundsPlayed = 0;

    }

    public Tile getTileAt(Position p) {
        return world.tileMap.get(p);
    }

    public Unit getUnitAt(Position p) {
        return world.unitMap.get(p);
    }

    public City getCityAt(Position p) {
        return world.cityMap.get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        // the winner
        if (winner != null) {
            return winner;
        }
        winner = winnerStrategy.winner(this);
        return winner;
    }

    public int getAge() {
        return age;
    }

    /**
     * @param from the position that the unit has now
     * @param to   the position the unit should move to
     * @return true if the unit will move. false if not allowed to move.
     */
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
        world.unitMap.put(to, unitToBeMoved);
        world.unitMap.remove(from);
        unitFrom.reduceMoveCountBy(distanceToBeMoved, mutatorKey);

        if (getCityAt(to) != null
                && getCityAt(to).getOwner() != getPlayerInTurn()) {
            getCityAt(to).setOwner(getPlayerInTurn(), mutatorKey);
        }

        return true;
    }

    /**
     *
     */
    public void endOfTurn() {
        if (playerInTurn.equals(Player.RED)) {
            playerInTurn = Player.BLUE;
        } else {

            playerInTurn = Player.RED;
            age = ageStrategy.calculateAge(age);

            // Restore the move count of all the units.
            for (Position p : world.unitMap.keySet()) {
                world.unitMap.get(p).restoreMoveCount(mutatorKey);
            }

            // For each city, add 6 to the current amount of production, and produce as many units as it can afford.
            for (Position p : world.cityMap.keySet()) {

                world.cityMap.get(p).increaseAmountOfProduction(6, mutatorKey);  // Constant amount of 6 in AlphaCiv.

                // produce units!
                produceUnitsInCityAt(p);
            }
            // Add to the counter of rounds played in the game.
            roundsPlayed++;
        }
    }

    /**
     * A method for producing as many units around a city as it can afford, provided there are enough free tiles.
     * The order in which the free tiles are found has the shape of a clockwise spiralling square,
     * starting from the position of the city and then the position just above it.
     * The city will also have the production cost of the units produced subtracted from its production amount.
     * Precondition: there is a city at position pCity.
     *
     * @param pCity The position of the city that should produce units.
     */
    private void produceUnitsInCityAt(Position pCity) {
        int nUnitsProduced = 0;  // the number of units produced by this method-call.
        City city = getCityAt(pCity);
        String unittype = city.getProduction();

        if (unittype != null) {
            int unitcost;
            try {
                unitcost = GameConstants.COSTMAP.get(unittype);
            } catch (NullPointerException npEx) {
                throw new InvalidUnittypeException("Unittype not found in GameConstants.COSTMAP");
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

                        nUnitsProduced++;  // now one more unit is being created.
                        Unit u = new UnitImpl(playerInTurn, unittype, mutatorKey);
                        world.unitMap.put(p, u);
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
            city.reduceAmountOfProduction(nUnitsProduced * unitcost, mutatorKey);
        }
    }


    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        City c = getCityAt(p);
        if (c.getOwner().equals(playerInTurn)) {
            c.setProduction(unitType, mutatorKey);
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

    public Set<Position> getCityPositions() {
        return world.cityMap.keySet();
    }

    @SuppressWarnings("unchecked")
    public HashMap<Player, Integer> getAttacksWonMap() {
        return (HashMap<Player, Integer>) attacksWon.clone();
    }
}