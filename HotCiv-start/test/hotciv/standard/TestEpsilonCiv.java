package hotciv.standard;

import hotciv.GameFactory.EpsilonCivFactory;
import hotciv.framework.Game;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TestEpsilonCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonCivFactory());
    }

    @Test
    public void dummy() {
        // TODO We tested all necessary cases in TestAdvancedAttackStrategy?
    }


}
