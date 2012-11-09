package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 09-11-12
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class Legion extends AbstractUnit {
    public Legion(Player owner) {
        unittype = GameConstants.LEGION;
        this.owner = owner;
        attackingStrength = 4;
        defensiveStrength = 2;
    }
}
