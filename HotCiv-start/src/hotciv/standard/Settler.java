package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.MutatorKey;
import hotciv.framework.Player;

/**
 * A class for a Settler Unit
 * Extends AbstractUnit.
 */
public class Settler extends AbstractUnit {
    public Settler(Player owner, MutatorKey mKey) {
        unittype = GameConstants.SETTLER;
        this.owner = owner;
        attackingStrength = 0;
        defensiveStrength = 3;
        totalMoves = 1;
        moveCount = totalMoves;
        mutatorKey = mKey;
    }
}
