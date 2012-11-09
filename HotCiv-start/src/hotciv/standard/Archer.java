package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 09-11-12
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
public class Archer extends AbstractUnit {
    public Archer(Player owner) {
        unittype = GameConstants.ARCHER;
        this.owner = owner;
        attackingStrength = 2;
        defensiveStrength = 3;
        totalMoves = 1;
        moveCount = totalMoves;
    }
}
