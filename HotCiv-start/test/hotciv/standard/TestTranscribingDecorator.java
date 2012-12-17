package hotciv.standard;

import hotciv.framework.*;
import hotciv.GameFactory.SemiCivFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Just for using the ExtendedGameDecorator... to see it and to get rid of "never used"-warnings
 */
public class TestTranscribingDecorator {
    private ExtendedGameDecorator gameDec;

    @Before
    public void setup() {
        gameDec = new ExtendedGameDecorator(new GameImpl(new SemiCivFactory()));
    }

    @Test  // Not meant to be a TDD test, just a way to see the transcribing of a game.
    public void dummy() {
        Position from = new Position(2,1);
        Position to = new Position(2,2);
        gameDec.moveUnit(from, to);  // Red moves red archer
        from = new Position(2,2);
        to = new Position(2,3);
        gameDec.moveUnit(from, to); // (zero moves left) should not be happen / be transcribed

        gameDec.changeProductionInCityAt(new Position(8,12), GameConstants.LEGION);
        gameDec.endOfTurn();  // red ends turn.

        gameDec.changeWorkForceFocusInCityAt(new Position(4,5), GameConstants.productionFocus);
        gameDec.endOfTurn();  // blue ends turn.
    }
}
