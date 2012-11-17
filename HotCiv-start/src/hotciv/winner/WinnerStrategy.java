package hotciv.winner;

import hotciv.framework.Game;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 15-11-12
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public interface WinnerStrategy {

    public Player winner(Game game);

}
