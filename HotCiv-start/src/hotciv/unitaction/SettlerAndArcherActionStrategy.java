package hotciv.unitaction;

import hotciv.framework.GameConstants;
import hotciv.framework.MutatorKey;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Archer;
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
            Archer a = (Archer) u;
            a.fortify(mKey);
        }

        if (type.equals(GameConstants.SETTLER)
                && !world.cityMap.containsKey(p)) {

            world.cityMap.put(p, new CityImpl(u.getOwner(), mKey));
            world.unitMap.remove(p);
        }
    }
}
