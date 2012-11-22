package hotciv.standard;

import hotciv.age.LinearAgeStrategy;
import hotciv.framework.*;
import hotciv.unitaction.NoActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.world.WorldStrategy;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 22-11-12
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class TestAdvancedAttackStrategy {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new LinearAgeStrategy(), new WinBy3WonAttacksStrategy(), new BattleLayoutStub(), new NoActionStrategy());

    }

    @Test
    public void redUnitAt0_0ShouldWinVsBlueAt1_1() {

    }

    private class BattleLayoutStub implements WorldStrategy {

        private Tile[][] tileTable = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        private City[][] cityTable = new City[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        private Unit[][] unitTable = new Unit[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];



        public BattleLayoutStub()  {

            // Initialize the tile array with plains on every tile, with the responding positions.
            for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                    tileTable[i][j] = new TileImpl(new Position(i, j), GameConstants.PLAINS);
                }
            }
            //tileTable[1][0] = new TileImpl(new Position(1, 0), GameConstants.OCEANS);
            //tileTable[0][1] = new TileImpl(new Position(0, 1), GameConstants.HILLS);
            //tileTable[2][2] = new TileImpl(new Position(2, 2), GameConstants.MOUNTAINS);
            //cityTable[1][1] = new CityImpl(Player.RED);
            //cityTable[4][1] = new CityImpl(Player.BLUE);

            unitTable[0][0] = new Archer(Player.RED);
            unitTable[0][1] = new Settler(Player.RED);
            unitTable[0][2] = new Legion(Player.RED);

            unitTable[1][1] = new Legion(Player.BLUE);
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
    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
