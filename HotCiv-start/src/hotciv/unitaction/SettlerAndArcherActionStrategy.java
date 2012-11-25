package hotciv.unitaction;

import hotciv.framework.GameConstants;
import hotciv.framework.MutatorKey;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.World;

/**
 *
 */
public class SettlerAndArcherActionStrategy implements UnitActionStrategy {
    @Override
    public void performUnitAction(World world, Position p) {
        MutatorKey mKey = world.getMutatorKey();
        Unit u = world.unitMap.get(p);
        String type = u.getTypeString();

        if (type.equals(GameConstants.ARCHER)) {
            // Toggle the fortify state.
            switch (u.getTotalMoves()) {
                case 1:
                    u.setDefensiveStrength(6, mKey);
                    u.setTotalMoves(0, mKey);
                    u.restoreMoveCount(mKey);
                    break;
                case 0:
                    u.setDefensiveStrength(3, mKey);
                    u.setTotalMoves(1, mKey);
                    u.restoreMoveCount(mKey);
                    break;
            }
        }

        if (type.equals(GameConstants.SETTLER)
                && !world.cityMap.containsKey(p)) {
            // Build a city at the cost of the settler.
            world.cityMap.put(p, new CityImpl(u.getOwner(), mKey));
            world.unitMap.remove(p);
        }
    }
}
