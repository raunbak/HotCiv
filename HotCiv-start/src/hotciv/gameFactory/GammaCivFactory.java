package hotciv.gameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.attack.SimpleAttackStrategy;
import hotciv.framework.Die;
import hotciv.framework.ExtendedGame;
import hotciv.population.PopulationStrategy;
import hotciv.population.SimplePopulationStrategy;
import hotciv.unitaction.SettlerAndArcherActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.RedWinsAtAge3000BCStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.SimpleWorkForceStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.SimpleLayoutStrategy;
import hotciv.world.WorldStrategy;

/**
 *
 */
public class GammaCivFactory implements AbstractGameFactory {
    @Override
    public AgeStrategy createAgeStrategy() {
        return new LinearAgeStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy(ExtendedGame game) {
        return new RedWinsAtAge3000BCStrategy();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new SimpleLayoutStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new SettlerAndArcherActionStrategy();
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
