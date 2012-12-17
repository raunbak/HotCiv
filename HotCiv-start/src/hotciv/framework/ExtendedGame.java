package hotciv.framework;

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
     * Returns a set containing all the positions at which there are units.
     * Added by L&M.
     */
    public Iterable<Position> getUnitPositions();

    /**
     * Add an AttacksWonSubscriber to the list that will get notified each time an attack is won.
     * @param sub The subscriber that wants to receive updates in regards to attacks won.
     */
    public void addAttacksWonSubscriber(AttacksWonSubscriber sub);
}
