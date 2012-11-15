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
        game = new GameImpl(new LinearAgeStrategy());
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
        City c = game.getCityAt(new Position(4, 1));
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
    public void shouldHaveMountainAt2_2() {
        Tile t = game.getTileAt(new Position(0,1));
        assertEquals("The tile at position (0,1) should be hills", GameConstants.HILLS, t.getTypeString());
    }

    @Test
    public void shouldIncrementAgeBy100EachRound() {
        assertEquals("Game should start at 4000 BC", -4000, game.getAge());
        makeGameRunNturns(10);
        assertEquals("Age should now be 3000 BC", -3000, game.getAge());
    }

    @Test
    public void redShouldWinInYear3000BC() {
        assertEquals("Game should start at 4000 BC", -4000, game.getAge());
        makeGameRunNturns(10);
        assertEquals("Red should have won at 3000 BC", Player.RED, game.getWinner());
    }

    @Test
    public void redShouldHaveCorrectUnitsAtStart(){
        Unit u = game.getUnitAt(new Position(2,0));
        assertEquals("Should have an archer at (2,0)",GameConstants.ARCHER,u.getTypeString());
        assertEquals("Archer at (2,0) should have Red as owner",Player.RED,u.getOwner());

        u = game.getUnitAt(new Position(4,3));
        assertEquals("Should have an Settler at (4,3)",GameConstants.SETTLER,u.getTypeString());
        assertEquals("Settler at (4,3) should have Red as owner",Player.RED,u.getOwner());

    }

    @Test
    public void blueShouldHaveCorrectUnitAtStart(){
        Unit u = game.getUnitAt(new Position(3,2));
        assertEquals("Should have an legion at (3,2)",GameConstants.LEGION,u.getTypeString());
        assertEquals("Legion at (3,2) should have Blue as owner",Player.BLUE,u.getOwner());

    }

    @Test
    public void archerShouldBeAbleToMoveFrom2_0To3_1() {
        Unit archer = game.getUnitAt(new Position(2,0));
        assertNotNull("There should be an archer at (2,0)", archer);
        game.moveUnit(new Position(2,0), new Position(3,1));
        assertEquals("This archer should now be at position (3,1)", archer, game.getUnitAt(new Position(3,1)));
    }

    @Test
    public void unitShouldNotBeAbleToMoveToATileWithAMountain() {
        game.moveUnit(new Position(3,2), new Position(2,2));
        assertNull("There should not be any units on a mountain-tile", game.getUnitAt(new Position(2,2)));
    }

    @Test
    public void redCannotMoveBlueUnits() {
        game.moveUnit(new Position(3,2), new Position(4,2));
        assertNull("If not null, then red could move blues units", game.getUnitAt(new Position(4,2)));
    }

    @Test
    public void unitsCannotMoveFurtherThanRemainingMoveCount() {
        game.moveUnit(new Position(4,3), new Position(4,5));
        assertNull("This settler should not be able to move here. (distance > 1)", game.getUnitAt(new Position(4,5)));
    }

    @Test
    public void unitsCannotMoveWhenAtZeroMoveCount() {
        game.moveUnit(new Position(4,3), new Position(4,4));
        game.moveUnit(new Position(4,4), new Position(4,5));
        assertNull("This settler should not be able to move here. (no moves left)", game.getUnitAt(new Position(4,5)));
    }

    @Test
    public void thereShouldBeOnlyOneUnitOnATile() {
        game.moveUnit(new Position(2,0), new Position(3,1));  // Move archer
        game.moveUnit(new Position(4,3), new Position(4,2));  // Move settler
        game.endOfTurn();  // End the turn, because red has no moves left.
        game.endOfTurn();  // End the turn again, so it becomes red's turn again.
        game.moveUnit(new Position(3,1), new Position(4,2));  // Move archer to settler's position.
        assertNotNull("Archer should still be at (3,1) because it can't move to settlers position", game.getUnitAt(new Position(3,1)));
    }

    @Test
    public void movingRedUnitToBlueUnitOverwritesTheBlueUnit() {
        game.moveUnit(new Position(4,3), new Position(3,2));
        assertEquals("The red settler should now be in the legion's position", Player.RED, game.getUnitAt(new Position(3,2)).getOwner());
    }

    @Test
    public void RedCityAt1_1_ShouldProduce6ProductionEachTurn() {
        makeGameRunNturns(1); // To make sure a full turn has passed.
        City c = game.getCityAt(new Position(1,1));
        assertEquals("Since a turn has passed, this City should have 6 production",6,c.getCurrentAmountOfProduction());
    }

    @Test
    public void ShouldLowerProductionAmountInRed1_1_CityFrom30to10(){
        makeGameRunNturns(5);
        City c = game.getCityAt(new Position(1,1));
        c.reduceAmountOfProduction(20);
        assertEquals("Should be 10 production in this city now",10,c.getCurrentAmountOfProduction());

    }

    @Test
    public void canChangeProductionInRedCity1_1() {
        City c = game.getCityAt(new Position(1,1));
        assertNull("This city should not have a production focus at this point",c.getProduction());
        game.changeProductionInCityAt(new Position(1,1),GameConstants.ARCHER);
        assertEquals("Production focus should be Archer in this city",GameConstants.ARCHER,c.getProduction());
    }

    @Test
    public void blueCityAt4_1_WillProduceALegionIn3Turns(){
        game.changeProductionInCityAt(new Position(4,1),GameConstants.LEGION);
        makeGameRunNturns(3);
        Unit u = game.getUnitAt(new Position(4,1));
        assertEquals("There should be a Legion unit here",GameConstants.LEGION,u.getTypeString());
    }

    @Test
    public void shouldPlaceUnitAtNextFreeTileIfCityIsOccupied() {
        game.changeProductionInCityAt(new Position(1,1),GameConstants.ARCHER);
        // Run game until red city has produced enough to make 2 archers.
        makeGameRunNturns((int)Math.ceil(2 * GameConstants.ARCHERCOST / 6.0));
        Unit u = game.getUnitAt(new Position(0,1));
        assertEquals("There should now be an archer at (0,1)",GameConstants.ARCHER,u.getTypeString());
    }

    @Test
    public void shouldSkipOccupiedTileWhenPlacingUnit() {
        game.changeProductionInCityAt(new Position(4,1), GameConstants.SETTLER);
        // Run the game until the blue city tries to produce a third settler.
        makeGameRunNturns((int)Math.ceil(3 * GameConstants.SETTLERCOST / 6.0));
        Unit uLegion = game.getUnitAt(new Position(3,2));
        assertEquals("The legion should still be there (3,2).", GameConstants.LEGION, uLegion.getTypeString());
        Unit uSettler3 = game.getUnitAt(new Position(4,2));
        assertEquals("The third settler should have been placed here (4,2).", GameConstants.SETTLER, uSettler3.getTypeString());

    }

    @Test
    public void startByPlacingTheUnit2TilesToTheNorthWhenNoFreeTilesInAOneTileRadius() {
        game.changeProductionInCityAt(new Position(4,1), GameConstants.ARCHER);
        // Run the game until the blue city has produced 9 archers.
        makeGameRunNturns((int)Math.ceil(9 * GameConstants.ARCHERCOST / 6.0));
        Unit u = game.getUnitAt(new Position(2,1));
        assertEquals("The 9'th archer should be placed at (2,1).", GameConstants.ARCHER, u.getTypeString());
    }

    @Test
    public void ThereShouldBeAUnitInCity1_1andOneAbove(){
        makeGameRunNturns(3);
        game.changeProductionInCityAt(new Position(1,1),GameConstants.ARCHER);

        makeGameRunNturns(1);
        Unit u1 = game.getUnitAt(new Position(1,1));
        Unit u2 = game.getUnitAt(new Position(0,1));
        assertEquals("There should be an archer here!(1,1)",GameConstants.ARCHER,u1.getTypeString());
        assertEquals("There should be an archer here!(0,1)",GameConstants.ARCHER,u2.getTypeString());

    }


    private void makeGameRunNturns(int Nturns) {
        // TODO works for two players only
        for (int i=0; i<2*Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}