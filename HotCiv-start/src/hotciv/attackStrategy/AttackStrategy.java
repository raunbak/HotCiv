package hotciv.attackStrategy;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 22-11-12
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public interface AttackStrategy {
    public Unit outcomeOfBattle(Game game, Position pAttacking, Position pDefending);
}
