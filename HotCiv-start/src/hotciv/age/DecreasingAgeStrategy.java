package hotciv.age;

/**
 *
 */
public class DecreasingAgeStrategy implements AgeStrategy {
    @Override
    public int calculateAge(int oldAge) {
        if (oldAge < -100 && oldAge >= -4000) {
            return oldAge+= 100;
        }

        if (oldAge == -100) {
            return -1;
        }

        if (oldAge == -1) {
            return 1;
        }

        if (oldAge == 1){
            return 50;
        }

        if (oldAge >= 50 && oldAge<=1750) {
            return oldAge+= 50;
        }

        if (oldAge > 1750 && oldAge <=1900) {
            return oldAge+= 25;
        }

        if (oldAge >1900 && oldAge <=1970){
            return oldAge+= 5;
        }

        return oldAge += 1;
    }
}

