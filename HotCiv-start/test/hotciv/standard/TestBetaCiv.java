package hotciv.standard;

import hotciv.age.DecreasingAgeStrategy;
import hotciv.framework.*;

import hotciv.winner.RedWinsAtAge3000BC;
import hotciv.winner.WinByConquestStrategy;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 15-11-12
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
public class TestBetaCiv {
    private Game game;
    @Before
    public void setUp() {
        game = new GameImpl(new DecreasingAgeStrategy(),new WinByConquestStrategy());
    }
    @Test
    public void works(){
        assertEquals("hej",1,1);
    }
    @Test
    public  void blueCanConquerARedCity(){
        game.endOfTurn();
        game.moveUnit(new Position(3,2),new Position(3,1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(3,1),new Position(2,1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(2,1),new Position(1,1));

        assertEquals("a", GameConstants.LEGION, game.getUnitAt(new Position(1, 1)).getTypeString());
        City c = game.getCityAt(new Position(1,1));
        assertEquals("Blue should be owner of town at (1,1) now",Player.BLUE,c.getOwner());
    }

    @Test
    public void blueShouldWinWhenItConquersRedCityAt1_1() {
        game.endOfTurn();
        game.moveUnit(new Position(3,2),new Position(3,1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(3,1),new Position(2,1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(2,1),new Position(1,1));
        makeGameRunNturns(1);

       assertEquals("a", GameConstants.LEGION, game.getUnitAt(new Position(1, 1)).getTypeString());
       assertEquals("Blue should be the winner!",Player.BLUE,game.getWinner());
    }
    @Test
    public void redShouldWinWhenItConquersBlueCityAt4_1() {
       game.moveUnit(new Position(4,3),new Position(4,2));
       makeGameRunNturns(1);
       game.moveUnit(new Position(4, 2), new Position(4, 1));
       makeGameRunNturns(1);

       assertEquals("a", GameConstants.SETTLER, game.getUnitAt(new Position(4, 1)).getTypeString());
       assertEquals("Red should be the winner!",Player.RED,game.getWinner());
    }


    private void makeGameRunNturns(int Nturns) {
        // TODO works for two players only
        for (int i=0; i<2*Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
