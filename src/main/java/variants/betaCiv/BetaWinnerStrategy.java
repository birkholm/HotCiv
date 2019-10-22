package variants.betaCiv;

import common.City;
import common.Player;
import common.Position;
import common.WinnerStrategy;

import java.util.Map;

public class BetaWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(int age, Map<Position, City> cityMap) {

        Player owner = null;

        for (City city : cityMap.values()) {
            if (owner != null) {
                if (city.getOwner() != owner) {
                    return null;
                }
            }
            owner = city.getOwner();
        }
        return owner;
    }

}
