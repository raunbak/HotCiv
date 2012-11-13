package hotciv.standard;

import hotciv.framework.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

 -------------------------
 Additional changes made by MR and LM
 */

public class GameImpl implements Game {
    /**
     * Fields for GameImpl.
     *
     */
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

    /**
     *
     * @param from the position that the unit has now
     * @param to the position the unit should move to
     * @return true if the unit will move. false if not allowed to move.
     */
    public boolean moveUnit( Position from, Position to ) {
        Unit unitFrom = getUnitAt(from);
        Unit unitTo = getUnitAt(to);
        Tile tileTo = getTileAt(to);

        if (!playerInTurn.equals(unitFrom.getOwner())) {return false;}

        int distanceToBeMoved = from.distanceBetween(to);
        if (distanceToBeMoved > unitFrom.getMoveCount()) {return false;}


        if (tileTo.getTypeString().equals(GameConstants.MOUNTAINS)) {return false;}

        if (unitTo != null) {
            if (unitFrom.getOwner().equals(unitTo.getOwner())) {return false;}
            // Because the attack-strategy used, the attacking unit always wins. So no need to check if a unit of different owner is at position "to".
        }

        // Now, move the unit:
        unitTable[to.getRow()][to.getColumn()] = unitFrom;
        unitTable[from.getRow()][from.getColumn()] = null;
        unitFrom.reduceMoveCountBy(distanceToBeMoved);
        return true;
    }

    /**
     *
     */
    public void endOfTurn() {
        if (playerInTurn.equals(Player.RED)) playerInTurn = Player.BLUE;
        else {
            playerInTurn = Player.RED;
            age += 100;
            if (age == -3000) { winner = Player.RED;}


            for (int i= 0; i< GameConstants.WORLDSIZE; i++) {
                for  (int j = 0; j < GameConstants.WORLDSIZE;j++) {
                 if (cityTable[i][j] != null) {

                     cityTable[i][j].addAmountTofProduction(6); // Constant amount of 6 in AlphaCiv.
                     String unittype = cityTable[i][j].getProduction();
                     int productionAmount = cityTable[i][j].getCurrentAmountOfProduction();

                     // If the city the city is currently set to produce a type of unit
                     //    and the the city has enough production amount to afford it, then create the unit.
                     if (unittype!=null && GameConstants.COSTMAP.get(unittype) <= productionAmount) {
                         // Below we need the name of our unitclass. Since we used the same name as the unittypes in GameConstants
                         //    but with capital first-letter, we can get the relevant classname at runtime using the following:
                         String className = Character.toUpperCase(unittype.charAt(0)) + unittype.substring(1);

                         // Now, we use the following code to create an instance of the relevant unit
                         //     at the right position in unitTable (again completely polymorphic).
                         try {
                             Class<?> unitClass = Class.forName("hotciv.standard."+className);
                             Constructor<?> cons = unitClass.getConstructor(Player.class);
                             Object object = cons.newInstance(getPlayerInTurn());

                             unitTable[i][j] = (Unit) object;

                         } catch (InstantiationException e) {
                             e.printStackTrace();
                         } catch (IllegalAccessException e) {
                             e.printStackTrace();
                         } catch (InvocationTargetException e) {
                             e.printStackTrace();
                         } catch (NoSuchMethodException e) {
                             e.printStackTrace();
                         } catch (ClassNotFoundException e) {
                             e.printStackTrace();
                         }

                         cityTable[i][j].reduceAmountOfProduction(GameConstants.COSTMAP.get(unittype));
                     }

                 }
                }
            }




            for (int i=0; i<GameConstants.WORLDSIZE; i++) {
                for (int j=0; j<GameConstants.WORLDSIZE; j++) {
                    if (unitTable[i][j] != null) {
                        unitTable[i][j].restoreMoveCount();
                    }
                }
            }
            // TODO more end-of-round processing
        }
    }
    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
    public void changeProductionInCityAt( Position p, String unitType ) {

        City c = getCityAt(p);

        c.setProduction(unitType);
    }
    public void performUnitActionAt( Position p ) {}

    /**
     * Setup of AlphaCiv
     * Adds the number of units and city, also a map layout required by AlphaCiv.
     */
    private void SetupOfAlphaCiv(){
        // Initialize the tile array with plains on every tile, with the responding positions.
        for (int i=0; i<GameConstants.WORLDSIZE; i++) {
            for (int j=0; j<GameConstants.WORLDSIZE; j++) {
                tileTable[i][j] = new TileImpl(new Position(i,j), GameConstants.PLAINS);
            }
        }

        tileTable[1][0] = new TileImpl(new Position(1,0), GameConstants.OCEANS);
        tileTable[0][1] = new TileImpl(new Position(0,1), GameConstants.HILLS);
        tileTable[2][2] = new TileImpl(new Position(2,2), GameConstants.MOUNTAINS);
        cityTable[1][1] = new CityImpl(Player.RED);
        cityTable[4][1] = new CityImpl(Player.BLUE);
        unitTable[2][0] = new Archer(Player.RED);
        unitTable[4][3] = new Settler(Player.RED);
        unitTable[3][2] = new Legion(Player.BLUE);
    }
}
