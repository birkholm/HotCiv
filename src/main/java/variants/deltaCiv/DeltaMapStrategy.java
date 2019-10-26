package variants.deltaCiv;

import common.*;

import java.util.Map;

public class DeltaMapStrategy implements MapStrategy {

    /*
    // City
    RED CITY                    90
    BLUE CITY                   91

    // Valid unit types
    RED ARCHER = "archer";      20
    BLUE ARCHER = "archer";     21

    RED LEGION = "legion";      30
    BLUE LEGION = "legion";     31

    RED SETTLER = "settler";    40
    BLUE SETTLER = "settler";   41

    // Valid terrain types
    PLAINS = "plains";          10
    OCEANS = "ocean";           11
    FOREST = "forest";          12
    HILLS = "hills";            13
    MOUNTAINS = "mountain";     14

     */
    private int[][] mapArray = {
            {11, 11, 11, 10, 10, 14, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11},
            {11, 11, 10, 13, 13, 10, 10, 10, 10, 12, 12, 12, 10, 10, 11, 11},
            {11, 10, 10, 10, 10, 10, 14, 10, 10, 10, 11, 11, 11, 10, 10, 11},
            {11, 10, 10, 14, 14, 14, 10, 10, 10, 10, 11, 11, 10, 10, 10, 10},
            {11, 11, 11, 10, 10, 91, 10, 10, 13, 13, 10, 10, 10, 10, 11, 11},
            {11, 10, 12, 10, 10, 10, 10, 10, 10, 10, 10, 13, 13, 10, 10, 11},
            {11, 11, 11, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11},
            {11, 10, 10, 10, 10, 10, 10, 11, 10, 10, 13, 10, 10, 14, 11, 11},
            {11, 10, 10, 10, 10, 10, 10, 11, 10, 13, 10, 10, 90, 12, 11, 11},
            {10, 12, 12, 12, 10, 10, 10, 10, 11, 10, 12, 12, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 10, 10, 10, 10, 10},
            {11, 10, 10, 14, 14, 14, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11},
            {11, 11, 10, 10, 10, 10, 10, 10, 12, 12, 10, 10, 10, 10, 11, 11},
            {11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11},
            {11, 11, 10, 10, 10, 13, 13, 10, 10, 11, 11, 11, 11, 11, 11, 11},
            {11, 11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11}
    };

    @Override
    public void generateMap(Map<Position, Tile> tileMap, Map<Position, Unit> unitMap, Map<Position, City> cityMap) {

        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {


                if (mapArray[i][j] == 10) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
                } else if (mapArray[i][j] == 11) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.OCEANS));
                } else if (mapArray[i][j] == 12) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.FOREST));
                } else if (mapArray[i][j] == 13) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.HILLS));
                } else if (mapArray[i][j] == 14) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.MOUNTAINS));
                } else if (mapArray[i][j] == 90) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
                    cityMap.put(new Position(i, j), new CityImpl(Player.RED));
                } else if (mapArray[i][j] == 91) {
                    tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
                    cityMap.put(new Position(i, j), new CityImpl(Player.BLUE));
                }

                /*
                //place cities
                if (i == 8 && j == 12) {
                    cityMap.put(new Position(i, j), new CityImpl(Player.RED));
                } else if (i == 4 && j == 5) {
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

                 */
            }
        }

        /*
        //place units
        unitMap.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        unitMap.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        unitMap.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));

         */

    }
}
