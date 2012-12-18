package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * A class representing the world.
 * Holds the Tile, City, and Unit HashMaps.
 * Game holds the world-instance, and when needed passes a pointer
 * to other objects which should be able to make modifications.
 */

public class WorldImpl implements World {
    private HashMap<Position, TileImpl> tileMap;
    private HashMap<Position, CityImpl> cityMap;
    private HashMap<Position, UnitImpl> unitMap;

    public WorldImpl() {
        tileMap = new HashMap<Position, TileImpl>();
        cityMap = new HashMap<Position, CityImpl>();
        unitMap = new HashMap<Position, UnitImpl>();
    }

    @Override
    public Tile getTileAt(Position p) {
        return tileMap.get(p);
    }

    @Override
    public ModifiableUnit getUnitAt(Position p) {
        return unitMap.get(p);
    }

    @Override
    public ModifiableCity getCityAt(Position p) {
        return cityMap.get(p);
    }

    @Override
    public Iterable<Position> getCityPositions() {
        return cityMap.keySet();
    }

    @Override
    public Iterable<Position> getUnitPositions() {
        return ((HashMap<Position,UnitImpl>) unitMap.clone()).keySet();
    }

    @Override
    public void forceMoveUnit(Position from, Position to) {
        if (unitMap.containsKey(from) && !from.equals(to)) {
            unitMap.put(to, unitMap.get(from));
            unitMap.remove(from);
        }
    }

    @Override
    public void createTileAt(Position p, String type) {
        tileMap.put(p, new TileImpl(p, type));
    }

    @Override
    public void createCityAt(Position p, Player owner) {
        cityMap.put(p, new CityImpl(owner));
    }

    @Override
    public void createUnitAt(Position p, Player owner, String type) {
        unitMap.put(p, new UnitImpl(owner, type, p));
    }

    @Override
    public void removeUnitAt(Position p) {
        unitMap.remove(p);
    }
}
