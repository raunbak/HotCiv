package hotciv.framework;
/**
 * A die class, for random number rolling.
 * Added by L&M.
 */

public class Die{

    private int sides;
    /**
     * Die-constructor
     * @param sides Number of sides on the die
     * @exception IllegalArgumentException if sides is less than 1.
     */
    public Die(int sides) {
        if(sides < 1){
            throw new IllegalArgumentException("A Die cannot have "+sides+" sides. More than 0 please.");
        }
        this.sides = sides;
    }

    /**
     * Roll the die. Generates a random number, between 1 and the number of sides.
     */
    public int roll(){
        return (int)((Math.random() * sides) + 1);
    }
}
