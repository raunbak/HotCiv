package hotciv.framework;

/**
 * Represents the world.
 * Holds the Tiles, Citys, and Units.
 * Can create these as well as move and remove units.
 * Should only be passed to classes that should be able to do direct modifications in the world.
 */
public interface World {
    public Tile getTileAt(Position p);
    public ModifiableCity getCityAt(Position p);
    public ModifiableUnit getUnitAt(Position p);

    public Iterable<Position> getCityPositions();
    public Iterable<Position> getUnitPositions();

    public void createTileAt(Position p, String type);
    public void createCityAt(Position p, Player owner);
    public void createUnitAt(Position p, Player owner, String type);

    public void removeUnitAt(Position p);

    /**
     * Move a unit from one position to another.
     * If there is a unit in position "from", then a unit in position "to" will be overridden.
     * Should not used instead of moveUnit() in Game.
     * Precondition: Position "from" and "to" are both valid world-positions.
     * @param from The position of the unit to be moved.
     * @param to The position where the unit is moved to.
     */
    public void forceMoveUnit(Position from, Position to);
}
