package hotciv.population;

import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public interface PopulationStrategy {
    public void populationGrowth(World world, Position p);
}
