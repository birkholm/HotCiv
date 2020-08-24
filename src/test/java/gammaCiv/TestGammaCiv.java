package gammaCiv;

import common.*;
import org.junit.Before;
import org.junit.Test;
import variants.alphaCiv.AlphaAgeStrategy;
import variants.alphaCiv.AlphaMapStrategy;
import variants.alphaCiv.AlphaWinnerStrategy;
import variants.gammaCiv.GammaActionStrategy;
import variants.gammaCiv.GammaMoveStrategy;

import static org.junit.Assert.*;

public class TestGammaCiv {
    private Game game;
    private GameConstants constants;

    /**
     * Fixture for betaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaWinnerStrategy(), new AlphaAgeStrategy(), new GammaActionStrategy(), new GammaMoveStrategy(), new AlphaMapStrategy());
        constants = new GameConstants();
    }

    @Test
    public void settlerShouldBuildCity() {

        Position position = new Position(4, 3);
        //assume there is a RED settler on 4,3
        assertEquals(GameConstants.SETTLER, game.getUnitAt(position).getTypeString());
        game.performUnitActionAt(position);
        //there should be a RED city at previous position, with population 1
        assertNotNull(game.getCityAt(position));
        assertEquals(Player.RED, game.getCityAt(position).getOwner());
        assertEquals(1, game.getCityAt(position).getSize());
        //the settler should be removed from the position
        assertEquals(false, (GameConstants.SETTLER.equals(game.getUnitAt(position))));
    }

    @Test
    public void archerShouldFortify() {
        //RED archer at 2,0
        Position position = new Position(2, 0);
        assertEquals(GameConstants.ARCHER, game.getUnitAt(position).getTypeString());
        assertEquals(3, game.getUnitAt(position).getDefensiveStrength());

        game.performUnitActionAt(position);
        assertEquals(6, game.getUnitAt(position).getDefensiveStrength());

        assertTrue(game.getUnitAt(position).isFortify());

        //not possible to move a fortify unit
        assertFalse(game.moveUnit(position, new Position(7, 5)));
    }

    @Test
    public void removeArchersFortify() {
        //RED archer fortity at 2, 0 with 6 defensive strength
        archerShouldFortify();

        Position position = new Position(2, 0);

        game.performUnitActionAt(position);
        assertEquals(3, game.getUnitAt(position).getDefensiveStrength());

        assertFalse(game.getUnitAt(position).isFortify());

        assertTrue(game.moveUnit(position, new Position(7, 5)));
    }


}