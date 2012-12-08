package hotciv.workforce;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class AdvancedWorkForceStrategy implements WorkForceStrategy {
    @Override
    public void gatherFoodAndProduction(World world, Position pCity) {
        ModifiableCity city = world.getCityAt(pCity);

        // Get the surrounding tiles in a list.
        List<Position> positions = Utility.get8NeighborhoodPositions(pCity);
        List<Tile> tileList = new ArrayList<Tile>();
        for (Position p : positions) {
            tileList.add(world.getTileAt(p));
        }

        // Sort the list in respect to the food or resources in descending order.
        TileComparator tileComp = new TileComparator(city.getWorkForceFocus());
        Collections.sort(tileList, Collections.reverseOrder(tileComp));

        // One of the workers will stay in the city to produce 1 food and 1 production.
        int resourcesProduced = 1;
        int foodGathered = 1;
        int workersAvailable = city.getSize() - 1;
        tileList = tileList.subList(0, workersAvailable);

        // Sum all the food and resources.
        for (Tile t : tileList) {
            resourcesProduced += t.getResourcesPerRound();
            foodGathered += t.getFoodPerRound();
        }

        // Add the gathered food and produced resources to the city.
        city.increaseAmountOfProduction(resourcesProduced);
        city.increaseAmountOfFood(foodGathered);
    }
}
