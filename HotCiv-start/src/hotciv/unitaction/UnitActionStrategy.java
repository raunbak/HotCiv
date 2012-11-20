package hotciv.unitaction;

import hotciv.framework.Game;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 20-11-12
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public interface UnitActionStrategy {
    public Object performUnitAction(Unit u, Game game);
}
