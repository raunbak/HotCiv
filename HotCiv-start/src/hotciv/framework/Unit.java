package hotciv.framework;

/**
 * Represents a single unit in the game.
 * <p/>
 * Responsibilities:
 * 1) Know its type name.
 * 2) Know its owner.
 * 3) Know its defensive and attacking strengths.
 * 4) Know remaining moves (added by L&M)
 * 5) Be able to restore it's moves to it's original value. {added by L&N)
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */
public interface Unit {
    // === Accessor methods ===================================
    /**
     * return the type of the unit
     *
     * @return unit type as a string, valid values are at
     *         least those listed in GameConstants, particular variants
     *         may define more strings to be valid.
     */
    public String getTypeString();

    /**
     * return the owner of this unit.
     *
     * @return the player that controls this unit.
     */
    public Player getOwner();

    /**
     * return the move distance left (move count).
     * A move count of N means the unit may travel
     * a distance of N in this turn. The move count
     * is reset to the units maximal value before
     * a new turn starts.
     *
     * @return the move count
     */
    public int getMoveCount();

    /**
     *
     * @return the total moves currently allowed each turn.
     */
    public int getTotalMoves();

    /**
     * return the defensive strength of this unit
     *
     * @return defensive strength
     */
    public int getDefensiveStrength();

    /**
     * return the attack strength of this unit
     *
     * @return attack strength
     */
    public int getAttackingStrength();


    // === Mutator methods ======================================
    /**
     * Reduces a units movecount by int move amount.
     * Precondition: moves <= moveCount
     *
     * @param moves the number of moves to be subtracted from the move count.
     */
    public void reduceMoveCountBy(int moves, MutatorKey mutatorKey);

    /**
     * Restores a units movecount back to the total moves each round.
     */
    public void restoreMoveCount(MutatorKey mutatorKey);

    /**
     * Set the number of moves allowed per round.
     */
    public void setTotalMoves(int totalMoves, MutatorKey mutatorKey);

    public void setDefensiveStrength(int defStrength, MutatorKey mutatorKey);
}
