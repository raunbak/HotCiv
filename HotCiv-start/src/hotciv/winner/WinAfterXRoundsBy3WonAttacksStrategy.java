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
    private GameImpl gameimpl;
    private HashMap<Player,Integer> wonAttacksAt20Rounds;
    @Override

    public Player winner(Game game) {
        gameimpl =  (GameImpl) game;

        if (wonAttacksAt20Rounds.isEmpty()) {
            wonAttacksAt20Rounds = gameimpl.getAttackWonMap();
        }

        HashMap<Player,Integer> attacktsWon = gameimpl.getAttackWonMap();

        for (Player p: attacktsWon.keySet()) {
            if ((attacktsWon.get(p)- wonAttacksAt20Rounds.get(p))>=3) {
                return p;
            }
        }
        return null;

    }
}
