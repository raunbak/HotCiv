package hotciv.attackStrategy;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Legion;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 22-11-12
 * Time: 12:27
 * To change this template use File | Settings | File Templates.
 */
public class AdvancedAttackStrategy implements AttackStrategy {
    @Override
    public Unit outcomeOfBattle(Game game, Position unitAttacting, Position unitDefending) {
        return new Legion(Player.BLUE);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
