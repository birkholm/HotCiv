package common;

import java.util.HashMap;
import java.util.Map;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {

    Map<Position, Tile> tileMap = new HashMap<>();
    Map<Position, Unit> unitMap = new HashMap<>();
    Map<Position, City> cityMap = new HashMap<>();

    GameConstants constants = new GameConstants();

    Map<Player, Boolean> playerTurns = new HashMap<>();

    Player playerInTurn;

    int age;

    WinnerStrategy winnerStrategy;
    AgeStrategy ageStrategy;
    ActionStrategy actionStrategy;
    MoveStrategy moveStrategy;


    public GameImpl(WinnerStrategy winnerStrategy, AgeStrategy ageStrategy, ActionStrategy actionStrategy, MoveStrategy moveStrategy) {
        initializeTileMap();
        initializeUnitMap();
        this.playerInTurn = Player.RED;
        this.age = -4000;
        resetPlayerTurns();
        this.winnerStrategy = winnerStrategy;
        this.ageStrategy = ageStrategy;
        this.actionStrategy = actionStrategy;
        this.moveStrategy = moveStrategy;

    }

    private void resetPlayerTurns() {
        playerTurns.clear();
        playerTurns.put(Player.RED, Boolean.TRUE);
        playerTurns.put(Player.BLUE, Boolean.TRUE);
    }

    public Tile getTileAt(Position p) {

        return tileMap.get(p);
    }

    public Unit getUnitAt(Position p) {

        return unitMap.get(p);
    }

    public City getCityAt(Position p) {
        return cityMap.get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }


    public Player getWinner() {

        return winnerStrategy.getWinner(age, cityMap);

    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {

        //check if trying to move fortified units
        if (!moveStrategy.moveUnit(getUnitAt(from))) {
            return false;
        }

        //only move own units
        if (getPlayerInTurn() != unitMap.get(from).getOwner()) {
            return false;
        }

        //do not move over mountains or oceans
        if ((tileMap.get(to).getTypeString() == GameConstants.MOUNTAINS) || (tileMap.get(to).getTypeString() == GameConstants.OCEANS)) {
            return false;
        }

        System.out.println("before move");
        printUnits();

        //move unit
        if (getPlayerInTurn() == unitMap.get(from).getOwner()) {
            //delete unit from old position
            Unit unitFrom = unitMap.remove(from);

            if (unitMap.get(to) == null) {
                unitMap.put(to, unitFrom);
                //if there is a city, change owner
                if (cityMap.get(to) != null) {
                    cityMap.get(to).setOwner(getPlayerInTurn());
                }
            } else {
                if (unitFrom.getOwner() == unitMap.get(to).getOwner()) {
                    return false;
                } else {
                    //replace unit TO with unit FROM
                    unitMap.replace(to, unitFrom);
                    //if there is a city, change owner
                    if (cityMap.get(to) != null) {
                        cityMap.get(to).setOwner(getPlayerInTurn());
                    }
                }
            }
        }



/*    //fuck up unit
    if(Player.BLUE == playerInTurn) {
      //try to fuck him harder up
      for (Map.Entry<Position, Unit> entry: unitMap.entrySet()) {
        if (entry.getValue().getOwner() == Player.RED) {
          entry.setValue(new UnitImpl(constants.ARCHER,Player.BLUE));
        }
      }
    }
  */

        System.out.println("after move");
        printUnits();
        System.out.println("---------------");
        return true;

    }

    public void endOfTurn() {

        //end players turn
        playerTurns.replace(playerInTurn, Boolean.FALSE);

        //check if all players have ended their turn
        boolean eor = true;
        for (Map.Entry<Player, Boolean> entry : playerTurns.entrySet()) {
            if (entry.getValue()) {
                eor = false;
            }

        }

        //check if end of round should be performed
        if (eor) {
            // A) restore all units' move counts
            // B) produce food and production in all cities
            // C) produce units in all cities (if enough production)
            // D) increase population size in all cities (if enough food)
            // E) increment the world age.


            //B
            for (Map.Entry<Position, City> entry : cityMap.entrySet()) {
                entry.getValue().setTreasure(entry.getValue().getTreasure() + 6);
            }

            //C
            for (Map.Entry<Position, City> entry : cityMap.entrySet()) {
                int treasure = entry.getValue().getTreasure();
                if (entry.getValue().getProduction() == GameConstants.ARCHER) {
                    if (treasure >= 10) {
                        treasure -= 10;
                        entry.getValue().setTreasure(treasure);
                        Position position = getAvailablePosition(entry.getKey());
                        unitMap.put(position, new UnitImpl(GameConstants.ARCHER, entry.getValue().getOwner()));
                    }
                }
            }


            //E
            age = ageStrategy.getAge(age);

            //Restore players turns
            resetPlayerTurns();

        }
        //change player in turn;
        playerInTurn = playerInTurn == Player.RED ? Player.BLUE : Player.RED;
    }

    private Position getAvailablePosition(Position position) {
        //position is default the calling city´s own position
        Position positionCandidate = new Position(0, 0);
        positionCandidate.c = position.getColumn();
        positionCandidate.r = position.getRow();

        //try first, to see if there is the city´s place
        if (tryPositionCandidate(positionCandidate)) {
            return positionCandidate;
        }

        int column = position.getColumn();
        int row = position.getRow();

        if (row > 0) {

            //first try, just north of the city
            positionCandidate.r -= 1;
            if (tryPositionCandidate(positionCandidate)) {
                return positionCandidate;
            }
            //second try northeast of city
            positionCandidate.c += 1;
            if (tryPositionCandidate(positionCandidate)) {
                return positionCandidate;
            }

        } else {
            //"third" try, east of the city
            positionCandidate.c += 1;
            if (tryPositionCandidate(positionCandidate)) {
                return positionCandidate;
            }
            //fourth try, southeast
            positionCandidate.r += 1;
            if (tryPositionCandidate(positionCandidate)) {
                return positionCandidate;
            }
            //fifth try, south
            positionCandidate.c -= 1;
            if (tryPositionCandidate(positionCandidate)) {
                return positionCandidate;
            }
            //sixth try, southwest
            positionCandidate.c -= 1;
            if (tryPositionCandidate(positionCandidate)) {
                return positionCandidate;
            }
            //seventh try, west
            positionCandidate.r -= 1;
            if (tryPositionCandidate(positionCandidate)) {
                return positionCandidate;
            }
        }
        //eight try, northwest
        positionCandidate = position;
        positionCandidate.c -= 1;
        positionCandidate.r -= 1;
        if (tryPositionCandidate(positionCandidate)) {
            return positionCandidate;
        }
        //we have been around the city so hopefully this shouldn´t happen
        System.out.println("were f.....");
        return positionCandidate;
    }

    private boolean tryPositionCandidate(Position position) {

        //must not be ocean or hills
        if ((tileMap.get(position).getTypeString() != GameConstants.HILLS) && (tileMap.get(position).getTypeString() != GameConstants.OCEANS)) {
            //there must not be a unit
            return unitMap.get(position) == null;
        } else {
            return false;
        }

    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        cityMap.get(p).setProduction(unitType);
    }

    public void performUnitActionAt(Position p) {

        actionStrategy.performAction(p, unitMap, cityMap);
    }

    private void initializeTileMap() {
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
    }

    private void initializeUnitMap() {
        //place units
        unitMap.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        unitMap.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        unitMap.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
    }

    public void printMap() {
        tileMap.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public void printUnits() {
        unitMap.forEach((key, value) -> System.out.println(key + " " + value.getTypeString() + " " + value.getOwner()));
    }

}
