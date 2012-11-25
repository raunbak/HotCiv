package hotciv.attackStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 22-11-12
 * Time: 12:27
 * To change this template use File | Settings | File Templates.
 */
public class AdvancedAttackStrategy implements AttackStrategy {
    @Override
    public Unit outcomeOfBattle(Game game, Position pAttacking, Position pDefending) {
        // TODO implement the features!
        return game.getUnitAt(pAttacking);
    }
}
