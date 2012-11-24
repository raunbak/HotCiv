package hotciv.standard;

import hotciv.age.LinearAgeStrategy;
import hotciv.framework.*;
import hotciv.unitaction.NoActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.world.WorldStrategy;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TestWinByAttacksStrategy {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new LinearAgeStrategy(), new WinBy3WonAttacksStrategy(), new BattleLayoutStub(), new NoActionStrategy());

    }

    @Test
    public void blueShouldWinAfter3Att() {
        game.endOfTurn();
        game.moveUnit(new Position(1,1),new Position(0,0));
        assertEquals("Blue should be here",Player.BLUE,Player.BLUE);
        makeGameRunNturns(1);
        game.moveUnit(new Position(0,0),new Position(0,1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(0,1),new Position(0,2));

        assertEquals("Blue should be the winner now",Player.BLUE,game.getWinner());

    }


    private class BattleLayoutStub implements WorldStrategy {

        private HashMap<Position, Tile> tileMap = new HashMap<Position, Tile>();
        private HashMap<Position, City> cityMap = new HashMap<Position, City>();
        private HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();


        public BattleLayoutStub() {
            // Initialize the tile array with plains on every tile, with the responding positions.
            for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                    Position p = new Position(i, j);
                    tileMap.put(p, new TileImpl(p, GameConstants.PLAINS));
                }
            }

            Position p = new Position(0, 0);
            unitMap.put(p, new Archer(Player.RED));
            p = new Position(0,1);
            unitMap.put(p, new Settler(Player.RED));
            p = new Position(0,2);
            unitMap.put(p, new Legion(Player.RED));
            p = new Position(1,1);
            unitMap.put(p, new Legion(Player.BLUE));
        }

        @Override
        public HashMap<Position, Tile> getTileArray() {
            return tileMap;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public HashMap<Position, Unit> getUnitArray() {
            return unitMap;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public HashMap<Position, City> getCityArray() {
            return cityMap;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
