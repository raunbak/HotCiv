package hotciv.age;

/**
 *
 */
public class LinearAgeStrategy implements AgeStrategy {
    @Override
    public int calculateAge(int oldAge) {
        return oldAge+=100; }
}
