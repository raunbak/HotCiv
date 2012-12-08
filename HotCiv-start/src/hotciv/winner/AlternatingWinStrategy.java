package hotciv.winner;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;

/**
 *
 */
public class AlternatingWinStrategy implements WinnerStrategy {
    private WinnerStrategy before20RoundsStrategy,
            after20RoundsStrategy,
            currentStrategy;

    public AlternatingWinStrategy (WinnerStrategy before20RoundsStrategy,
                                   WinnerStrategy after20RoundsStrategy) {
        this.before20RoundsStrategy = before20RoundsStrategy;
        this.after20RoundsStrategy = after20RoundsStrategy;
        this.currentStrategy = null;
    }
    @Override
    public Player winner(ExtendedGame game) {
        if (game.getRoundsPlayed() <= 20) {
            currentStrategy = before20RoundsStrategy;
        }
        else {
            currentStrategy = after20RoundsStrategy;
        }

        return currentStrategy.winner(game);
    }
}
