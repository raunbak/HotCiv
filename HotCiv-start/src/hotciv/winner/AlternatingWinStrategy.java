package hotciv.winner;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 24-11-12
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
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
    public Player winner(Game game) {
        if (game.getRoundsPlayed() <= 20) {
            currentStrategy = before20RoundsStrategy;
        }
        else {
            currentStrategy = after20RoundsStrategy;
        }

        return currentStrategy.winner(game);
    }
}
