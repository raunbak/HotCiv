package hotciv.world;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

/**
 *
 */
public class AdvancedLayoutStrategy implements WorldStrategy {
    private HashMap<Position, TileImpl> tileMap = new HashMap<Position, TileImpl>();
    private HashMap<Position, CityImpl> cityMap = new HashMap<Position, CityImpl>();
    private HashMap<Position, UnitImpl> unitMap = new HashMap<Position, UnitImpl>();


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
        Position p = new Position(8, 12);
        cityMap.put(p, new CityImpl(Player.RED));
        p = new Position(4, 5);
        cityMap.put(p, new CityImpl(Player.BLUE));
        p = new Position(2, 0);
        unitMap.put(p, new UnitImpl(Player.RED, GameConstants.ARCHER));
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

                Position p = new Position(r, c);
                tileMap.put(p, new TileImpl(p, type));

            }
        }
    }


    @Override
    public HashMap<Position, TileImpl> getTileMap() {
        return tileMap;
    }

    @Override
    public HashMap<Position, UnitImpl> getUnitMap() {
        return unitMap;
    }

    @Override
    public HashMap<Position, CityImpl> getCityMap() {
        return cityMap;
    }
}