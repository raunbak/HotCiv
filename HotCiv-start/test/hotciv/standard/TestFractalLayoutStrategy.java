package hotciv.standard;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.attack.SimpleAttackStrategy;
import hotciv.framework.Die;
import hotciv.framework.ExtendedGame;
import hotciv.framework.Game;
import hotciv.gameFactory.AbstractGameFactory;
import hotciv.population.PopulationStrategy;
import hotciv.population.SimplePopulationStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.RedWinsAtAge3000BCStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.SimpleWorkForceStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.FractalLayoutStrategy;
import hotciv.world.LayoutStrategy;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the FractalLayoutStrategy using the ThirdPartyFractalGenerator.
 */
public class TestFractalLayoutStrategy {
    private Game game;
    @Before
    public void setup() {
        game = new GameImpl(new TestFractalFactory());
    }

    @Test
    public void dummy() {
        // TODO
    }


    private class TestFractalFactory implements AbstractGameFactory {
        @Override
        public AgeStrategy createAgeStrategy() {
            return new LinearAgeStrategy();
        }

        @Override
        public WinnerStrategy createWinnerStrategy(ExtendedGame game) {
            return new RedWinsAtAge3000BCStrategy();
        }

        @Override
        public LayoutStrategy createLayoutStrategy() {
            return new FractalLayoutStrategy();
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
            return new SimpleWorkForceStrategy();
        }

        @Override
        public PopulationStrategy createPopulationStrategy() {
            return new SimplePopulationStrategy();
        }
    }
}
