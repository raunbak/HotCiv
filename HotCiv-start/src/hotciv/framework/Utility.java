package hotciv.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of utility methods for speeding up
 * the implementation effort in the HotCiv Game
 * <#if type == "code">
 * <p/>
 * <#include "/data/author.txt">
 * </#if>
 */
public class Utility {

    /**
     * return an iterator over positions that are in the 8 neighborhood
     * of a given position. The 8 neighborhood is the (normally 8)
     * positions adjacent to the center position. The center position
     * itself is not part of the iterator. PostCondition: The iterator
     * is always valid and will contain from 3 to 8 positions. It will
     * only contain positions that are valid on the game world, that is
     * a neighborhood centered in (0,0) will contain (1,0), (1,1) and
     * (0,1) but not e.g. (-1,-1). The iterator always return the
     * positions in a sequence starting at the north west position and
     * the rest given row-wise.
     *
     * @param center the position marking the center of the 8 neighborhood.
     * @return iterator over the valid positions in the center's 8
     *         neighborhood.
     */
    public static List<Position> get8NeighborhoodPositions(Position center) {
        List<Position> list = new ArrayList<Position>();
        int row = center.getRow();
        int col = center.getColumn();
        int r, c;
        for (int dr = -1; dr <= +1; dr++) {
            for (int dc = -1; dc <= +1; dc++) {
                r = row + dr;
                c = col + dc;
                // avoid positions outside the world
                if (r >= 0 && r < GameConstants.WORLDSIZE &&
                        c >= 0 && c < GameConstants.WORLDSIZE) {
                    // avoid center position
                    if (r != row || c != col) {
                        list.add(new Position(r, c));
                    }
                }
            }
        }
        return list;
    }

    /**
     * get the terrain factor for the attack and defense strength
     * according to the GammaCiv specification
     *
     * @param world     the world the factor should be given for
     * @param position the position that the factor should be calculated
     *                 for
     * @return the terrain factor
     */
    public static int getTerrainFactor(World world, Position position) {
        // cities overrule underlying terrain
        if (world.getCityAt(position) != null) {
            return 3;
        }
        Tile t = world.getTileAt(position);
        if (t.getTypeString().equals(GameConstants.FOREST) ||
                t.getTypeString().equals(GameConstants.HILLS)) {
            return 2;
        }
        return 1;
    }

    /**
     * calculate the additional support a unit at position p owned by a
     * given player gets from friendly units on the given world.
     *
     * @param world     the world to calculate on
     * @param position the position of the unit whose support is wanted
     * @param player   the player owning the unit at position 'position'
     * @return the support for the unit, +1 for each friendly unit in the 8
     *         neighborhood.
     */
    public static int getFriendlySupport(World world, Position position,
                                         Player player) {
        Iterable<Position> neighborhood = get8NeighborhoodPositions(position);
        int support = 0;
        for (Position p : neighborhood) {
            if (world.getUnitAt(p) != null &&
                    world.getUnitAt(p).getOwner() == player) {
                support++;
            }
        }
        return support;
    }
}