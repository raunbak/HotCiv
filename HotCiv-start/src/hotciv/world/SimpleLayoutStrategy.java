package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.*;

import java.util.HashMap;

/**
 *
 */
public class SimpleLayoutStrategy implements WorldStrategy {
    private HashMap<Position, Tile> tileMap = new HashMap<Position, Tile>();
    private HashMap<Position, City> cityMap = new HashMap<Position, City>();
    private HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();
    private MutatorKey mutatorKey;

    public SimpleLayoutStrategy() {
        mutatorKey = new MutatorKey();
        // Initialize the tile array with plains on every tile, with the responding positions.
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                Position p = new Position(i, j);
                tileMap.put(p, new TileImpl(p, GameConstants.PLAINS));
            }
        }
        Position p = new Position(1, 0);
        tileMap.put(p, new TileImpl(p, GameConstants.OCEANS));
        p = new Position(0, 1);
        tileMap.put(p, new TileImpl(p, GameConstants.HILLS));
        p = new Position(2, 2);
        tileMap.put(p, new TileImpl(p, GameConstants.MOUNTAINS));
        p = new Position(1, 1);
        cityMap.put(p, new CityImpl(Player.RED, mutatorKey));
        p = new Position(4, 1);
        cityMap.put(p, new CityImpl(Player.BLUE, mutatorKey));
        p = new Position(2, 0);
        unitMap.put(p, new Archer(Player.RED, mutatorKey));
        p = new Position(4, 3);
        unitMap.put(p, new Settler(Player.RED, mutatorKey));
        p = new Position(3, 2);
        unitMap.put(p, new Legion(Player.BLUE, mutatorKey));
    }

    @Override
    public HashMap<Position, Tile> getTileMap() {
        return tileMap;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public HashMap<Position, Unit> getUnitMap() {
        return unitMap;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public HashMap<Position, City> getCityMap() {
        return cityMap;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MutatorKey getMutatorKey() {
        return mutatorKey;
    }
}
