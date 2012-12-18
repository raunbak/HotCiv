package hotciv.standard;

import hotciv.GameFactory.DeltaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TestDeltaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new DeltaCivFactory());
    }

    @Test
    public void shouldHaveOceanAt9_8() {
        Tile t = game.getTileAt(new Position(9, 8));
        assertEquals("There should be an ocean at (9,8).", GameConstants.OCEANS, t.getTypeString());
    }

    @Test
    public void shouldHaveMountainAt2_6() {
        Tile t = game.getTileAt(new Position(2, 6));
        assertEquals("There should be a mountain at (2,6).", GameConstants.MOUNTAINS, t.getTypeString());
    }

    @Test
    public void shouldHavePlainsAt1_2() {
        Tile t = game.getTileAt(new Position(1, 2));
        assertEquals("There should be plains at (1,2).", GameConstants.PLAINS, t.getTypeString());
    }

    @Test
    public void shouldHaveForestAt4_4() {
        Tile t = game.getTileAt(new Position(4, 4));
        assertEquals("There should be a forest at (4,4).", GameConstants.FOREST, t.getTypeString());
    }

    @Test
    public void shouldHaveHillsAt7_10() {
        Tile t = game.getTileAt(new Position(7, 10));
        assertEquals("There should be hills at (7,10).", GameConstants.HILLS, t.getTypeString());
    }

}
