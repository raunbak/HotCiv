package hotciv.world;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 19-11-12
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public interface WorldStrategy {

    public HashMap<Position, Tile> getTileMap();

    public HashMap<Position, Unit> getUnitMap();

    public HashMap<Position, City> getCityMap();

    public MutatorKey getMutatorKey();
}
