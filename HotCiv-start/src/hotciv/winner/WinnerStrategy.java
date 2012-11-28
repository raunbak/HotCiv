package hotciv.winner;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

/**
 *
 */
public interface WinnerStrategy {

    public Player winner(ExtendedGame game);

}
