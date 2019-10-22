package variants.betaCiv;

import common.AgeStrategy;

public class BetaAgeStrategy implements AgeStrategy {

    @Override
    public int getAge(int age) {

        if (-4000 <= age && age < -100) {
            return age + 100;
        }
        if (-100 <= age && age < -1) {
            return age + 99;
        }
        if (-1 <= age && age < 1) {
            return age + 2;
        }
        if (1 <= age && age < 50) {
            return age + 49;
        }
        if (50 <= age && age < 1750) {
            return age + 50;
        }
        if (1750 <= age && age < 1900) {
            return age + 25;
        }
        if (1900 <= age && age < 1970) {
            return age + 5;
        } else return age + 1;

    }
}
