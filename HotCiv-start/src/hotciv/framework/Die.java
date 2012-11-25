package hotciv.framework;
/**
 * A die class, for random number rolling.
 */

public class Die{

    private int eyes;
    private int sides;
    /**
     * constructor, uses sides as parameter.
     * The constructor uses the sides parameter, and makes the sides variable
     * the same value as sides.
     * @param sides
     * @exception IllegalArgumentException if sides is less than 1.
     */
    public Die(int sides){
        if(sides<1){
            throw new IllegalArgumentException("More than 0 please.");
        }
        this.sides = sides;
    }

    /**
     * Roll, uses Math.random() to make a new number, between 1 and sides.
     * So, it is possible to make a 6 or n-sided die.
     */
    public void roll(){
        eyes = (int)((Math.random()*sides)+1);
    }

    /**
     * getEyes returns the number from the int eyes.
     */
    public int getEyes(){
        return eyes;
    }
}
