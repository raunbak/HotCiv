package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 09-11-12
 * Time: 12:38
 * To change this template use File | Settings | File Templates.
 */
public class Settler extends AbstractUnit {
    public Settler(Player owner) {
        unittype = GameConstants.SETTLER;
        this.owner = owner;
        attackingStrength = 0;
        defensiveStrength = 3;
    }
}
