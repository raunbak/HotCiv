package hotciv.unitaction;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Unit;
import hotciv.standard.Archer;
import hotciv.standard.CityImpl;

/**
 *
 */
public class SettlerAndArcherActionStrategy implements UnitActionStrategy {
    @Override
    public Object performUnitAction(Unit u, Game game) {
        String type = u.getTypeString();

        if (type.equals(GameConstants.ARCHER)) {
            Archer a = (Archer) u;
            a.fortify();
        }

        if (type.equals(GameConstants.SETTLER)) {
            return new CityImpl(game.getPlayerInTurn());
        }

        return null;
    }
}
