package hotciv.standard;

import hotciv.framework.Game;
import hotciv.gameFactory.ZetaCivFactory;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class TestZetaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new ZetaCivFactory());
    }

    @Test
    public void dummy() {
        // TODO Add test cases for ZetaCiv, or test AlternatingWinStrategy in a test stub...
    }
}
