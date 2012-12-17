package hotciv.standard;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.attack.SimpleAttackStrategy;
import hotciv.control.ControlStrategy;
import hotciv.control.HumanControlStrategy;
import hotciv.framework.*;
import hotciv.GameFactory.AbstractGameFactory;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.LayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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

        Position pTo =  new Position(0, 0);
        game.moveUnit(new Position(1, 0), pTo);
        assertEquals("Blue should be here", Player.BLUE, game.getUnitAt(pTo).getOwner());

        pTo = new Position(0, 1);
        game.moveUnit(new Position(1, 1), pTo);
        assertEquals("Blue should be here", Player.BLUE, game.getUnitAt(pTo).getOwner());

        assertNotSame("Blue should not be the winner yet", Player.BLUE, game.getWinner());

        pTo = new Position(0, 2);
        game.moveUnit(new Position(1, 2), pTo);
        assertEquals("Blue should be here", Player.BLUE, game.getUnitAt(pTo).getOwner());

        assertEquals("Blue should be the winner now", Player.BLUE, game.getWinner());

    }


    private class BattleLayoutStub implements LayoutStrategy {
        @Override
        public void setupInitialWorld(World world) {

            // Initialize the tile array with plains on every tile, with the responding positions.
            for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                    Position p = new Position(i, j);
                    world.createTileAt(p, GameConstants.PLAINS);
                }
            }

            Position p = new Position(0, 0);
            world.createUnitAt(p, Player.RED, GameConstants.ARCHER);
            p = new Position(0, 1);
            world.createUnitAt(p, Player.RED, GameConstants.SETTLER);
            p = new Position(0, 2);
            world.createUnitAt(p, Player.RED, GameConstants.LEGION);
            p = new Position(1, 0);
            world.createUnitAt(p, Player.BLUE, GameConstants.SETTLER);
            p = new Position(1, 1);
            world.createUnitAt(p, Player.BLUE, GameConstants.SETTLER);
            p = new Position(1, 2);
            world.createUnitAt(p, Player.BLUE, GameConstants.SETTLER);
        }
    }

    private class TestFactoryStub implements AbstractGameFactory {

        @Override
        public AgeStrategy createAgeStrategy() {
            return new LinearAgeStrategy();
        }

        @Override
        public WinnerStrategy createWinnerStrategy(ExtendedGame game) {
            return new WinBy3WonAttacksStrategy(game);
        }

        @Override
        public LayoutStrategy createLayoutStrategy() {
            return new BattleLayoutStub();
        }

        @Override
        public UnitActionStrategy createUnitActionStrategy() {
            return new NoActionStrategy();
        }

        @Override
        public AttackStrategy createAttackStrategy(Die die) {
            return new SimpleAttackStrategy();
        }

        @Override
        public WorkForceStrategy createWorkForceStrategy() {
            return null;
        }

        @Override
        public PopulationStrategy createPopulationStrategy() {
            return null;
        }

        @Override
        public ControlStrategy createControlStrategy(ExtendedGame game) {
            return new HumanControlStrategy();
        }
    }
}
