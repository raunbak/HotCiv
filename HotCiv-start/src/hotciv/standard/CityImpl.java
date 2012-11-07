package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 07-11-12
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public class CityImpl implements City {
    private Player owner;

    public CityImpl(Player p) {
        owner = p;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
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
