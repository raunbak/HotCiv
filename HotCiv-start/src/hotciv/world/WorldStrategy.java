package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 19-11-12
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public interface WorldStrategy {

    public HashMap<Position, TileImpl> getTileMap();

    public HashMap<Position, UnitImpl> getUnitMap();

    public HashMap<Position, CityImpl> getCityMap();
}
