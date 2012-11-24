package hotciv.world;

import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 19-11-12
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public interface WorldStrategy {

    public HashMap<Position, Tile> getTileArray();

    public HashMap<Position, Unit> getUnitArray();

    public HashMap<Position, City> getCityArray();
}
