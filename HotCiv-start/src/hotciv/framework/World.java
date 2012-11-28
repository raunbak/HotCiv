package hotciv.framework;

/**
 * Represents the world.
 * Holds the Tiles, Citys, and Units.
 */
public interface World {
    public <T extends Tile> T getTileAt(Position p);
    public <C extends City> C getCityAt(Position p);
    public <U extends Unit> U getUnitAt(Position p);

    public Iterable<Position> getCityPositions();
    public Iterable<Position> getUnitPositions();

    public void setTileAt(Position p, Tile t);
    public void setCityAt(Position p, City c);
    public void setUnitAt(Position p, Unit u);

    public void removeCityAt(Position p);
    public void removeUnitAt(Position p);
}
