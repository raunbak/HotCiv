package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.MutatorKey;
import hotciv.framework.Player;

/**
 * An implementation of City
 */
public class CityImpl implements City {
    private Player owner;
    private int production;
    private String unitInProduction;
    private MutatorKey mutatorKey;

    /**
     * Constructor for CityImpl
     *
     * @param player The owner of this city.
     */
    public CityImpl(Player player, MutatorKey mKey) {
        owner = player;
        mutatorKey = mKey;
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
    public void reduceAmountOfProduction(int amount, MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            production -= amount;
        }
    }

    @Override
    public void increaseAmountOfProduction(int amount, MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            production += amount;
        }
    }

    @Override
    public void setProduction(String unit, MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            unitInProduction = unit;
        }
    }

    @Override
    public void setOwner(Player player, MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            owner = player;
        }
    }

}