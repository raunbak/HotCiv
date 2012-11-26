package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.*;

import java.util.HashMap;

/**
 *
 */
public class SimpleLayoutStrategy implements WorldStrategy {
    private HashMap<Position, TileImpl> tileMap = new HashMap<Position, TileImpl>();
    private HashMap<Position, CityImpl> cityMap = new HashMap<Position, CityImpl>();
    private HashMap<Position, UnitImpl> unitMap = new HashMap<Position, UnitImpl>();

    public SimpleLayoutStrategy() {

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
        cityMap.put(p, new CityImpl(Player.RED));
        p = new Position(4, 1);
        cityMap.put(p, new CityImpl(Player.BLUE));
        p = new Position(2, 0);
        unitMap.put(p, new UnitImpl(Player.RED, GameConstants.ARCHER));
        p = new Position(4, 3);
        unitMap.put(p, new UnitImpl(Player.RED, GameConstants.SETTLER));
        p = new Position(3, 2);
        unitMap.put(p, new UnitImpl(Player.BLUE, GameConstants.LEGION));
    }

    @Override
    public World getWorld() {
        return new World(tileMap, cityMap, unitMap);
    }
}
