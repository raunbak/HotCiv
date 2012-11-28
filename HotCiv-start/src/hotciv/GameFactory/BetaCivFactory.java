package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.DecreasingAgeStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.attackStrategy.SimpleAttackStrategy;
import hotciv.framework.ExtendedGame;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinByConquestStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.SimpleLayoutStrategy;
import hotciv.world.WorldStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 24-11-12
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
public class BetaCivFactory implements AbstractGameFactory {
    @Override
    public AgeStrategy createAgeStrategy() {
        return new DecreasingAgeStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy(ExtendedGame game) {
        return new WinByConquestStrategy();
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
