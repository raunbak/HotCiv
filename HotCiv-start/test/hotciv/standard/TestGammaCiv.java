package hotciv.standard;

import hotciv.age.DecreasingAgeStrategy;
import hotciv.age.LinearAgeStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.winner.RedWinsAtAge3000BC;
import hotciv.winner.WinByConquestStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 17-11-12
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class TestGammaCiv {
    private Game game;
    @Before
    public void setUp() {
        game = new GameImpl(new LinearAgeStrategy(),new RedWinsAtAge3000BC());
    }

    @Test
    public void archerAt2_0ShouldHaveIncreasedDefStrength(){
        assertEquals("Should have def of 3",3,game.getUnitAt(new Position(2,0)).getDefensiveStrength());
        game.performUnitActionAt(new Position(2,0));
        assertEquals("Should have def of 6",6,game.getUnitAt(new Position(2,0)).getDefensiveStrength());

    }
    @Test
    public void archerAt2_0ShouldHaveNormalDefStrength() {
        game.performUnitActionAt(new Position(2,0));
        game.performUnitActionAt(new Position(2,0));
        assertEquals("Should have def of 3",3,game.getUnitAt(new Position(2,0)).getDefensiveStrength());
    }
    @Test
    public void archerAt2_0ShouldNotBeAbleToMove() {
        game.performUnitActionAt(new Position(2,0));
        makeGameRunNturns(1);
        assertNotNull("There should be an unit here",game.getUnitAt(new Position(2,0)));
        game.moveUnit(new Position(2,0),new Position(2,1));
        assertNull("No unit should be here",game.getUnitAt(new Position(2,1)));
    }

    @Test
    public void thereShouldBeACityAt4_3() {
        game.performUnitActionAt(new Position(4,3));
        assertNotNull("There should be a city here (4,3)",game.getCityAt(new Position(4,3)));

    }

    @Test
    public void thereShouldNotBeASettlerAt4_3AfterItHasMadeACity() {
        game.performUnitActionAt(new Position(4,3));
        assertNull("No Settler should be here, since it has made a city",game.getUnitAt(new Position(4,3)));
    }

    private void makeGameRunNturns(int Nturns) {
        // TODO works for two players only
        for (int i=0; i<2*Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
