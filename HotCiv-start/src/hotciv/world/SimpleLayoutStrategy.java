package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.*;

/**
 *
 */
public class SimpleLayoutStrategy implements WorldStrategy {
    private Tile[][] tileTable = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    private City[][] cityTable = new City[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    private Unit[][] unitTable = new Unit[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];


    public SimpleLayoutStrategy() {
        // Initialize the tile array with plains on every tile, with the responding positions.
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                tileTable[i][j] = new TileImpl(new Position(i, j), GameConstants.PLAINS);
            }
        }
        tileTable[1][0] = new TileImpl(new Position(1, 0), GameConstants.OCEANS);
        tileTable[0][1] = new TileImpl(new Position(0, 1), GameConstants.HILLS);
        tileTable[2][2] = new TileImpl(new Position(2, 2), GameConstants.MOUNTAINS);
        cityTable[1][1] = new CityImpl(Player.RED);
        cityTable[4][1] = new CityImpl(Player.BLUE);
        unitTable[2][0] = new Archer(Player.RED);
        unitTable[4][3] = new Settler(Player.RED);
        unitTable[3][2] = new Legion(Player.BLUE);
    }

    @Override
    public Tile[][] getTileArray() {
        return tileTable;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Unit[][] getUnitArray() {
        return unitTable;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public City[][] getCityArray() {
        return cityTable;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
