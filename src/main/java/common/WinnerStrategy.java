package common;

import java.util.Map;

public interface WinnerStrategy {

    Player getWinner(int age, Map<Position, City> cityMap);
}
