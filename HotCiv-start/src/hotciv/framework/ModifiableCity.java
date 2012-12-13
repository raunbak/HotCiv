package hotciv.framework;

/**
 * A city with mutators.
 */
public interface ModifiableCity extends City {

    public void reduceAmountOfProduction(int amount);

    public void increaseAmountOfProduction(int amount);

    public void setProduction(String unittype);

    public void setWorkForceFocus(String focus);

    public void increaseAmountOfFood(int amount);

    public void reduceAmountOfFood(int amount);

    public void addToPopulation(int numberOfPeople);

    public void setOwner(Player player);

    /**
     * A method for producing as many units around a city as it can afford, provided there are enough free tiles.
     * The city will also have the production cost of the units produced subtracted from its production amount.
     * Precondition: there is a city at position pCity.
     * Added by L&M.
     *
     * @param world
     * @param pCity The position of the city that should produce units.
     * @param observers
     */
    public void produceUnits(World world, Position pCity, GameObsComposite observers);
}
