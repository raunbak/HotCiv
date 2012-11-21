package hotciv.framework;

/**
 * A class representing a 2-dimensional vector with integer components.
 * Used in method produceUnitsInCityAt() in GameImpl in conjunction with Position
 * to delegate some of the calculation-responsibility,
 * as well as make the code cleaner and more readable.
 * Added by L&M.
 */
public class Vector {
    // Here, (r,c) is not an absolute position, rather a position relative to actual positions in the grid.
    private int r;
    private int c;

    public Vector(int r, int c) {
        this.r = r;
        this.c = c;
    }

    /**
     * Get a new position object with the same coordinates as p, but with this vector added.
     *
     * @param p the position that this vector should be added to in the returned position.
     * @return a position that is the result of adding this vector to p.
     */
    public Position addToPosition(Position p) {
        return new Position(r + p.getRow(), c + p.getColumn());
    }

    /**
     * Rotate this vector 90 degrees clockwise.
     * A vector (r,c) becomes (c,-r).
     */
    public void rotate90clockwise() {
        int rOld = r;
        r = c;
        c = -rOld;
    }

    public int getRowCoordinate() {
        return r;
    }

    public int getColumnCoordinate() {
        return c;
    }
}
