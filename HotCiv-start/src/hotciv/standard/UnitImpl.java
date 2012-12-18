package hotciv.standard;

import hotciv.framework.*;

/**
 * UnitImpl is an implementation of Unit.
 */
public class UnitImpl implements ModifiableUnit {
    private Player owner;
    private String unittype;
    private int defensiveStrength;
    private int attackingStrength;
    private int moveCount;
    private int totalMoves;
    private Position position;

    public UnitImpl(Player owner, String unittype, Position p) {
        this.owner = owner;
        this.unittype = unittype;
        position = p;
        try {
            defensiveStrength = GameConstants.DEFMAP.get(unittype);
            attackingStrength = GameConstants.ATTMAP.get(unittype);
            totalMoves = GameConstants.MOVEMAP.get(unittype);
        } catch (NullPointerException npEx) {
            throw new InvalidTypeException(unittype);
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
    public Position getPosition() {
        return position;
    }

    @Override
    public void reduceMoveCountBy(int moves) {
        moveCount -= moves;
    }

    @Override
    public void restoreMoveCount() {
        moveCount = totalMoves;
    }

    @Override
    public void setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    @Override
    public void setDefensiveStrength(int defStrength) {
        defensiveStrength = defStrength;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
    }
}
