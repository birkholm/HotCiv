package EpsilonCiv;

import common.Game;
import common.GameConstants;
import common.GameImpl;
import org.junit.Before;
import org.junit.Test;
import variants.alphaCiv.*;

public class TestEpsilonCiv {

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
    public void shouldWinAfter3Attacks()    {
        
    }
}
