package hotciv.framework;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */
public class GameConstants {
    // The size of the world is set permanently to a 16x16 grid
    public static final int WORLDSIZE = 16;
    // Valid terrain types
    public static final String PLAINS = "plains";
    private static final int PLAINS_FOOD = 3;
    private static final int PLAINS_PRODUCTION = 0;

    public static final String OCEANS = "ocean";
    private static final int OCEANS_FOOD = 1;
    private static final int OCEANS_PRODUCTION = 0;

    public static final String FOREST = "forest";
    private static final int FOREST_FOOD = 0;
    private static final int FOREST_PRODUCTION = 3;

    public static final String HILLS = "hills";
    private static final int HILLS_FOOD = 0;
    private static final int HILLS_PRODUCTION = 2;

    public static final String MOUNTAINS = "mountain";
    private static final int MOUNTAINS_FOOD = 0;
    private static final int MOUNTAINS_PRODUCTION = 1;

    // Production map of tiles.
    public static final Map<String, Integer> PRODMAP;
    static {
        Map<String, Integer> aMap = new HashMap<String, Integer>();
        aMap.put(PLAINS, PLAINS_PRODUCTION);
        aMap.put(OCEANS, OCEANS_PRODUCTION);
        aMap.put(FOREST, FOREST_PRODUCTION);
        aMap.put(HILLS, HILLS_PRODUCTION);
        aMap.put(MOUNTAINS, MOUNTAINS_PRODUCTION);
        PRODMAP = Collections.unmodifiableMap(aMap);
    }

    // Production map of tiles.
    public static final Map<String, Integer> FOODMAP;
    static {
        Map<String, Integer> aMap = new HashMap<String, Integer>();
        aMap.put(PLAINS, PLAINS_FOOD);
        aMap.put(OCEANS, OCEANS_FOOD);
        aMap.put(FOREST, FOREST_FOOD);
        aMap.put(HILLS, HILLS_FOOD);
        aMap.put(MOUNTAINS, MOUNTAINS_FOOD);
        FOODMAP = Collections.unmodifiableMap(aMap);
    }

    // Valid production balance types
    public static final String productionFocus = "hammer";
    public static final String productionFocusDescription = "Production-focus";
    public static final String foodFocus = "apple";
    public static final String foodFocusDescription = "Food-focus";

    public static Map<String, String> BALANCE_DESCRIPTIONMAP;
    static {
        BALANCE_DESCRIPTIONMAP = new HashMap<String, String>();
        BALANCE_DESCRIPTIONMAP.put(productionFocus, productionFocusDescription);
        BALANCE_DESCRIPTIONMAP.put(foodFocus, foodFocusDescription);
        BALANCE_DESCRIPTIONMAP = Collections.unmodifiableMap(BALANCE_DESCRIPTIONMAP);
    }


    // == Valid unit types and stats ====================
    // Archer
    public static final String ARCHER = "archer";
    private static final int ARCHER_COST = 10;
    private static final int ARCHER_DEFENSIVE = 3;
    private static final int ARCHER_ATTACKING = 2;
    private static final int ARCHER_TOTAL_MOVES = 1;

    // Legion
    public static final String LEGION = "legion";
    private static final int LEGION_COST = 15;
    private static final int LEGION_DEFENSIVE = 2;
    private static final int LEGION_ATTACKING = 4;
    private static final int LEGION_TOTAL_MOVES = 1;

    // Settler
    public static final String SETTLER = "settler";
    private static final int SETTLER_COST = 30;
    private static final int SETTLER_DEFENSIVE = 3;
    private static final int SETTLER_ATTACKING = 0;
    private static final int SETTLER_TOTAL_MOVES = 1;

    // Cost map of units.
    public static final Map<String, Integer> COSTMAP;
    static {
        Map<String, Integer> aMap = new HashMap<String, Integer>();
        aMap.put(ARCHER, ARCHER_COST);
        aMap.put(LEGION, LEGION_COST);
        aMap.put(SETTLER, SETTLER_COST);
        COSTMAP = Collections.unmodifiableMap(aMap);
    }

    // Defensive strength map of units.
    public static final Map<String, Integer> DEFMAP;
    static {
        Map<String, Integer> aMap = new HashMap<String, Integer>();
        aMap.put(ARCHER, ARCHER_DEFENSIVE);
        aMap.put(LEGION, LEGION_DEFENSIVE);
        aMap.put(SETTLER, SETTLER_DEFENSIVE);
        DEFMAP = Collections.unmodifiableMap(aMap);
    }

    // Attacking strength map of units.
    public static final Map<String, Integer> ATTMAP;
    static {
        Map<String, Integer> aMap = new HashMap<String, Integer>();
        aMap.put(ARCHER, ARCHER_ATTACKING);
        aMap.put(LEGION, LEGION_ATTACKING);
        aMap.put(SETTLER, SETTLER_ATTACKING);
        ATTMAP = Collections.unmodifiableMap(aMap);
    }

    // Total moves map for units.
    public static final Map<String, Integer> MOVEMAP;
    static {
        Map<String, Integer> aMap = new HashMap<String, Integer>();
        aMap.put(ARCHER, ARCHER_TOTAL_MOVES);
        aMap.put(LEGION, LEGION_TOTAL_MOVES);
        aMap.put(SETTLER, SETTLER_TOTAL_MOVES);
        MOVEMAP = Collections.unmodifiableMap(aMap);
    }


}
