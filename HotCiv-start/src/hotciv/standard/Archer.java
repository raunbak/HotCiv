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

    public void fortify() {
        switch (totalMoves) {
            case 1:
                defensiveStrength = 6;
                totalMoves = 0;
                moveCount = 0;
                break;
            case 0:
                defensiveStrength = 3;
                totalMoves = 1;
                moveCount = 1;
                break;
        }


    }

}
