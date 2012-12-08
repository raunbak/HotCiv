package hotciv.unitaction;

import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public interface UnitActionStrategy {
    /**
     * Makes a unit do its unitaction.
     * @param world The world of the game.
     * @param p The position of the unit.
     */
    public void performUnitAction(World world, Position p);
}
