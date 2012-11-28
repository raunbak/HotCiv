package hotciv.standard;

import hotciv.framework.InvalidUnittypeException;
import hotciv.framework.Player;
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
            u = new UnitImpl(Player.RED, "Photon Man");
        } catch (InvalidUnittypeException e) {
        }
        assertNull("InvalidUnittypeException should have been thrown, thus Unit u should still be null.", u);
    }
}
