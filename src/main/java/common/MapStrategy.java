package common;

import java.util.Map;

public interface MapStrategy {

    void generateMap(Map<Position, Tile> tileMap, Map<Position, Unit> unitMap, Map<Position, City> cityMap);
}
