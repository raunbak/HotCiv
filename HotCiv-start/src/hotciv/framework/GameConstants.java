package hotciv.framework;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.

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
public class GameConstants {
  // The size of the world is set permanently to a 16x16 grid 
  public static final int WORLDSIZE = 16;
  // Valid unit types
  public static final String ARCHER    = "Archer";
  public static final String LEGION    = "Legion";
  public static final String SETTLER   = "Settler";
  // Valid terrain types
  public static final String PLAINS    = "plains";
  public static final String OCEANS    = "ocean";
  public static final String FOREST    = "forest";
  public static final String HILLS     = "hills";
  public static final String MOUNTAINS = "mountain";
  // Valid production balance types
  public static final String productionFocus = "hammer";
  public static final String foodFocus = "apple";
  // Valid costs of units
  public static final int ARCHERCOST = 10;
  public static final int LEGIONCOST = 15;
  public static final int SETTLERCOST = 30;

  // Cost map of units.
  public static final Map<String,Integer> COSTMAP;
    static {
        Map<String,Integer> aMap = new HashMap<String, Integer>();
        aMap.put(ARCHER,ARCHERCOST);
        aMap.put(LEGION,LEGIONCOST);
        aMap.put(SETTLER,SETTLERCOST);
        COSTMAP = Collections.unmodifiableMap(aMap);
    }
}
