package hotciv.gameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.attack.SimpleAttackStrategy;
import hotciv.framework.Die;
import hotciv.framework.ExtendedGame;
import hotciv.population.PopulationStrategy;
import hotciv.population.SimplePopulationStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.AlternatingWinStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinByConquestStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.SimpleWorkForceStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.LayoutStrategy;
import hotciv.world.SimpleLayoutStrategy;

/**
 *
 */
public class ZetaCivFactory implements AbstractGameFactory {
    @Override
    public AgeStrategy createAgeStrategy() {
        return new LinearAgeStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy(ExtendedGame game) {
        return new AlternatingWinStrategy(new WinByConquestStrategy(), new WinBy3WonAttacksStrategy(game));
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
}
