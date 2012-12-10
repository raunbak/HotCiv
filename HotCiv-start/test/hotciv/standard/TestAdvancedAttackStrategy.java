package hotciv.standard;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attack.AdvancedAttackStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.framework.*;
import hotciv.gameFactory.AbstractGameFactory;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.LayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 *
 */
public class TestAdvancedAttackStrategy {
    private Game game;
    private int fixedDieRoll = 3;    // TODO make tests for expected results with fixed die rolls.

    @Before
    public void setUp() {
        game = new GameImpl(new TestFactoryStub());
    }

    @Test
    public void blueUnitAt5_5ShouldWinVsRedAttackingFrom6_6() {
        Position pFrom = new Position(6, 6);
        Position pTo = new Position(5, 5);
        game.moveUnit(pFrom, pTo);
        assertEquals("The blue unit should have survived the attack",Player.BLUE,game.getUnitAt(pTo).getOwner());
    }

    @Test
    public void redSettlerAt1_1shouldHaveEnoughSupportToWinVsBlue_1def_UnitAt0_0() {
        Position pFrom = new Position(1, 1);
        Position pTo = new Position(0, 0);
        game.moveUnit(pFrom, pTo);
        assertEquals("The red settler (1,1) should have won against blue unit with 1 def.str. (0,0).",Player.RED,game.getUnitAt(pTo).getOwner());
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

            Position p = new Position(5, 5);
            world.createUnitAt(p, Player.BLUE, GameConstants.ARCHER);
            ModifiableUnit overpoweredArcher = world.getUnitAt(p);
            overpoweredArcher.setDefensiveStrength(9001);
            p = new Position(6, 6);
            world.createUnitAt(p, Player.RED, GameConstants.LEGION);


            p = new Position(1, 1);
            world.createUnitAt(p, Player.RED, GameConstants.SETTLER);
            // Put red units in all adjacent positions, except at (0,0).
            Iterable<Position> neighborhood = Utility.get8NeighborhoodPositions(p);
            for (Position pn : neighborhood) {
                world.createUnitAt(pn, Player.RED, GameConstants.SETTLER);
            }
            p = new Position(0,0);
            world.createUnitAt(p, Player.BLUE, GameConstants.LEGION);
            ModifiableUnit uDef = world.getUnitAt(p);
            uDef.setDefensiveStrength(1);
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
            return new AdvancedAttackStrategy(new TestDieStub(fixedDieRoll));
        }

        @Override
        public WorkForceStrategy createWorkForceStrategy() {
            return null;
        }

        @Override
        public PopulationStrategy createPopulationStrategy() {
            return null;
        }
    }

    private class TestDieStub extends Die {
        private int sides;
        public TestDieStub(int sides) {
            super(sides);
            this.sides = sides;
        }

        public int roll() {
            return sides;
        }
    }
}
