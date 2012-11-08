package hotciv.standard;

import hotciv.framework.*;

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {
    private Player playerInTurn = Player.RED;
    // 2-dimensional arrays for storing the tiles/cities/units:
    private Tile[][] tileTable = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    private City[][] cityTable = new City[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    private Unit[][] unitTable = new Unit[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    private int age = -4000;   // Game starts in year 4000 BC
    private Player winner = null;

    public GameImpl() {
       SetupOfAlphaCiv();
    }

    public Tile getTileAt( Position p ) {
        return tileTable[p.getRow()][p.getColumn()];
    }
  public Unit getUnitAt( Position p ) {
      return unitTable[p.getRow()][p.getColumn()];
  }

  public City getCityAt( Position p ) {
      return cityTable[p.getRow()][p.getColumn()];
  }
  public Player getPlayerInTurn() { return playerInTurn; }
  public Player getWinner() { return winner; }
  public int getAge() { return age; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {
      if (playerInTurn.equals(Player.RED)) playerInTurn = Player.BLUE;
      else {
          playerInTurn = Player.RED;
          age += 100;
          if (age == -3000) winner = Player.RED;
          // TODO more end-of-round processing
      }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}


  private void SetupOfAlphaCiv(){
      tileTable[1][0] = new TileImpl(new Position(1,0), GameConstants.OCEANS);
      tileTable[0][1] = new TileImpl(new Position(0,1), GameConstants.HILLS);
      tileTable[2][2] = new TileImpl(new Position(2,2), GameConstants.MOUNTAINS);
      cityTable[1][1] = new CityImpl(Player.RED);
      cityTable[4][1] = new CityImpl(Player.BLUE);
      unitTable[2][0] = new UnitImpl(GameConstants.ARCHER,Player.RED);
      unitTable[4][3] = new UnitImpl(GameConstants.SETTLER,Player.RED);
      unitTable[3][2] = new UnitImpl(GameConstants.LEGION,Player.BLUE);
  }
}
