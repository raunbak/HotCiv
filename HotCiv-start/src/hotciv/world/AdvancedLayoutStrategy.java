package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.Archer;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;

import java.util.HashMap;

/**
 *
 */
public class AdvancedLayoutStrategy implements WorldStrategy {
    private HashMap<Position, Tile> tileMap = new HashMap<Position, Tile>();
    private HashMap<Position, City> cityMap = new HashMap<Position, City>();
    private HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();


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
        Position p = new Position(8,12);
        cityMap.put(p, new CityImpl(Player.RED));
        p = new Position(4,5);
        cityMap.put(p, new CityImpl(Player.BLUE));
        p = new Position(2,0);
        unitMap.put(p, new Archer(Player.RED));
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

                Position p = new Position(r,c);
                tileMap.put(p, new TileImpl(p, type));

            }
        }
    }


    @Override
    public HashMap<Position, Tile> getTileArray() {
        return tileMap;
    }

    @Override
    public HashMap<Position, Unit> getUnitArray() {
        return unitMap;
    }

    @Override
    public HashMap<Position, City> getCityArray() {
        return cityMap;
    }
}