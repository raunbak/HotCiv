package hotciv.world;

import hotciv.framework.City;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 19-11-12
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public interface WorldStrategy {

    public Tile[][] getTileArray();

    public Unit[][] getUnitArray();

    public City[][] getCityArray();
}
