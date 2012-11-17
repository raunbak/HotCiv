package hotciv.standard;

import com.sun.xml.internal.bind.v2.TODO;

import hotciv.age.AgeStrategy;
import hotciv.age.DecreasingAgeStrategy;
import hotciv.framework.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 15-11-12
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public class TestDecreasingAgeStrategy {
        private AgeStrategy AS;
        @Before
        public void setUp() {
            AS = new DecreasingAgeStrategy();
        }

    @Test
    public void shouldGiveMinus2000ForAgeMinus2100() {
        assertEquals("Age now should be -2000, comming from -2100",-2000,AS.CalculateAge(-2100));
    }

    @Test
    public void shouldGive1980(){
        assertEquals("Age should be 1980",1980,AS.CalculateAge(1979));
    }
    // TODO More tests

    }