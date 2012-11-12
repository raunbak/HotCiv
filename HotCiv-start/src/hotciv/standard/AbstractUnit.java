package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * AbstractUnit is an implementation of Unit.
 * it has implementations for all of the necessary methods of a unit.
 *
 */
public abstract class AbstractUnit implements Unit
{
    protected Player owner;
    protected String unittype;
    protected int defensiveStrength;
    protected int attackingStrength;
    protected int moveCount;
    protected int totalMoves;

    @Override
    public String getTypeString() {
        return unittype;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return attackingStrength;
    }

    @Override
    public void reduceMoveCountBy(int moves) {
        moveCount -= moves;
    }

    @Override
    public void restoreMoveCount() {
        moveCount = totalMoves;
    }

}
