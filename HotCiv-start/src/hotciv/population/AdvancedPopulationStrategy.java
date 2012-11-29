package hotciv.population;

import hotciv.framework.ModifiableCity;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public class AdvancedPopulationStrategy implements PopulationStrategy {
    @Override
    public void populationGrowth(World world, Position p) {
        ModifiableCity city = world.getCityAt(p);

        // City population
        int foodAmount = city.getCurrentAmountOfFood();
        int foodLimit = 5 + 3 * city.getSize();
        if (foodAmount > foodLimit && city.getSize() < 9) {
            city.addToPopulation(1);
            city.reduceAmountOfFood(foodAmount);
        }
    }
}
