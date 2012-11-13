package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Class for an Archer Unit.
 * Extends AbstractUnit
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
