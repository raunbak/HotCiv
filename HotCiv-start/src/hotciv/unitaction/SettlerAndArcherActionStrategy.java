package hotciv.unitaction;

import hotciv.framework.Game;
import hotciv.framework.Unit;
import hotciv.standard.Archer;
import hotciv.standard.CityImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 20-11-12
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 */
public class SettlerAndArcherActionStrategy implements UnitActionStrategy {
    @Override
    public Object performUnitAction(Unit u, Game game) {
        String type = u.getTypeString();

        if (type.equals("archer")) {

            Archer a = (Archer) u;
            a.fortify();
        }
        if (type.equals("settler")) {
            return new CityImpl(game.getPlayerInTurn());
        }
        return null;
    }
}
