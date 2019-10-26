package variants.alphaCiv;

import common.*;

import java.util.Map;

public class AlphaMapStrategy implements MapStrategy {
    @Override
    public void generateMap(Map<Position, Tile> tileMap, Map<Position, Unit> unitMap, Map<Position, City> cityMap) {


        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {

                //place cities
                if (i == 1 && j == 1) {
                    cityMap.put(new Position(i, j), new CityImpl(Player.RED));
                } else if (i == 4 && j == 1) {
                    cityMap.put(new Position(i, j), new CityImpl(Player.BLUE));
                }

                //place Tiles
                if (i == 1 && j == 0) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.OCEANS));
                } else if (i == 0 && j == 1) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.HILLS));
                } else if (i == 2 && j == 2) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.MOUNTAINS));
                } else {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
                }
            }
        }


        //place units
        unitMap.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        unitMap.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        unitMap.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
    }

}
