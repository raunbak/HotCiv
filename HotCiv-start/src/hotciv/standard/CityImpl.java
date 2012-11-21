package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * An implementation of City
 */
public class CityImpl implements City {
    private Player owner;
    private int production;
    private String unitInProduction;
    /**
     * Constructor for CityImpl
     * @param player The owner of this city.
     */
    public CityImpl(Player player) {
        owner = player;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return unitInProduction;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    @Override
    public int getCurrentAmountOfProduction() {
        return production;
    }

    @Override
    public int reduceAmountOfProduction(int amount) {
        return production -= amount;

    }
    @Override
    public int increaseAmountOfProduction(int amount) {
        return production += amount;
    }

    @Override
    public void setProduction(String unit) {
        unitInProduction = unit;
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }

}