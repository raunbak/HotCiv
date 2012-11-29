package hotciv.standard;

import hotciv.GameFactory.AbstractGameFactory;
import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attack.AdvancedAttackStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.framework.*;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.WorldStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;

/**
 *
 */
public class TestAdvancedAttackStrategy {
    private Game game;

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



    private class BattleLayoutStub implements WorldStrategy {
        @Override
        public void setupInitialWorld(World world) {
            // Initialize the tile array with plains on every tile, with the responding positions.
            for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
                for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                    Position p = new Position(i, j);
                    world.setTileAt(p, new TileImpl(p, GameConstants.PLAINS));
                }
            }
            //tileTable[1][0] = new TileImpl(new Position(1, 0), GameConstants.OCEANS);
            //tileTable[0][1] = new TileImpl(new Position(0, 1), GameConstants.HILLS);
            //tileTable[2][2] = new TileImpl(new Position(2, 2), GameConstants.MOUNTAINS);
            //cityTable[1][1] = new CityImpl(Player.RED);
            //cityTable[4][1] = new CityImpl(Player.BLUE);

            Position p = new Position(5, 5);
            UnitImpl overpoweredArcher = new UnitImpl(Player.BLUE, GameConstants.ARCHER);
            overpoweredArcher.setDefensiveStrength(9001);
            world.setUnitAt(p, overpoweredArcher);
            p = new Position(6, 6);
            world.setUnitAt(p, new UnitImpl(Player.RED, GameConstants.LEGION));


            p = new Position(1, 1);
            UnitImpl u = new UnitImpl(Player.RED, GameConstants.SETTLER);
            world.setUnitAt(p, u);
            // Put red units in all adjacent positions, except at (0,0).
            Iterator<Position> neighborhood = Utility.get8NeighborhoodIterator(p);
            while (neighborhood.hasNext()) {
                p = neighborhood.next();
                world.setUnitAt(p, u);
            }
            p = new Position(0,0);
            UnitImpl uDef = new UnitImpl(Player.BLUE, GameConstants.LEGION);
            uDef.setDefensiveStrength(1);
            world.setUnitAt(p, uDef);
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

        @Override
        public WorkForceStrategy createWorkForceStrategy() {
            return null;
        }

        @Override
        public PopulationStrategy createPopulationStrategy() {
            return null;
        }
    }

    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
