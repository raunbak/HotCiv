package hotciv.attackStrategy;

import hotciv.framework.ModifiableUnit;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.World;

/**
 *
 */
public interface AttackStrategy {
    public ModifiableUnit outcomeOfBattle(World world, Position pAttacking, Position pDefending);
}
