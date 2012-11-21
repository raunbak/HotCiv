package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.Archer;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;

/**
 *
 */
public class AdvancedLayoutStrategy implements WorldStrategy {
    private Tile[][] tileTable = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    private City[][] cityTable = new City[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    private Unit[][] unitTable = new Unit[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];


    String[] tilelayout = new String[]{
            "...ooMooooo.....",
            "..ohhoooofffoo..",
            ".oooooMooo...oo.",
            ".ooMMMoooo..oooo",
            "...ofooohhoooo..",
            ".ofoofooooohhoo.",
            "...ooo..........",
            ".ooooo.ooohooM..",
            ".ooooo.oohooof..",
            "offfoooo.offoooo",
            "oooooooo...ooooo",
            ".ooMMMoooo......",
            "..ooooooffoooo..",
            "....ooooooooo...",
            "..ooohhoo.......",
            ".....ooooooooo..",
    };

    public AdvancedLayoutStrategy() {
        defineTilesOfWorld();
        cityTable[8][12] = new CityImpl(Player.RED);
        cityTable[4][5] = new CityImpl(Player.BLUE);
        unitTable[2][0] = new Archer(Player.RED);
    }

    private void defineTilesOfWorld() {
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = tilelayout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char tileChar = line.charAt(c);
                String type = "error";
                if (tileChar == '.') {
                    type = GameConstants.OCEANS;
                }
                if (tileChar == 'o') {
                    type = GameConstants.PLAINS;
                }
                if (tileChar == 'M') {
                    type = GameConstants.MOUNTAINS;
                }
                if (tileChar == 'f') {
                    type = GameConstants.FOREST;
                }
                if (tileChar == 'h') {
                    type = GameConstants.HILLS;
                }

                tileTable[r][c] = new TileImpl(new Position(r, c), type);

            }
        }
    }


    @Override
    public Tile[][] getTileArray() {
        return tileTable;
    }

    @Override
    public Unit[][] getUnitArray() {
        return unitTable;
    }

    @Override
    public City[][] getCityArray() {
        return cityTable;
    }
}