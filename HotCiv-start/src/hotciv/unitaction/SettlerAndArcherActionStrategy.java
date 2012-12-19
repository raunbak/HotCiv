package hotciv.unitaction;

import hotciv.framework.GameConstants;
import hotciv.framework.ModifiableUnit;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public class SettlerAndArcherActionStrategy implements UnitActionStrategy {
    @Override
    public void performUnitAction(World world, Position p) {
        ModifiableUnit u = world.getUnitAt(p);
        String type = u.getTypeString();

        if (type.equals(GameConstants.ARCHER)) {
            int defStr = u.getDefensiveStrength();
            u.setDefensiveStrength(defStr != GameConstants.DEFMAP.get(type) ? defStr/2 : defStr*2);
            u.setMovable(!u.isMovable());
        }

        if (type.equals(GameConstants.SETTLER)
                && world.getCityAt(p) == null) {
            // Build a city at the cost of the settler.
            world.createCityAt(p, u.getOwner());
            world.removeUnitAt(p);
        }
    }
}
