package hotciv.winner;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.winner.WinnerStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 15-11-12
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
public class RedWinsAtAge3000BC implements WinnerStrategy {
    @Override
    public Player winner(Game game) {
        if (game.getAge() == -3000 ) {
            return Player.RED;
        }
        else {
            return null;
        }
    }
}
