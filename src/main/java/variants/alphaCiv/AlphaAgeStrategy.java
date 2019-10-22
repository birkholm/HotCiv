package variants.alphaCiv;

import common.AgeStrategy;

public class AlphaAgeStrategy implements AgeStrategy {

    @Override
    public int getAge(int age) {
        return age + 100;
    }
}
