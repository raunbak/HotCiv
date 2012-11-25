package hotciv.framework;

/**
 * Represents a city in the game.
 * <p/>
 * Responsibilities:
 * 1) Knows its owner.
 * 2) Knows its population size.
 * 3) Know how much production it has.  (L&M)
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
public interface City {
    // === Accessor methods ===================================
    /**
     * return the owner of this city.
     *
     * @return the player that controls this city.
     */
    public Player getOwner();

    /**
     * return the size of the population.
     *
     * @return population size.
     */
    public int getSize();

    /**
     * return the type of unit this city is currently producing.
     *
     * @return a string type defining the unit under production,
     *         see GameConstants for valid values.
     */
    public String getProduction();

    /**
     * return the work force's focus in this city.
     *
     * @return a string type defining the focus, see GameConstants
     *         for valid return values.
     */
    public String getWorkforceFocus();

    /**
     * Returns how much a city has of production.
     *
     * @return an int of the current amount of production the city has.
     */
    public int getCurrentAmountOfProduction();


    // === Mutator methods ======================================
    /**
     * Reduce the amount of production in the city. Used when producing units.
     * @param amount the amount to be subtracted from the current amount.
     */
    public void reduceAmountOfProduction(int amount, MutatorKey mutatorKey);

    /**
     * Increase the amount of production in the city. Used after each round.
     * @param amount the amount to be added to the current amount.
     */
    public void increaseAmountOfProduction(int amount, MutatorKey mutatorKey);

    /**
     * Set the type of unit that the city should produce.
     * @param unittype the new type of unit to be produced.
     */
    public void setProduction(String unittype, MutatorKey mutatorKey);

    /**
     * Set the owner of the city.
     * @param player the new owner of the city.
     */
    public void setOwner(Player player, MutatorKey mutatorKey);

}
