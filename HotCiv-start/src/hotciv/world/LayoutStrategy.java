package hotciv.world;

import hotciv.framework.World;

/**
 * A strategy for generating the initial layout of the world map (Tiles, Cities, and Units).
 */
public interface LayoutStrategy {
    /**
     * Generates the initial layout of the world map (Tiles, Cities, and Units).
     * @param world The world that needs the initial layout generated.
     */
    public void setupInitialWorld(World world);
}
