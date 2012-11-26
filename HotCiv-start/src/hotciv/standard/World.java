package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * A class representing the world.
 * Holds the Tile, City, and Unit HashMaps.
 * Game holds the world-instance, and when needed passes a pointer
 * to other objects which should be able to make modifications.
 */
public class World {
    private HashMap<Position, TileImpl> tileMap;
    private HashMap<Position, CityImpl> cityMap;
    private HashMap<Position, UnitImpl> unitMap;

    public World(HashMap<Position, TileImpl> tileMap,
                 HashMap<Position, CityImpl> cityMap,
                 HashMap<Position, UnitImpl> unitMap) {
        this.tileMap = tileMap;
        this.cityMap = cityMap;
        this.unitMap = unitMap;
    }

    public HashMap<Position, TileImpl> tileMap() {
        return tileMap;
    }

    public HashMap<Position, CityImpl> cityMap() {
        return cityMap;
    }

    public HashMap<Position, UnitImpl> unitMap() {
        return unitMap;
    }

}
