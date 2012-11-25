package hotciv.standard;

import hotciv.framework.*;

/**
 * UnitImpl is an implementation of Unit.
 */
public class UnitImpl implements Unit {
    protected Player owner;
    protected String unittype;
    protected int defensiveStrength;
    protected int attackingStrength;
    protected int moveCount;
    protected int totalMoves;
    protected MutatorKey mutatorKey;

    public UnitImpl(Player owner, String unittype, MutatorKey mutatorKey) {
        this.owner = owner;
        this.unittype = unittype;
        this.mutatorKey = mutatorKey;
        try {
            defensiveStrength = GameConstants.DEFMAP.get(unittype);
            attackingStrength = GameConstants.ATTMAP.get(unittype);
            totalMoves = GameConstants.MOVEMAP.get(unittype);
        } catch (NullPointerException npEx) {
            throw new InvalidUnittypeException("Unittype not found in one of the stat-maps in GameConstants.");
        }
        moveCount = totalMoves;
    }

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
    public int getTotalMoves() {
        return totalMoves;
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
    public void reduceMoveCountBy(int moves, MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            moveCount -= moves;
        }
    }

    @Override
    public void restoreMoveCount(MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            moveCount = totalMoves;
        }
    }

    @Override
    public void setTotalMoves(int totalMoves, MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            this.totalMoves = totalMoves;
        }
    }

    @Override
    public void setDefensiveStrength(int defStrength, MutatorKey mKey) {
        if (mutatorKey.equals(mKey)) {
            defensiveStrength = defStrength;
        }
    }

}
