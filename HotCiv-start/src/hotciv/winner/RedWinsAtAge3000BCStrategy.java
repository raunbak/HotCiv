package hotciv.winner;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

/**
 *
 */
public class RedWinsAtAge3000BCStrategy implements WinnerStrategy {
    @Override
    public Player winner(ExtendedGame game) {
        if (game.getAge() == -3000) {
            return Player.RED;
        } else {
            return null;
        }
    }
}
