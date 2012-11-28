package hotciv.framework;

import java.util.HashMap;

/**
 * A game with extra methods.
 */
public interface ExtendedGame extends Game {
    /**
     * Get the number of rounds played so far.
     * Added by L&M.
     * @return Number of rounds played
     */
    public int getRoundsPlayed();

    /**
     * Returns a set containing all the positions at which there are cities.
     * Added by L&M.
     */
    public Iterable<Position> getCityPositions();

    /**
     *
     * @return
     */
    public HashMap<Player, Integer> getAttacksWonMap();
}
