package deltaCiv;

import common.*;
import org.junit.Before;
import org.junit.Test;
import variants.alphaCiv.AlphaActionStrategy;
import variants.alphaCiv.AlphaAgeStrategy;
import variants.alphaCiv.AlphaMoveStrategy;
import variants.alphaCiv.AlphaWinnerStrategy;
import variants.deltaCiv.DeltaMapStrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestDeltaCiv {
    private Game game;
    private GameConstants constants = new GameConstants();

    /**
     * Fixture for betaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaWinnerStrategy(),
                new AlphaAgeStrategy(),
                new AlphaActionStrategy(),
                new AlphaMoveStrategy(),
                new DeltaMapStrategy()
        );
    }

    @Test
    public void shouldHaveARedCityAt8_12() {
        City c = game.getCityAt(new Position(8, 12));
        assertNotNull("There should be a city at (8,12)", c);
        Player p = c.getOwner();
        assertEquals("City at (8,12) should be owned by red",
                Player.RED, p);

    }

    @Test
    public void shouldHaveBlueCityAt4_5() {
        City c = game.getCityAt(new Position(4, 5));
        assertNotNull("There should be a city at (4,5)", c);
        Player p = c.getOwner();
        assertEquals("City at (4,5) should be owned by red",
                Player.BLUE, p);

    }
}