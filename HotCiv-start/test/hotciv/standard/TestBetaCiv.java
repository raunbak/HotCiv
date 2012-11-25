package hotciv.standard;

import hotciv.GameFactory.BetaCivFactory;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TestBetaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new BetaCivFactory());
    }

    @Test
    public void works() {
        assertEquals("hej", 1, 1);
    }

    @Test
    public void blueCanConquerARedCity() {
        game.endOfTurn();
        game.moveUnit(new Position(3, 2), new Position(3, 1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(3, 1), new Position(2, 1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(2, 1), new Position(1, 1));

        assertEquals("The legion should now be in position (1,1)", GameConstants.LEGION, game.getUnitAt(new Position(1, 1)).getTypeString());
        City c = game.getCityAt(new Position(1, 1));
        assertEquals("Blue should be owner of town at (1,1) now", Player.BLUE, c.getOwner());
    }

    @Test
    public void blueShouldWinWhenItConquersRedCityAt1_1() {
        game.endOfTurn();
        game.moveUnit(new Position(3, 2), new Position(3, 1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(3, 1), new Position(2, 1));
        makeGameRunNturns(1);
        game.moveUnit(new Position(2, 1), new Position(1, 1));
        makeGameRunNturns(1);

        assertEquals("The legion should now be in position (1,1)", GameConstants.LEGION, game.getUnitAt(new Position(1, 1)).getTypeString());
        assertEquals("Blue should be the winner!", Player.BLUE, game.getWinner());
    }

    @Test
    public void redShouldWinWhenItConquersBlueCityAt4_1() {
        game.moveUnit(new Position(4, 3), new Position(4, 2));
        makeGameRunNturns(1);
        game.moveUnit(new Position(4, 2), new Position(4, 1));
        makeGameRunNturns(1);

        assertEquals("Should have settler at (4,1)", GameConstants.SETTLER, game.getUnitAt(new Position(4, 1)).getTypeString());
        assertEquals("Red should be the winner!", Player.RED, game.getWinner());
    }


    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
