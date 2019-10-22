package common;

import java.util.Map;

public interface ActionStrategy {

    void performAction(Position p, Map<Position, Unit> unitMap, Map<Position, City> cityMap);
}
