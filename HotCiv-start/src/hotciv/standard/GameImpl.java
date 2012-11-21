package hotciv.standard;

import hotciv.age.AgeStrategy;
import hotciv.framework.*;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.WorldStrategy;

import java.util.Arrays;

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
    // 2-dimensional arrays for storing the tiles/cities/units:
    private Tile[][] tileTable;
    private City[][] cityTable;
    private Unit[][] unitTable;
    private int age = -4000;   // Game starts in year 4000 BC
    private Player winner = null;
    private AgeStrategy ageStrategy;
    private WinnerStrategy winnerStrategy;
    private WorldStrategy worldStrategy;
    private UnitActionStrategy unitActionStrategy;

    public GameImpl(AgeStrategy ageStrategy,
                    WinnerStrategy winnerStrategy,
                    WorldStrategy worldStrategy,
                    UnitActionStrategy unitActionStrategy) {

        this.ageStrategy = ageStrategy;
        this.winnerStrategy = winnerStrategy;
        this.worldStrategy = worldStrategy;
        this.unitActionStrategy = unitActionStrategy;
        tileTable = this.worldStrategy.getTileArray();
        cityTable = this.worldStrategy.getCityArray();
        unitTable = this.worldStrategy.getUnitArray();

    }

    public Tile getTileAt(Position p) {
        return tileTable[p.getRow()][p.getColumn()];
    }

    public Unit getUnitAt(Position p) {
        return unitTable[p.getRow()][p.getColumn()];
    }

    public City getCityAt(Position p) {
        return cityTable[p.getRow()][p.getColumn()];
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
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

        if (!playerInTurn.equals(unitFrom.getOwner())) {
            return false;
        }

        int distanceToBeMoved = from.distanceTo(to);
        if (distanceToBeMoved > unitFrom.getMoveCount()) {
            return false;
        }


        if (tileTo.getTypeString().equals(GameConstants.MOUNTAINS)) {
            return false;
        }

        if (unitTo != null) {
            if (unitFrom.getOwner().equals(unitTo.getOwner())) {
                return false;
            }
            // Because the attack-strategy used, the attacking unit always wins. So no need to check if a unit of different owner is at position "to".
        }
        // Now, move the unit:
        unitTable[to.getRow()][to.getColumn()] = unitFrom;
        unitTable[from.getRow()][from.getColumn()] = null;
        unitFrom.reduceMoveCountBy(distanceToBeMoved);

        if (getCityAt(to) != null && getCityAt(to).getOwner() != getPlayerInTurn()) {
            getCityAt(to).setOwner(getPlayerInTurn());
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

            // Loop through all the positions in the world.
            for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                    // Restore the move count of all the units.
                    if (unitTable[i][j] != null) {
                        unitTable[i][j].restoreMoveCount();
                    }

                    // For each city, add 6 to the current amount of production, and produce as many units as it can afford.
                    if (cityTable[i][j] != null) {
                        cityTable[i][j].increaseAmountOfProduction(6); // Constant amount of 6 in AlphaCiv.

                        Position p = new Position(i, j);
                        // produce units!
                        produceUnitsInCityAt(p);

                    }
                }
            }
        }

        // the winner (if there is one) is determined each turn, not only each round.
        winner = winnerStrategy.winner(this);
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
            int unitcost = GameConstants.COSTMAP.get(unittype);   // TODO Do something with the NullPointerException thrown exception if unittype is not found.

            // integer division in Java gets rounded down, which is correct here.
            int nUnitsAffordable = city.getCurrentAmountOfProduction() / unitcost;

            // The number of steps needed to take in current direction before making a turn.
            int stepLimit = 1;

            // Begin at the same position as the city itself
            Position p = pCity.clone();

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
                        Unit u = null;
                        // Initialize the unit to be created depending on the unittype. (hardcoded)
                        if (unittype.equals(GameConstants.ARCHER)) {
                            u = new Archer(getPlayerInTurn());
                        } else if (unittype.equals(GameConstants.LEGION)) {
                            u = new Legion(getPlayerInTurn());
                        } else if (unittype.equals(GameConstants.SETTLER)) {
                            u = new Settler(getPlayerInTurn());
                        }
                        // For now, we are relying on the earlier call to GameConstants.COSTMAP
                        //      to cast a NullPointerException if the unittype is invalid.
                        //      Thus, u will never remain null after the if-statements above.
                        unitTable[p.getRow()][p.getColumn()] = u;
                    }

                    // Add the stepping "vector" to the current position to get the next position.
                    p = stepDirection.addToPosition(p);

                    // Increment stepsTakenSinceLastTurn so we know when we should turn the next corner.
                    stepsTakenSinceLastTurn++;
                }

                // Make a turn 90 degrees clockwise.
                stepDirection.rotate90clockwise();

                // Only increment the step-limit when making a turn at either a North-East or South-West corner.
                // An equivalent condition is: (stepDirection.getRowCoordinate() != 0).
                if (stepDirection.getColumnCoordinate() == 0) {
                    stepLimit++;
                }
            }

            // city needs to pay for the produced units.
            city.reduceAmountOfProduction(nUnitsProduced * unitcost);
        }
    }


    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {

        City c = getCityAt(p);

        c.setProduction(unitType);
    }

    public void performUnitActionAt(Position p) {
        Unit u = getUnitAt(p);

        Object obj = unitActionStrategy.performUnitAction(u, this);
        if (obj != null) {
            int i = p.getRow();
            int j = p.getColumn();
            if (obj.getClass().equals(CityImpl.class) && cityTable[i][j] == null) {
                cityTable[i][j] = (City) obj;
                unitTable[i][j] = null;
            }
        }


    }

    @Override
    public City[] getAllCities() {
        City[] citylist = new City[GameConstants.WORLDSIZE * GameConstants.WORLDSIZE];
        int k = 0;
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {

                if (cityTable[i][j] != null) {
                    citylist[k] = cityTable[i][j];
                    k++;
                }

            }
        }

        return Arrays.copyOf(citylist, k);
    }
}