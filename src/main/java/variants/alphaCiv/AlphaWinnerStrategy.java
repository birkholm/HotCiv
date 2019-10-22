package variants.alphaCiv;

import common.City;
import common.Player;
import common.Position;
import common.WinnerStrategy;

import java.util.Map;

public class AlphaWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(int age, Map<Position, City> cityMap) {
        if (age >= -3000) {
            return Player.RED;
        }
        return null;
    }
}
