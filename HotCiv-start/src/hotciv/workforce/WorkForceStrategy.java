package hotciv.workforce;

import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public interface WorkForceStrategy {
    /**
     * Makes the available workers in a city gather food and production.
     * @param world The world of the game.
     * @param pCity The position of the city.
     */
    public void gatherFoodAndProduction(World world, Position pCity);
}
