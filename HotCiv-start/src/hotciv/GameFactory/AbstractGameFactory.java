package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.attack.AttackStrategy;
import hotciv.framework.ExtendedGame;
import hotciv.population.PopulationStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.workforce.WorkForceStrategy;
import hotciv.world.WorldStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 24-11-12
 * Time: 12:15
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractGameFactory {

    public AgeStrategy createAgeStrategy();

    public WinnerStrategy createWinnerStrategy(ExtendedGame game);

    public WorldStrategy createWorldStrategy();

    public UnitActionStrategy createUnitActionStrategy();

    public AttackStrategy createAttackStrategy();

    public WorkForceStrategy createWorkForceStrategy();

    public PopulationStrategy createPopulationStrategy();
}
