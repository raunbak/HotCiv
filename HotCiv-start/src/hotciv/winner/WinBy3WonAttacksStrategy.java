package hotciv.winner;

import hotciv.framework.AttacksWonSubscriber;
import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

import java.util.HashMap;

/**
 *
 */
public class WinBy3WonAttacksStrategy implements WinnerStrategy, AttacksWonSubscriber {
    private HashMap<Player,Integer> attacksWon;

    public WinBy3WonAttacksStrategy(ExtendedGame game) {
        attacksWon = new HashMap<Player, Integer>();
        game.addAttacksWonSubscriber(this);
    }

    @Override
    public Player winner(ExtendedGame game) {
        for (Player p : attacksWon.keySet()) {
            if (attacksWon.get(p) >= 3) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void aWonAttack(Player p) {

        if (!attacksWon.containsKey(p))  {
            attacksWon.put(p,0);
        }
        int attWon = attacksWon.get(p);
        attacksWon.put(p, attWon + 1);
    }
}
