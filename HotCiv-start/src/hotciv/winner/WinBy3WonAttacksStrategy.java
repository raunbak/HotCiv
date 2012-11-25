package hotciv.winner;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 22-11-12
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */
public class WinBy3WonAttacksStrategy implements WinnerStrategy {
    @Override
    public Player winner(Game game) {
        GameImpl gameimpl = (GameImpl) game;

        HashMap<Player, Integer> attacksWon = gameimpl.getAttacksWonMap();

        for (Player p : attacksWon.keySet()) {
            if (attacksWon.get(p) >= 3) {
                return p;
            }
        }

        return null;
    }
}
