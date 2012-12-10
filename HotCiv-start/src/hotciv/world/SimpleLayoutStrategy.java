package hotciv.world;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public class SimpleLayoutStrategy implements LayoutStrategy {
    @Override
    public void setupInitialWorld(World world) {
        // Initialize the tile array with plains on every tile, with the responding positions.
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                Position p = new Position(i, j);
                world.createTileAt(p, GameConstants.PLAINS);
            }
        }
        Position p = new Position(1, 0);
        world.createTileAt(p, GameConstants.OCEANS);
        p = new Position(0, 1);
        world.createTileAt(p, GameConstants.HILLS);
        p = new Position(2, 2);
        world.createTileAt(p, GameConstants.MOUNTAINS);
        p = new Position(1, 1);
        world.createCityAt(p, Player.RED);
        p = new Position(4, 1);
        world.createCityAt(p, Player.BLUE);
        p = new Position(2, 0);
        world.createUnitAt(p, Player.RED, GameConstants.ARCHER);
        p = new Position(4, 3);
        world.createUnitAt(p, Player.RED, GameConstants.SETTLER);
        p = new Position(3, 2);
        world.createUnitAt(p, Player.BLUE, GameConstants.LEGION);
    }
}
