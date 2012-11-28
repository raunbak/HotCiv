package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.attackStrategy.SimpleAttackStrategy;
import hotciv.framework.ExtendedGame;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.RedWinsAtAge3000BCStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.AdvancedLayoutStrategy;
import hotciv.world.WorldStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 24-11-12
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class DeltaCivFactory implements AbstractGameFactory {
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
        return new AdvancedLayoutStrategy();
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
