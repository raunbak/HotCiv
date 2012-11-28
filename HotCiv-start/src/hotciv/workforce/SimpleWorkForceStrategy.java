package hotciv.workforce;

import hotciv.framework.ModifiableCity;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public class SimpleWorkForceStrategy implements WorkForceStrategy {

    @Override
    public void gatherFoodAndProduction(World world, Position p) {
        ((ModifiableCity) world.getCityAt(p)).increaseAmountOfProduction(6);
    }
}
