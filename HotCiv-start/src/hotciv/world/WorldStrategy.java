package hotciv.world;

import hotciv.framework.World;

/**
 * A strategy for generating the initial layout of the world map (Tiles, Cities, and Units).
 */
public interface WorldStrategy {

    public void setupInitialWorld(World world);

}
