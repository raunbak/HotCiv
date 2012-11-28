package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * A class representing the world.
 * Holds the Tile, City, and Unit HashMaps.
 * Game holds the world-instance, and when needed passes a pointer
 * to other objects which should be able to make modifications.
 */
@SuppressWarnings("unchecked")
public class WorldImpl implements World {
    private HashMap<Position, Tile> tileMap;
    private HashMap<Position, ModifiableCity> cityMap;
    private HashMap<Position, ModifiableUnit> unitMap;

    public WorldImpl() {
        tileMap = new HashMap<Position, Tile>();
        cityMap = new HashMap<Position, ModifiableCity>();
        unitMap = new HashMap<Position, ModifiableUnit>();
    }


    public Tile getTileAt(Position p) {
        return tileMap.get(p);
    }

    public ModifiableUnit getUnitAt(Position p) {
        return unitMap.get(p);
    }

    public ModifiableCity getCityAt(Position p) {
        return cityMap.get(p);
    }

    @Override
    public Iterable<Position> getCityPositions() {
        return cityMap.keySet();
    }

    @Override
    public Iterable<Position> getUnitPositions() {
        return unitMap.keySet();
    }

    @Override
    public void setTileAt(Position p, Tile t) {
        tileMap.put(p, (TileImpl) t);
    }

    @Override
    public void setCityAt(Position p, City c) {
        cityMap.put(p, (CityImpl) c);
    }

    @Override
    public void setUnitAt(Position p, Unit u) {
        unitMap.put(p, (UnitImpl) u);
    }

    @Override
    public void removeCityAt(Position p) {
        cityMap.remove(p);
    }

    @Override
    public void removeUnitAt(Position p) {
        unitMap.remove(p);
    }
}
