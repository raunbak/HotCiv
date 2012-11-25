package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinnerStrategy;
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

    public WinnerStrategy createWinnerStrategy();

    public WorldStrategy createWorldStrategy();

    public UnitActionStrategy createUnitActionStrategy();

    public AttackStrategy createAttackStrategy();
}