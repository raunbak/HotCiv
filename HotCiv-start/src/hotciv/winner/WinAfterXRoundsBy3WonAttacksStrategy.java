package hotciv.winner;

import hotciv.framework.AttacksWonSubscriber;
import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

import java.util.HashMap;

/**
 * Precondition, this strategy should only used when a game has lasted X rounds. Like 20 rounds.
 * Should only be used with AlternatingWinStrategy.
 */
public class WinAfterXRoundsBy3WonAttacksStrategy implements WinnerStrategy, AttacksWonSubscriber {
    private HashMap<Player,Integer> wonAttacksAt20Rounds = new HashMap<Player, Integer>();

    @Override
    public Player winner(ExtendedGame game) {

        for (Player p : wonAttacksAt20Rounds.keySet()) {
            if (wonAttacksAt20Rounds.get(p) >= 3) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void aWonAttack(Player p) {
        int attWon = wonAttacksAt20Rounds.get(p);
        wonAttacksAt20Rounds.put(p, attWon + 1);
    }
}
