package hotciv.standard;

import hotciv.age.LinearAgeStrategy;
import hotciv.framework.Game;
import hotciv.winner.RedWinsAtAge3000BC;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 17-11-12
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public class TestDeltaCiv {
    private Game game;
    @Before
    public void setUp() {
        game = new GameImpl(new LinearAgeStrategy(),new RedWinsAtAge3000BC());
    }

    @Test

}
