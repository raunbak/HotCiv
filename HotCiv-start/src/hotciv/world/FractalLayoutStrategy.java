package hotciv.world;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.World;
import thirdparty.ThirdPartyFractalGenerator;

/**
 * Concrete strategy for making world layout based on the ThirdPartyFractalGenerator.
 */
public class FractalLayoutStrategy implements LayoutStrategy {
    @Override
    public void setupInitialWorld(World world) {
        ThirdPartyFractalGenerator fracGen = new ThirdPartyFractalGenerator();

        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {

                // Get the tile-type represented by the a character from the fractalgenerator.
                char tileChar = fracGen.getLandscapeAt(r, c);

                // Translate the character to a tile-type (like in AdvancedLayoutStrategy).
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

                // Create the tile.
                Position p = new Position(r, c);
                world.createTileAt(p, type);
            }
        }

    }
}
