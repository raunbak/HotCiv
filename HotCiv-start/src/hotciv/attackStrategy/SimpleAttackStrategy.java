package hotciv.attackStrategy;

import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.World;

/**
 *
 */
public class SimpleAttackStrategy implements AttackStrategy {
    @Override
    public Unit outcomeOfBattle(World world, Position pAttacking, Position pDefending) {
        return world.getUnitAt(pAttacking);

    }

}
