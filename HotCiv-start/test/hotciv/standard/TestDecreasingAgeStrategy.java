package hotciv.standard;

import hotciv.age.AgeStrategy;
import hotciv.age.DecreasingAgeStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class TestDecreasingAgeStrategy {
    private AgeStrategy AS;

    @Before
    public void setUp() {
        AS = new DecreasingAgeStrategy();
    }

    @Test
    public void shouldGiveMinus2000ForAgeMinus2100() {
        assertEquals("Age now should be -2000, comming from -2100", -2000, AS.calculateAge(-2100));
    }

    @Test
    public void shouldGive1980() {
        assertEquals("Age should be 1980", 1980, AS.calculateAge(1979));
    }
    // TODO More tests

}