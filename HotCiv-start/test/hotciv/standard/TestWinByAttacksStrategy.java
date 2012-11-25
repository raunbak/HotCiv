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

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TestWinByAttacksStrategy {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new TestFactoryStub());

    }

    @Test
    public void blueShouldWinAfter3Att() {
        game.endOfTurn();
        game.moveUnit(new Position(1, 1), new Position(0, 0));
        assertEquals("Blue should be here", Player.BLUE, Player.BLUE);
        makeGameRunNturns(1);
        game.moveUnit(new Position(0, 0), new Position(0, 1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(0, 1), new Position(0, 2));

        assertEquals("Blue should be the winner now", Player.BLUE, game.getWinner());

    }


    private class BattleLayoutStub implements WorldStrategy {

        private HashMap<Position, Tile> tileMap = new HashMap<Position, Tile>();
        private HashMap<Position, City> cityMap = new HashMap<Position, City>();
        private HashMap<Position, Unit> unitMap = new HashMap<Position, Unit>();
        private MutatorKey mutatorKey;


        public BattleLayoutStub() {
            // Generate the key to use for mutating cities and units.
            mutatorKey = new MutatorKey();

            // Initialize the tile array with plains on every tile, with the responding positions.
            for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                    Position p = new Position(i, j);
                    tileMap.put(p, new TileImpl(p, GameConstants.PLAINS));
                }
            }

            Position p = new Position(0, 0);
            unitMap.put(p, new UnitImpl(Player.RED, GameConstants.ARCHER, mutatorKey));
            p = new Position(0, 1);
            unitMap.put(p, new UnitImpl(Player.RED, GameConstants.SETTLER, mutatorKey));
            p = new Position(0, 2);
            unitMap.put(p, new UnitImpl(Player.RED, GameConstants.LEGION, mutatorKey));
            p = new Position(1, 1);
            unitMap.put(p, new UnitImpl(Player.BLUE, GameConstants.LEGION, mutatorKey));
        }

        @Override
        public HashMap<Position, Tile> getTileMap() {
            return tileMap;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public HashMap<Position, Unit> getUnitMap() {
            return unitMap;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public HashMap<Position, City> getCityMap() {
            return cityMap;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public MutatorKey getMutatorKey() {
            return mutatorKey;
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