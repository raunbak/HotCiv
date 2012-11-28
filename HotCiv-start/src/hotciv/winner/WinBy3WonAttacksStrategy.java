package hotciv.winner;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

/**
 * TODO delete class if we use WinAfterXRoundsBy3WonAttacksStrategy instead.
 */
public class WinBy3WonAttacksStrategy implements WinnerStrategy {
    @Override
    public Player winner(ExtendedGame game) {
        HashMap<Player, Integer> attacksWon = game.getAttacksWonMap();

        for (Player p : attacksWon.keySet()) {
            if (attacksWon.get(p) >= 3) {
                return p;
            }
        }

        return null;
    }
}
