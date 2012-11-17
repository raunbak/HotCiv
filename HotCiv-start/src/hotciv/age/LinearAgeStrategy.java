package hotciv.age;

import hotciv.age.AgeStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 15-11-12
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public class LinearAgeStrategy implements AgeStrategy {
    @Override
    public int CalculateAge(int oldAge) {
        return oldAge+=100; }
}
