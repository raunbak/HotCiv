package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * An implementation of City
 */
public class CityImpl implements City {
    private Player owner;

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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getWorkforceFocus() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}