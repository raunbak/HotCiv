package hotciv.framework;

/**
 * A unit with mutators.
 */
public interface ModifiableUnit extends Unit {

    public void reduceMoveCountBy(int moves);

    public void restoreMoveCount();

    public void setMovable(boolean movable);

    public void setDefensiveStrength(int defStrength);

    public void setPosition(Position p);
}
