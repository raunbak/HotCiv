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

    public Tile getTileAt( Position p ) { return null; }
  public Unit getUnitAt( Position p ) { return null; }
  public City getCityAt( Position p ) { return new City() {
      @Override
      public Player getOwner() {
          return Player.RED;  //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public int getSize() {
          return 0;  //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public String getProduction() {
          return null;  //To change body of implemented methods use File | Settings | File Templates.
      }

      @Override
      public String getWorkforceFocus() {
          return null;  //To change body of implemented methods use File | Settings | File Templates.
      }
  }; }
  public Player getPlayerInTurn() { return playerInTurn; } // TODO Don't be a constant.
  public Player getWinner() { return null; }
  public int getAge() { return 0; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {
      if (playerInTurn.equals(Player.RED)) playerInTurn = Player.BLUE;
      else playerInTurn = Player.RED;
      // TODO end-of-round processing
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
}
