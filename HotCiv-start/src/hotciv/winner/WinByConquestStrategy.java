package hotciv.winner;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;

/**
 *
 */
public class WinByConquestStrategy implements WinnerStrategy{

    /**
     * Precondition: There are at least two cities in the game.
     * @param game The game that should have its winner determined.
     * @return The winner of the game or null if there is no winner yet.
     */
    @Override
    public Player winner(Game game) {

        City[] citylist = game.getAllCities();
        Player winnerSoFar = citylist[0].getOwner();

        for (int i = 1; i < citylist.length; i++) {
            // If one of the cities has a different owner than winnerSoFar,
            //      then there is no winner yet, and null should be returned.
            if (! citylist[i].getOwner().equals(winnerSoFar)) {
                winnerSoFar = null;
                break;
            }
        }

        return winnerSoFar;
    }
}
