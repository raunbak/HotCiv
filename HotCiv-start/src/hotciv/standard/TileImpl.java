package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: Laurids
 * Date: 07-11-12
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
public class TileImpl implements Tile {
    private Position position;
    private String type;

    public TileImpl(Position p, String type) {
        position = p;
        this.type = type;
    }

    @Override
    public Position getPosition() {
        return null;    // TODO return position
    }

    @Override
    public String getTypeString() {
        return type;
    }
}
