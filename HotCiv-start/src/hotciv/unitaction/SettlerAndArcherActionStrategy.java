package hotciv.unitaction;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.World;

/**
 *
 */
public class SettlerAndArcherActionStrategy implements UnitActionStrategy {
    @Override
    public void performUnitAction(World world, Position p) {
        UnitImpl u = world.unitMap().get(p);
        String type = u.getTypeString();

        if (type.equals(GameConstants.ARCHER)) {
            // Toggle the fortify state.
            switch (u.getTotalMoves()) {
                case 1:
                    u.setDefensiveStrength(u.getDefensiveStrength() * 2);
                    u.setTotalMoves(0);
                    u.restoreMoveCount();
                    break;
                case 0:
                    u.setDefensiveStrength(u.getDefensiveStrength() / 2);
                    u.setTotalMoves(1);
                    u.restoreMoveCount();  // TODO this could be abused to get an infinite number of moves in one turn. Do we have to introduce a "movable"-variable in UnitImpl?
                    break;
            }
        }

        if (type.equals(GameConstants.SETTLER)
                && !world.cityMap().containsKey(p)) {
            // Build a city at the cost of the settler.
            world.cityMap().put(p, new CityImpl(u.getOwner()));
            world.unitMap().remove(p);
        }
    }
}
