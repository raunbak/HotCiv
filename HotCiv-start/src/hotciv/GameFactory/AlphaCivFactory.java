package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.attackStrategy.SimpleAttackStrategy;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.RedWinsAtAge3000BCStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.SimpleLayoutStrategy;
import hotciv.world.WorldStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 24-11-12
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivFactory implements AbstractGameFactory {
    @Override
    public AgeStrategy createAgeStrategy() {
        return new LinearAgeStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new RedWinsAtAge3000BCStrategy();
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
        return new SimpleAttackStrategy();
    }
}
