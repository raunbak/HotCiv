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
     * @param player
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
        return unitInProduction;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getWorkforceFocus() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getCurrentAmountOfProduction() {
        return production;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int reduceAmountOfProduction(int amount) {
        return production -= amount;

    }
    @Override
    public int addAmountTofProduction(int amount) {
        return production += amount;
    }

    @Override
    public void setProduction(String unit) {
        unitInProduction = unit;
    }

}