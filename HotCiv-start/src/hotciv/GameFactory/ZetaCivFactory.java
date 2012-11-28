package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.attackStrategy.SimpleAttackStrategy;
import hotciv.framework.ExtendedGame;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.*;
import hotciv.world.SimpleLayoutStrategy;
import hotciv.world.WorldStrategy;

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
        return new AlternatingWinStrategy(new WinByConquestStrategy(),new WinBy3WonAttacksStrategy(game));
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
