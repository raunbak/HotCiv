package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.attack.SimpleAttackStrategy;
import hotciv.control.ControlStrategy;
import hotciv.control.HumanControlStrategy;
import hotciv.framework.Die;
import hotciv.framework.ExtendedGame;
import hotciv.population.PopulationStrategy;
import hotciv.population.SimplePopulationStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.RedWinsAtAge3000BCStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.SimpleWorkForceStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.SimpleLayoutStrategy;
import hotciv.world.LayoutStrategy;

/**
 *
 */
public class AlphaCivFactory implements AbstractGameFactory {
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
        return new SimpleLayoutStrategy();
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

    @Override
    public ControlStrategy createControlStrategy(ExtendedGame game) {
        return new HumanControlStrategy();
    }
}
