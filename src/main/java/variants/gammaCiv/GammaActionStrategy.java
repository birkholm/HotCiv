package variants.gammaCiv;

import common.*;

import java.util.Map;

public class GammaActionStrategy implements ActionStrategy {

    GameConstants constants = new GameConstants();

    @Override
    public void performAction(Position p, Map<Position, Unit> unitMap, Map<Position, City> cityMap) {

        if (unitMap.get(p).getTypeString() == GameConstants.SETTLER) {
            //make a new city at the position
            City city = new CityImpl(unitMap.get(p).getOwner());
            city.setSize(1);
            cityMap.put(p, city);
            //remove the unit from the current position
            unitMap.remove(p);

        } else if (unitMap.get(p).getTypeString() == GameConstants.ARCHER) {

            int defensiveStrength = unitMap.get(p).getDefensiveStrength();
            //if already fortified, then undo
            if (unitMap.get(p).isFortify()) {
                defensiveStrength /= 2;
                unitMap.get(p).setDefensiveStrength(defensiveStrength);
                unitMap.get(p).setFortify(false);
            } else {
                //double the archers current defensive strength
                defensiveStrength *= 2;
                unitMap.get(p).setDefensiveStrength(defensiveStrength);
                unitMap.get(p).setFortify(true);
            }
        }

    }
}
