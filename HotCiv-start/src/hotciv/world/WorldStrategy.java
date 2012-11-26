package hotciv.world;

import hotciv.standard.World;

import java.util.HashMap;

/**
 * A strategy for generating the initial layout of the world map (Tiles, Cities, and Units).
 */
public interface WorldStrategy {

    public World getWorld();

}
