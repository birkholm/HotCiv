package alphaCiv;

import common.*;
import org.junit.Before;
import org.junit.Test;
import variants.alphaCiv.*;

import static org.junit.Assert.*;

/**
 * Skeleton class for alphaCiv test cases
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
public class TestAlphaCiv {
    private Game game;
    private GameConstants constants;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaWinnerStrategy(), new AlphaAgeStrategy(), new AlphaActionStrategy(), new AlphaMoveStrategy(), new AlphaMapStrategy());
        constants = new GameConstants();
    }

    @Test
    public void shouldHaveRedCityAt1_1() {
        City c = game.getCityAt(new Position(1, 1));
        assertNotNull("There should be a city at (1,1)", c);
        Player p = c.getOwner();
        assertEquals("City at (1,1) should be owned by red",
                Player.RED, p);
    }

    @Test
    public void shouldHaveRedAsFirstPlayer() {
        assertEquals(Player.RED, game.getPlayerInTurn());
    }

    @Test
    public void shouldHaveAnOceanAt1_0() {
        Tile t = game.getTileAt(new Position(1, 0));
        assertEquals(GameConstants.OCEANS, t.getTypeString());
    }

    @Test
    public void shouldHavePlainsAt0_0() {
        Tile t = game.getTileAt(new Position(0, 0));
        assertEquals(GameConstants.PLAINS, t.getTypeString());
    }

    @Test
    public void shouldBeRedArcherAt2_0() {
        Unit unit = game.getUnitAt(new Position(2, 0));
        assertEquals(GameConstants.ARCHER, unit.getTypeString());
        assertEquals(Player.RED, unit.getOwner());
    }

    @Test
    public void shouldBeOnlyOneBlueLegionAt3_2() {
        Unit unit = game.getUnitAt(new Position(3, 2));
        assertEquals(GameConstants.LEGION, unit.getTypeString());
        assertEquals(Player.BLUE, unit.getOwner());
    }

    @Test
    public void shouldBeRedSettlerAt4_3() {
        Unit unit = game.getUnitAt(new Position(4, 3));
        assertEquals(GameConstants.SETTLER, unit.getTypeString());
        assertEquals(Player.RED, unit.getOwner());
    }

    @Test
    public void shouldBePossibleToMoveAtPlain() {
        assertTrue(game.moveUnit(new Position(2, 0), new Position(3, 0)));
    }

    @Test
    public void shouldNotBePossibleToMoveOverMountains() {
        assertFalse(game.moveUnit(new Position(3, 2), new Position(2, 2)));
    }

    @Test
    public void redCannotMoveBlueUnits() {
        assertFalse(game.moveUnit(new Position(3, 2), new Position(3, 3)));
    }

    @Test
    public void citiesProduce6Production() {
        City city = game.getCityAt(new Position(1, 1));
        assertEquals(0, city.getTreasure());
        game.endOfTurn(); //red ends turn
        game.endOfTurn(); // blue ends turn, and end of round
        assertEquals(6, city.getTreasure());
        assertEquals(GameConstants.productionFocus, game.getCityAt(new Position(1, 1)).getWorkforceFocus());
    }

    @Test
    public void cityPopulationIsAlwaysOne() {
        City city = game.getCityAt(new Position(1, 1));
        assertEquals(1, city.getSize());
        city.setSize(2);
        assertEquals(1, city.getSize());
    }

    @Test
    public void blueShouldBeInTurnAfterRed() {
        assertEquals(Player.RED, game.getPlayerInTurn());
        game.endOfTurn();
        assertEquals(Player.BLUE, game.getPlayerInTurn());
    }

    @Test
    public void itShouldBeYear3900afterEndOfRound() {
        //start age = 4000
        game.endOfTurn(); //red ends turn, it should still be year 4000
        assertEquals(-4000, game.getAge());
        game.endOfTurn(); //blue ends turn, now it´s end of round so it should be year 3900
        assertEquals(-3900, game.getAge());
    }

    @Test
    public void redWinsAtAge3000() {
        //start age = -4000 = 4000BC
        assertEquals(-4000, game.getAge());
        assertNull(game.getWinner());
        game.endOfTurn(); //current 4000
        game.endOfTurn(); //3900
        game.endOfTurn(); //3900
        game.endOfTurn(); //3800
        game.endOfTurn(); //3800
        game.endOfTurn(); //3700
        game.endOfTurn(); //3700
        game.endOfTurn(); //3600
        game.endOfTurn(); //3600
        game.endOfTurn(); //3500
        game.endOfTurn(); //3500
        game.endOfTurn(); //3400
        game.endOfTurn(); //3400
        game.endOfTurn(); //3300
        game.endOfTurn(); //3300
        game.endOfTurn(); //3200
        game.endOfTurn(); //3200
        game.endOfTurn(); //3100
        game.endOfTurn(); //3100
        game.endOfTurn(); //3000
        assertEquals(Player.RED, game.getWinner());
    }

    @Test
    public void redAttackBlue() {
        //RED´s in turn, confirm Blue is at 3,2
        assertEquals(Player.BLUE, game.getUnitAt(new Position(3, 2)).getOwner());

        //red moves ( attack ) from 2,0 -> 3,2
        game.moveUnit(new Position(2, 0), new Position(3, 2));

        //there should be red at 3, 2
        assertEquals(Player.RED, game.getUnitAt(new Position(3, 2)).getOwner());

        //there shouldn´t be any red at 2,0
        //assertEquals(null, game.getUnitAt(new Position(2,0)).getOwner());

    }

    @Test
    public void shouldNotBePossibleToMoveToOwnColor() {
        assertFalse(game.moveUnit(new Position(2, 0), new Position(4, 3)));
    }

    @Test
    public void redShouldProduceLegions() {
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.LEGION);
        assertEquals(GameConstants.LEGION, game.getCityAt(new Position(1, 1)).getProduction());
    }

    @Test
    public void blueShouldProduceArchers() {
        game.changeProductionInCityAt(new Position(4, 1), GameConstants.ARCHER);
        assertEquals(GameConstants.ARCHER, game.getCityAt(new Position(4, 1)).getProduction());
    }

    @Test
    public void citiesShouldProduceUnitsAndPlaceThem() {
        //make red produce archer and place it at the city
        assertNull(game.getUnitAt(new Position(1, 1)));
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
        game.endOfTurn(); // red production = 0
        game.endOfTurn(); // red production = 6
        game.endOfTurn(); // red production = 6
        game.endOfTurn(); // red production = 12 -> it´s end of round so production should be 2, and a red archer placed on city
        assertEquals(GameConstants.ARCHER, game.getUnitAt(new Position(1, 1)).getTypeString());
        assertEquals(2, game.getCityAt(new Position(1, 1)).getTreasure());


    }

    @Test
    public void shouldPlaceUnitsCorrectly() {
        //units should be placed north of the city and then clockwise round (if possible ).
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
        game.endOfTurn(); // red production = 0
        game.endOfTurn(); // red production = 6
        game.endOfTurn(); // red production = 6
        game.endOfTurn(); // red production = 12 -> it´s end of round so production should be 2, and a red archer placed on city  }
        assertEquals(GameConstants.ARCHER, game.getUnitAt(new Position(1, 1)).getTypeString()); //first unit placed at city
        game.endOfTurn(); // red production = 2
        game.endOfTurn(); // red production = 8
        game.endOfTurn(); // red production = 8
        game.endOfTurn(); // red production = 14 -> should be placed a red archer outside of city at first available space
        assertEquals(4, game.getCityAt(new Position(1, 1)).getTreasure());
        assertEquals(GameConstants.ARCHER, game.getUnitAt(new Position(0, 2)).getTypeString());

    }
}