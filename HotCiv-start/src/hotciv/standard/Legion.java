package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.MutatorKey;
import hotciv.framework.Player;

/**
 * Class for a Legion Unit
 * Extends AbstractUnit.
 */
public class Legion extends AbstractUnit {
    public Legion(Player owner, MutatorKey mKey) {
        unittype = GameConstants.LEGION;
        this.owner = owner;
        attackingStrength = 4;
        defensiveStrength = 2;
        totalMoves = 1;
        moveCount = totalMoves;
        mutatorKey = mKey;
    }
}
