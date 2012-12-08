package hotciv.attack;

import hotciv.framework.ModifiableUnit;
import hotciv.framework.Position;
import hotciv.framework.World;

/**
 *
 */
public interface AttackStrategy {
    /**
     * Determines which unit wins a battle.
     * @param world The world of the game.
     * @param pAttacking The position where the attacking unit were moved from.
     * @param pDefending The position of the defending unit, where the attacking unit is moving to.
     * @return the winning unit.
     */
    public ModifiableUnit outcomeOfBattle(World world, Position pAttacking, Position pDefending);
}
