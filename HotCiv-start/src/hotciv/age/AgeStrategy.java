package hotciv.age;

/**
 *
 */
public interface AgeStrategy {
    /**
     * Calculates the new age of the game. To be called at the end of every round.
     * @param oldAge The current age of the game.
     * @return The new age of the game.
     */
    public int calculateAge(int oldAge);
}
