package hotciv.winner;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

/**
 * Precondition, this strategy should only used when a game has lasted X rounds. Like 20 rounds.
 * Should only be used with AlternatingWinStrategy.
 */
public class WinAfterXRoundsBy3WonAttacksStrategy implements WinnerStrategy {
    private HashMap<Player,Integer> wonAttacksAt20Rounds;

    @Override
    public Player winner(Game game) {
        GameImpl gameimpl = (GameImpl) game;

        if (wonAttacksAt20Rounds == null) {
            wonAttacksAt20Rounds = gameimpl.getAttacksWonMap();
        }

        HashMap<Player,Integer> attacksWon = gameimpl.getAttacksWonMap();

        for (Player p : attacksWon.keySet()) {
            if ((attacksWon.get(p) - wonAttacksAt20Rounds.get(p)) >= 3) {
                return p;
            }
        }
        return null;

    }
}
