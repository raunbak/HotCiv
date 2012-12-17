package hotciv.world;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 * TODO write a file header
 */
public class PopulatedFractalLayoutStrategy implements LayoutStrategy {
    public final double populationFrequency = 0.04;
    public final double cityToUnitRatio = 0.2;
    public final double[] unitRatios = {0.4, 0.8};  // {a,b} --> [0,a]:archer, [a,b]:legion, [b,1]:settler.

    @Override
    public void setupInitialWorld(World world) {
        LayoutStrategy fracGen = new FractalLayoutStrategy();
        fracGen.setupInitialWorld(world);


        // Populate the world
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                Position p = new Position(r,c);
                String tiletype = world.getTileAt(p).getTypeString();
                if (!tiletype.equals(GameConstants.MOUNTAINS)
                        && !tiletype.equals(GameConstants.OCEANS)
                        && Math.random() < populationFrequency) {
                    Player owner = Player.BLUE;
                    if (Math.random() < 0.5) {
                        owner = Player.BLUE;
                    }
                    if (Math.random() < cityToUnitRatio) {
                        world.createCityAt(p, owner);
                    } else {
                        double unitRoll = Math.random();
                        String unittype = GameConstants.SETTLER;
                        if (unitRoll < unitRatios[0]) {
                            unittype = GameConstants.ARCHER;
                        } else if (unitRoll < unitRatios[1]) {
                            unittype = GameConstants.LEGION;
                        }
                        world.createUnitAt(p, owner, unittype);
                    }
                }
            }
        }
    }
}
