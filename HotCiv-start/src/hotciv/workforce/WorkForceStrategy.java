package hotciv.workforce;

import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public interface WorkForceStrategy {
    /**
     *
     * @param world
     * @param p
     */
    public void gatherFoodAndProduction(World world, Position p);
}
