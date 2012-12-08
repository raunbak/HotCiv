package hotciv.winner;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

/**
 *
 */
public interface WinnerStrategy {
    /**
     * Determines the winner of the game.
     * @param game The game that needs its winner determined.
     * @return The winner of the game, null if there isn't one.
     */
    public Player winner(ExtendedGame game);
}
