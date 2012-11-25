package hotciv.winner;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.Set;

/**
 *
 */
public class WinByConquestStrategy implements WinnerStrategy {

    /**
     * Precondition: There are at least two cities in the game.
     *
     * @param game The game that should have its winner determined.
     * @return The winner of the game or null if there is no winner yet.
     */
    @Override
    public Player winner(Game game) {

        Set<Position> cityKeySet = game.getCityPositions();
        Player winnerSoFar = null;

        for (Position p : cityKeySet) {
            if (winnerSoFar == null) {
                winnerSoFar = game.getCityAt(p).getOwner();
            }

            // If one of the cities has a different owner than winnerSoFar,
            //      then there is no winner yet, and null should be returned.
            if (!game.getCityAt(p).getOwner().equals(winnerSoFar)) {
                winnerSoFar = null;
                break;
            }

        }

        return winnerSoFar;
    }
}
