package hotciv.attack;

import hotciv.framework.ModifiableUnit;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public class SimpleAttackStrategy implements AttackStrategy {
    @Override
    public ModifiableUnit outcomeOfBattle(World world, Position pAttacking, Position pDefending) {
        return world.getUnitAt(pAttacking);

    }

}
