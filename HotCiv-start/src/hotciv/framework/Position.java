package hotciv.framework;

/** Position on the world map.
    
    Responsibilities:
    1) Know a specific location (row,column).

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
public class Position {

  /** create a position. 
   * @param r the row
   * @param c the column
  */
  public Position(int r, int c) { 
    this.r = r; this.c = c; 
  }

  protected int r;
  protected int c;

  /** get the row represented by this position.
   * @return the row.
   */
  public int getRow() { return r; }

  /** get the column represented by this position.
   * @return the column.
   */
  public int getColumn() { return c; }

  public int distanceTo(Position p) {
      return Math.max(Math.abs(p.getRow() - r), Math.abs(p.getColumn() - c));
  }

  public void add(Position pVector) {
      r += pVector.getRow();
      c += pVector.getColumn();
  }

  public void rotate90clockwise() {
      // if (r,c) is a vector, then (r,-c) is that vector rotated 90 degrees clockwise.
      int rOld = r;
      r = c;
      c = - rOld;
  }

  public boolean isValidWorldPosition() {
      return (0 <= r && r < GameConstants.WORLDSIZE
           && 0 <= c && c < GameConstants.WORLDSIZE);
  }

  public boolean equals(Object o) {
    if (o == null) { return false; }
    if (o.getClass() != Position.class) { return false; }
    Position other = (Position) o;
    return r==other.r && c==other.c;
  }

  @Override
  public Position clone() {
      return new Position(r,c);
  }

  public int hashCode() {
    // works ok for positions up to columns == 479
    return 479*r+c;
  }
  
  public String toString() {
    return "["+r+","+c+"]";
  }
}
