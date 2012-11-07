package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Skeleton class for AlphaCiv test cases

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/
public class TestAlphaCiv {
  private Game game;
  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  @Test
  public void shouldHaveRedCityAt1_1() {
    City c = game.getCityAt(new Position(1,1));
    assertNotNull("There should be a city at (1,1)", c);
    Player p = c.getOwner();
    assertEquals( "City at (1,1) should be owned by red",
      Player.RED, p );
  }

  @Test
  public void shouldHaveBlueCityAt4_1() {
      City c = game.getCityAt(new Position(4,1));
      assertNotNull("There should be a city at (4,1)", c);
      Player p = c.getOwner();
      assertEquals( "City at (4,1) should be owned by blue", Player.BLUE, p );
  }

    @Test
    public void shouldHaveExactlyRedAndBluePlayer() {
       Player p1 = game.getPlayerInTurn();
        assertEquals("Red should be the first player in turn", Player.RED, p1);
        game.endOfTurn();
        Player p2 = game.getPlayerInTurn();
        assertEquals("Blue should now be the player in turn", Player.BLUE, p2);
        game.endOfTurn();
        Player p3 = game.getPlayerInTurn();
        assertEquals("New round begins, should be red's turn", Player.RED, p3);
    }

    // TODO this is redundant as it is tested in the test above.
    @Test
    public void redShouldBeFirstPlayer() {
        Player p = game.getPlayerInTurn();
        assertEquals("Red should be the first player in turn", Player.RED, p);
    }

    @Test
    public void shouldHaveOceanAt1_0() {
        Tile t = game.getTileAt(new Position(1,0));
        assertEquals("The tile at position (1,0) should be an ocean", GameConstants.OCEANS, t.getTypeString());
    }

    @Test
    public void shouldHaveHillsAt0_1() {
        Tile t = game.getTileAt(new Position(0,1));
        assertEquals("The tile at position (0,1) should be hills", GameConstants.HILLS, t.getTypeString());
    }

    @Test
    public void redShouldWinInYear3000BC() {
        assertEquals("Game should start at 4000 BC", -4000, game.getAge());
        for (int i=0; i<20; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
        assertEquals("Age should now be 3000 BC", -3000, game.getAge());
        assertEquals("Red should have won at 3000 BC", Player.RED, game.getWinner());
    }

}