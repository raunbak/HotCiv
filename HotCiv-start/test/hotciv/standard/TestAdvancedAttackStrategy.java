package hotciv.standard;

import hotciv.GameFactory.AbstractGameFactory;
import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attackStrategy.AdvancedAttackStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.framework.*;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.WorldStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 22-11-12
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class TestAdvancedAttackStrategy {
    private Game game;
    private AttackStrategy attStrategy;
    @Before
    public void setUp() {
        game = new GameImpl(new TestFactoryStub());
        attStrategy = new AdvancedAttackStrategy();
    }

    @Test
    public void redUnitAt0_0ShouldWinVsBlueAt1_1() {

    }

    private class BattleLayoutStub implements WorldStrategy {

        private HashMap<Position, Tile> tileMap = new HashMap<Position, Tile>();
        private HashMap<Position, City> cityMap = new HashMap<Position, City>();
        private HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();



        public BattleLayoutStub()  {

            // Initialize the tile array with plains on every tile, with the responding positions.
            for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                    Position p = new Position(i, j);
                    tileMap.put(p, new TileImpl(p, GameConstants.PLAINS));
                }
            }
            //tileTable[1][0] = new TileImpl(new Position(1, 0), GameConstants.OCEANS);
            //tileTable[0][1] = new TileImpl(new Position(0, 1), GameConstants.HILLS);
            //tileTable[2][2] = new TileImpl(new Position(2, 2), GameConstants.MOUNTAINS);
            //cityTable[1][1] = new CityImpl(Player.RED);
            //cityTable[4][1] = new CityImpl(Player.BLUE);

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
    private class TestFactoryStub implements AbstractGameFactory {

        @Override
        public AgeStrategy createAgeStrategy() {
            return new LinearAgeStrategy();
        }

        @Override
        public WinnerStrategy createWinnerStrategy() {
            return new WinBy3WonAttacksStrategy();
        }

        @Override
        public WorldStrategy createWorldStrategy() {
            return new BattleLayoutStub();
        }

        @Override
        public UnitActionStrategy createUnitActionStrategy() {
            return new NoActionStrategy();
        }

        @Override
        public AttackStrategy createAttackStrategy() {
            return new AdvancedAttackStrategy();
        }
    }
    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
