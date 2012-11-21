package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * An implementation of the Tile interface.
 * Created with IntelliJ IDEA.
 * User: Laurids
 */
public class TileImpl implements Tile {
    private Position position;
    private String type;

    /**
     *  Constructor for TileImpl.
     * @param p (the position of the tile)
     * @param type (the type of tile, String value)
     */
    public TileImpl(Position p,
                    String type) {

        position = p;
        this.type = type;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getTypeString() {
        return type;
    }
}
