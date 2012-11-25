package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attackStrategy.AdvancedAttackStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.SimpleLayoutStrategy;
import hotciv.world.WorldStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 24-11-12
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class EpsilonCivFactory implements AbstractGameFactory {
    @Override
    public AgeStrategy createAgeStrategy() {
        return new LinearAgeStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new WinBy3WonAttacksStrategy();
    }

    @Override
    public WorldStrategy createWorldStrategy() {
        return new SimpleLayoutStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new NoActionStrategy();
    }

    @Override
    public AttackStrategy createAttackStrategy() {
        return new AdvancedAttackStrategy();
    }
}
