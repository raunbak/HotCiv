package hotciv.unitaction;

import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public interface UnitActionStrategy {
    public void performUnitAction(World world, Position p);
}
