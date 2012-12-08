package hotciv.population;

import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public interface PopulationStrategy {
    /**
     * Makes population in a city grow. To be used at the end of each round.
     * @param world The world of the game.
     * @param pCity The position of the city.
     */
    public void populationGrowth(World world, Position pCity);
}
