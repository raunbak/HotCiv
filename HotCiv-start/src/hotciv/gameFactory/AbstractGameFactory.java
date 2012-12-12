package hotciv.gameFactory;

import hotciv.age.AgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.framework.Die;
import hotciv.framework.ExtendedGame;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.LayoutStrategy;

/**
 * Abstract Factory pattern.
 */
public interface AbstractGameFactory {

    public AgeStrategy createAgeStrategy();

    public WinnerStrategy createWinnerStrategy(ExtendedGame game);

    public LayoutStrategy createLayoutStrategy();

    public UnitActionStrategy createUnitActionStrategy();

    public AttackStrategy createAttackStrategy(Die die);

    public WorkForceStrategy createWorkForceStrategy();

    public PopulationStrategy createPopulationStrategy();
}
