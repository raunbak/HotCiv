package hotciv.workforce;

import com.vladium.util.IntSet;
import hotciv.framework.*;

import java.util.*;

/**
 *
 */
public class AdvancedWorkForceStrategy implements WorkForceStrategy {
    @Override
    public void gatherFoodAndProduction(World world, Position pCity) {
        ModifiableCity city = world.getCityAt(pCity);
        String focus = city.getWorkForceFocus();

        Iterator<Position> neighborhood = Utility.get8NeighborhoodIterator(pCity);
        Set<Position> positions = new HashSet<Position>();
        while (neighborhood.hasNext()) {
            Position p = neighborhood.next();
            positions.add(p);
        }

        // One of the people will stay in the city to produce 1 food and 1 production.
        int resourcesProduced = 1;
        int foodGathered = 1;

        int peopleAvailable = city.getSize() - 1;
        int peopleUsed = 0;

        Integer[] tileResources = new Integer[positions.size()];
        Integer[] tileFood = new Integer[positions.size()];

        for (Position p : positions) {
            String tileType = world.getTileAt(p).getTypeString();

            tileResources[peopleUsed] = GameConstants.PRODMAP.get(tileType);
            tileFood[peopleUsed] = GameConstants.FOODMAP.get(tileType);

            peopleUsed++;
        }


        System.out.println("Workforce focus: "+focus); // todo delete print

        // No preferences, just pick the number of tiles needed.
        if (focus == null) {
            for (int i = 0; i < peopleAvailable; i++) {
                resourcesProduced += tileResources[i];
                foodGathered += tileFood[i];

                System.out.println("Tile["+i+"] gives: "+tileFood[i]+" food and "+tileResources[i]+" production."); // todo delete print
            }
        }
        else if (focus.equals(GameConstants.productionFocus)) {
            // Sort in descending order.
            Arrays.sort(tileResources, Collections.reverseOrder());

            for (int i = 0; i < peopleAvailable; i++) {
                resourcesProduced += tileResources[i];

                System.out.println("Tile["+i+"] gives: "+tileResources[i]+" production.");  // todo delete print
            }
        }
        else if (focus.equals(GameConstants.foodFocus)) {
            // Sort in descending order.
            Arrays.sort(tileFood, Collections.reverseOrder());

            for (int i = 0; i < peopleAvailable; i++) {
                foodGathered += tileFood[i];

                System.out.println("Tile["+i+"] gives: "+tileFood[i]+" food.");  // todo delete print
            }


        }
        else {
            throw new InvalidTypeException(focus);
        }


        city.increaseAmountOfProduction(resourcesProduced);
        city.increaseAmountOfFood(foodGathered);

        // City population
        int foodLimit = 5 + 3 * city.getSize();
        if (city.getCurrentAmountOfFood() > foodLimit && city.getSize() < 9) {
            city.addToPopulation(1);
            city.reduceAmountOfFood(foodLimit);
        }
    }
}
