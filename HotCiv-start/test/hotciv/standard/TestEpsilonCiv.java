package hotciv.standard;

import hotciv.age.LinearAgeStrategy;
import hotciv.framework.Game;
import hotciv.unitaction.NoActionStrategy;
import hotciv.winner.WinBy3WonAttacksStrategy;
import hotciv.world.SimpleLayoutStrategy;
import org.junit.Before;

/**
 *
 */
public class TestEpsilonCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new LinearAgeStrategy(), new WinBy3WonAttacksStrategy(), new SimpleLayoutStrategy(), new NoActionStrategy());

    }
}
