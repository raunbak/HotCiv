package hotciv.GameFactory;

import hotciv.age.AgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.attackStrategy.AdvancedAttackStrategy;
import hotciv.attackStrategy.AttackStrategy;
import hotciv.framework.ExtendedGame;
import hotciv.unitaction.NoActionStrategy;
import hotciv.unitaction.UnitActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.winner.WinnerStrategy;
import hotciv.world.SimpleLayoutStrategy;
import hotciv.world.WorldStrategy;

/**
 *
 */
public class EpsilonCivFactory implements AbstractGameFactory {
    @Override
    public AgeStrategy createAgeStrategy() {
        return new LinearAgeStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy(ExtendedGame game) {
        return new WinBy3WonAttacksStrategy(game);
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
