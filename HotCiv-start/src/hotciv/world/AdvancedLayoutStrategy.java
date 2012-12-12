package hotciv.world;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public class AdvancedLayoutStrategy implements LayoutStrategy {


    private String[] tilelayout = new String[]{
            "...ooMooooo.....",
            "..ohhoooofffoo..",
            ".oooooMooo...oo.",
            ".ooMMMoooo..oooo",
            "...ofooohhoooo..",
            ".ofoofooooohhoo.",
            "...ooo..........",
            ".ooooo.ooohooM..",
            ".ooooo.oohooof..",
            "offfoooo.offoooo",
            "oooooooo...ooooo",
            ".ooMMMoooo......",
            "..ooooooffoooo..",
            "....ooooooooo...",
            "..ooohhoo.......",
            ".....ooooooooo..",
    };

    private void defineTilesOfWorld(World world) {
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = tilelayout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                }
                if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                }
                if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                }
                if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                }
                if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }

                Position p = new Position(r, c);
                world.createTileAt(p, type);

            }
        }
    }

    @Override
    public void setupInitialWorld(World world) {
        defineTilesOfWorld(world);
        Position p = new Position(8, 12);
        world.createCityAt(p, Player.RED);
        p = new Position(4, 5);
        world.createCityAt(p, Player.BLUE);
        p = new Position(2, 1);
        world.createUnitAt(p, Player.RED, GameConstants.ARCHER);
    }
}