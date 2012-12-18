package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.DecreasingAgeStrategy;
import hotciv.attack.AdvancedAttackStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.control.BlueEasyCpuControlStrategy;
import hotciv.control.ControlStrategy;
import hotciv.framework.Die;
import hotciv.framework.ExtendedGame;
import hotciv.population.AdvancedPopulationStrategy;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.SettlerAndArcherActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.AlternatingWinStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinByConquestStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.AdvancedWorkForceStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.LayoutStrategy;
import hotciv.world.PopulatedFractalLayoutStrategy;

/**
 * A game-configuration with
 */
public class PlayerVsEasyCpuCivFactory implements AbstractGameFactory {
    private final int sleepMillis;

    public PlayerVsEasyCpuCivFactory(int sleepMillis) {
        this.sleepMillis = sleepMillis;
    }

    @Override
    public AgeStrategy createAgeStrategy() {
        return new DecreasingAgeStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy(ExtendedGame game) {
        return new AlternatingWinStrategy(new WinByConquestStrategy(),
                new WinBy3WonAttacksStrategy(game));
    }

    @Override
    public LayoutStrategy createLayoutStrategy() {
        return new PopulatedFractalLayoutStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new SettlerAndArcherActionStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy(Die die) {
        return new AdvancedAttackStrategy(die);
    }

    @Override
    public WorkForceStrategy createWorkForceStrategy() {
        return new AdvancedWorkForceStrategy();
    }

    @Override
    public PopulationStrategy createPopulationStrategy() {
        return new AdvancedPopulationStrategy();
    }

    @Override
    public ControlStrategy createControlStrategy() {
        return new BlueEasyCpuControlStrategy(sleepMillis);
    }
}
