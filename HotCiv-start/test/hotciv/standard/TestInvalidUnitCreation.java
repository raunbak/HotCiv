package hotciv.standard;

import hotciv.framework.InvalidTypeException;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import org.junit.Test;

import static junit.framework.Assert.assertNull;

/**
 *
 */
public class TestInvalidUnitCreation {

    @Test
    public void shouldThrowExceptionWhenTryingToCreateInvalidUnittypeUnit() {
        Unit u = null;
        try {
            u = new UnitImpl(Player.RED, "Photon Man", new Position(0,0));
        } catch (InvalidTypeException e) {
        }
        assertNull("InvalidTypeException should have been thrown, thus Unit u should still be null.", u);
    }
}
