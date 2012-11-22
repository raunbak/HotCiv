package hotciv.winner;

import hotciv.framework.Game;
import hotciv.framework.Player;

/**
 *
 */
public class RedWinsAtAge3000BCStrategy implements WinnerStrategy {
    @Override
    public Player winner(Game game) {
        if (game.getAge() >= -3000) {
            return Player.RED;
        } else {
            return null;
        }
    }
}
