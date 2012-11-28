package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.*;

/**
 *
 */
public class SimpleLayoutStrategy implements WorldStrategy {
    @Override
    public void setupInitialWorld(World world) {
        // Initialize the tile array with plains on every tile, with the responding positions.
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                Position p = new Position(i, j);
                world.setTileAt(p, new TileImpl(p, GameConstants.PLAINS));
            }
        }
        Position p = new Position(1, 0);
        world.setTileAt(p, new TileImpl(p, GameConstants.OCEANS));
        p = new Position(0, 1);
        world.setTileAt(p, new TileImpl(p, GameConstants.HILLS));
        p = new Position(2, 2);
        world.setTileAt(p, new TileImpl(p, GameConstants.MOUNTAINS));
        p = new Position(1, 1);
        world.setCityAt(p, new CityImpl(Player.RED));
        p = new Position(4, 1);
        world.setCityAt(p, new CityImpl(Player.BLUE));
        p = new Position(2, 0);
        world.setUnitAt(p, new UnitImpl(Player.RED, GameConstants.ARCHER));
        p = new Position(4, 3);
        world.setUnitAt(p, new UnitImpl(Player.RED, GameConstants.SETTLER));
        p = new Position(3, 2);
        world.setUnitAt(p, new UnitImpl(Player.BLUE, GameConstants.LEGION));
    }
}
