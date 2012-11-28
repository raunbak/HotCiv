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
    private HashMap<Position, TileImpl> tileMap;
    private HashMap<Position, CityImpl> cityMap;
    private HashMap<Position, UnitImpl> unitMap;

    public WorldImpl() {
        tileMap = new HashMap<Position, TileImpl>();
        cityMap = new HashMap<Position, CityImpl>();
        unitMap = new HashMap<Position, UnitImpl>();
    }


    public TileImpl getTileAt(Position p) {
        return tileMap.get(p);
    }

    //@SuppressWarnings("unchecked")
    public UnitImpl getUnitAt(Position p) {
        return unitMap.get(p);
    }

    //@SuppressWarnings("unchecked")
    public CityImpl getCityAt(Position p) {
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
