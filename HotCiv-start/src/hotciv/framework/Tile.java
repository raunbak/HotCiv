package hotciv.framework;

/**
 * Tile represents a single territory tile of a given type.
 * <p/>
 * Responsibilities:
 * 1) Know its type and position on the board.
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

public interface Tile {

    /**
     * return position of this tile on the board.
     *
     * @return position of tile.
     */
    public Position getPosition();

    /**
     * return the tile type as a string. The set of
     * valid strings are defined by the graphics
     * engine, as they correspond to named image files.
     *
     * @return the type type as string
     */
    public String getTypeString();

    /**
     * The amount of food available for gathering on this tile.
     * @return The amount of food available.
     */
    public int getFoodPerRound();

    /**
     * The amount of resources available to be produces on this tile.
     * @return The amount of resources available.
     */
    public int getResourcesPerRound();
}
