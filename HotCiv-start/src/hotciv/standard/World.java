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
    public HashMap<Position, Tile> tileMap;
    public HashMap<Position, City> cityMap;
    public HashMap<Position, Unit> unitMap;
    private MutatorKey mutatorKey;

    public World(HashMap<Position, Tile> tileMap,
                 HashMap<Position, City> cityMap,
                 HashMap<Position, Unit> unitMap,
                 MutatorKey mutatorKey) {
        this.tileMap = tileMap;
        this.cityMap = cityMap;
        this.unitMap = unitMap;
        this.mutatorKey = mutatorKey;
    }

    public MutatorKey getMutatorKey() {
        return mutatorKey;
    }
}
