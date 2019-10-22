package betaCiv;

import common.*;
import org.junit.Before;
import org.junit.Test;
import variants.alphaCiv.AlphaActionStrategy;
import variants.alphaCiv.AlphaMoveStrategy;
import variants.betaCiv.BetaAgeStrategy;
import variants.betaCiv.BetaWinnerStrategy;

import static org.junit.Assert.assertEquals;

public class TestBetaCiv {
    private Game game;
    private GameConstants constants;

    /**
     * Fixture for betaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaWinnerStrategy(), new BetaAgeStrategy(), new AlphaActionStrategy(), new AlphaMoveStrategy());
        constants = new GameConstants();
    }

    @Test
    public void shouldHaveBlueAsWinner() {

        assertEquals(null, game.getWinner());
        game.endOfTurn();//red ends turn
        //blue attacks red´s city and wins the game
        game.moveUnit(new Position(3, 2), new Position(1, 1));
        assertEquals(Player.BLUE, game.getCityAt(new Position(1, 1)).getOwner());
        assertEquals(Player.BLUE, game.getWinner());
    }

    @Test
    public void shouldGiveAge3000() {

        assertEquals(-4000, game.getAge());
        // 2 players * 10 end of turns = 20
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();

        }

        assertEquals(-3000, game.getAge());
    }

    @Test
    public void shouldVerifyChristAge() {

        //38
        for (int i = 0; i < 78; i++) {
            game.endOfTurn();
        }
        assertEquals(-100, game.getAge());

        game.endOfTurn();
        game.endOfTurn();
        assertEquals(-1, game.getAge());

        game.endOfTurn();
        game.endOfTurn();
        assertEquals(1, game.getAge());

        game.endOfTurn();
        game.endOfTurn();
        assertEquals(50, game.getAge());
    }

    @Test
    public void shouldVerifyChristTo1750() {
        shouldVerifyChristAge();

        game.endOfTurn();
        game.endOfTurn();
        //100AD
        assertEquals(100, game.getAge());
        for (int i = 0; i < 66; i++) {
            game.endOfTurn();
        }
        assertEquals(1750, game.getAge());

    }

    @Test
    public void shouldVerify1750To1900() {
        shouldVerifyChristTo1750();

        game.endOfTurn();
        game.endOfTurn();
        //1775
        assertEquals(1775, game.getAge());
        for (int i = 0; i < 10; i++) {
            game.endOfTurn();

        }
        assertEquals(1900, game.getAge());

    }

    @Test
    public void shouldVerify1900To1970() {
        shouldVerify1750To1900();

        game.endOfTurn();
        game.endOfTurn();
        //1905
        assertEquals(1905, game.getAge());
        for (int i = 0; i < 26; i++) {
            game.endOfTurn();
        }
        assertEquals(1970, game.getAge());

    }

    @Test
    public void shoulVerifyAfter1970() {
        shouldVerify1900To1970();
        game.endOfTurn();
        game.endOfTurn();
        assertEquals(1971, game.getAge());

    }
}