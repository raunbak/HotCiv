package hotciv.standard;

import hotciv.framework.*;
import hotciv.gameFactory.AlphaCivFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for testing the subject behaviour of GameImpl in the observer-pattern used for notifying GameObservers.
 */
public class TestGameImplSubjectBehaviour {
    Game game;
    GameObserverStub obs1, obs2;

    @Before
    public void setup() {
        game = new GameImpl(new AlphaCivFactory());
        obs1 = new GameObserverStub();
        obs2 = new GameObserverStub();
        game.addObserver(obs1);
        game.addObserver(obs2);
    }

    @Test
    public void allObserversShouldBeNotified() {
        Position p = new Position(6,7);
        game.setTileFocus(p);

        assertTrue("Both observers should have been notified on the tile focus change",
                p.equals(obs1.tileFocus) && p.equals(obs2.tileFocus));
    }

    @Test
    public void shouldNotifyWhenTurnEnds() {
        game.endOfTurn();
        assertTrue("Observer should have been notified when red ends turn",
                Player.BLUE.equals(obs2.nextPlayer) && game.getAge() == obs2.age);

        game.endOfTurn();
        assertTrue("Observer should have been notified when blue ends turn",
                Player.RED.equals(obs2.nextPlayer) && game.getAge() == obs2.age);
    }

    @Test
    public void shouldNotifyOnMoveUnit() {
        Position from = new Position(2, 0);
        Position to = new Position(3, 1);
        game.moveUnit(from, to);
        Position fromObs = obs1.worldChanges.get(0);
        Position toObs = obs1.worldChanges.get(1);

        assertTrue("Observer should now have gotten notifications on worldchanges" +
                "at from and to positions.", from.equals(fromObs) && to.equals(toObs));
    }

    @Test
    public void shouldNotifyOnPerformUnitAction() {
        Position p = new Position(2, 0);
        game.performUnitActionAt(p);
        Position pObs = obs1.worldChanges.get(0);

        assertEquals("Observer should have been notified about performUnitAction-worldchange.", p, pObs);
    }

    @Test
    public void shouldNotifyOnNewUnitsProduced() {
        Position p0 = new Position(1, 1);
        Position p1 = new Position(0, 1);
        makeGameRunNturns(3);
        game.changeProductionInCityAt(p0, GameConstants.ARCHER);
        makeGameRunNturns(1);
        Position p0obs = obs1.worldChanges.get(0);
        Position p1obs = obs1.worldChanges.get(1);
        assertEquals("Observer should be notified on produced unit in (1,1)", p0, p0obs);
        assertEquals("Observer should be notified on produced unit in (0,1)", p1, p1obs);
    }


    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }


    private class GameObserverStub implements GameObserver {
        List<Position> worldChanges;
        Player nextPlayer;
        int age;
        Position tileFocus;

        public GameObserverStub() {
            worldChanges = new ArrayList<Position>();
        }

        @Override
        public void worldChangedAt(Position pos) {
            worldChanges.add(pos);
        }

        @Override
        public void turnEnds(Player nextPlayer, int age) {
            this.nextPlayer = nextPlayer;
            this.age = age;
        }

        @Override
        public void tileFocusChangedAt(Position position) {
            tileFocus = position;
        }
    }
}
