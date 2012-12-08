package hotciv.framework;

import java.util.Comparator;

/**
 * An implementation of Comparator to be used for sorting a list of tiles.
 * Can be used to determine the maximum food or production to be gained
 *  from a limited number of the tiles surrounding a city.
 */
public class TileComparator implements Comparator<Tile> {
    private String focus;

    public TileComparator(String focus) {
        this.focus = focus;
    }

    @Override
    public int compare(Tile t1, Tile t2) {
        int t1Value;
        int t2Value;
        if (focus.equals(GameConstants.foodFocus)) {
            t1Value = t1.getFoodPerRound();
            t2Value = t2.getFoodPerRound();
        }
        else if (focus.equals(GameConstants.productionFocus)) {
            t1Value = t1.getResourcesPerRound();
            t2Value = t2.getResourcesPerRound();
        }
        else {
            throw new InvalidTypeException(focus);
        }

        return t1Value - t2Value;
    }
}
