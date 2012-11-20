package hotciv.unitaction;

import hotciv.framework.Game;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 20-11-12
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 */
public class NoActionStrategy implements UnitActionStrategy {
    @Override
    public Object performUnitAction(Unit u, Game game) {
        return null;
    }
}
