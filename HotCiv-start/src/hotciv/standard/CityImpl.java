package hotciv.standard;

import hotciv.framework.*;

/**
 * An implementation of City
 */
public class CityImpl implements ModifiableCity {
    private Player owner;
    private int production;
    private String unitInProduction;
    private String workForceFocus;
    private int food;
    private int size;

    /**
     * Constructor for CityImpl
     *
     * @param player The owner of this city.
     */
    public CityImpl(Player player) {
        owner = player;
        size = 1;
        workForceFocus = GameConstants.foodFocus;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getProduction() {
        return unitInProduction;
    }

    @Override
    public String getWorkForceFocus() {
        return workForceFocus;
    }

    @Override
    public int getCurrentAmountOfProduction() {
        return production;
    }

    @Override
    public void reduceAmountOfProduction(int amount) {
        production -= amount;
    }

    @Override
    public void increaseAmountOfProduction(int amount) {
        production += amount;
    }

    @Override
    public void setProduction(String unittype) {
        unitInProduction = unittype;
    }

    @Override
    public void setWorkForceFocus(String focus) {
        workForceFocus = focus;
    }

    @Override
    public int getCurrentAmountOfFood() {
        return food;
    }

    @Override
    public void increaseAmountOfFood(int amount) {
        food += amount;
    }

    @Override
    public void reduceAmountOfFood(int amount) {
        food -= amount;
    }

    @Override
    public void addToPopulation(int numberOfPeople) {
        size += numberOfPeople;
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }

    /**
     * The order in which the free tiles are found has the shape of a clockwise spiralling square,
     * starting from the position of the city and then the position just above it.
     */
    @Override
    public void produceUnits(World world, Position pCity) {
        String unittype = getProduction();

        // If the city has not currently a production set, then there is nothing to produce.
        if (unittype == null) {
            return;
        }

        // Find the cost of the relevant unittype.
        int unitcost;
        try {
            unitcost = GameConstants.COSTMAP.get(unittype);
        } catch (NullPointerException npEx) {
            throw new InvalidTypeException(unittype);
        }

        // the number of units produced by this method-call.
        int nUnitsProduced = 0;

        // integer division in Java gets rounded down, which is correct here.
        int nUnitsAffordable = getCurrentAmountOfProduction() / unitcost;

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
                        && world.getUnitAt(p) == null
                        && !world.getTileAt(p).getTypeString().equals(GameConstants.MOUNTAINS)
                        && !world.getTileAt(p).getTypeString().equals(GameConstants.OCEANS)) {

                    UnitImpl u = new UnitImpl(getOwner(), unittype);
                    world.setUnitAt(p, u);
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
        reduceAmountOfProduction(nUnitsProduced * unitcost);
    }

}