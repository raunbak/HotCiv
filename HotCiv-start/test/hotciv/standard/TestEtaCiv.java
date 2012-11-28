package hotciv.standard;

import hotciv.GameFactory.EtaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.ModifiableCity;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class TestEtaCiv {
    private Game game;

    @Before
    public void setup() {
        game = new GameImpl(new EtaCivFactory());
    }

    @Test
    public void redCityWithProductionFocusShouldProduce4perRound() {
        Position p = new Position(1,1);
        ModifiableCity redCity = (ModifiableCity) game.getCityAt(p);
        redCity.addToPopulation(3);
        redCity.setWorkForceFocus(GameConstants.productionFocus);
        makeGameRunNturns(1);
        assertEquals("Should have produced 4 resources.", 4, game.getCityAt(p).getCurrentAmountOfProduction());
    }

    @Test
    public void redCityWithFoodFocusShouldProduce10perRound() {
        Position p = new Position(1,1);
        ModifiableCity blueCity = (ModifiableCity) game.getCityAt(p);
        blueCity.addToPopulation(3);
        blueCity.setWorkForceFocus(GameConstants.foodFocus);
        makeGameRunNturns(1);
        assertEquals("Should have produced 10 food.", 10, game.getCityAt(p).getCurrentAmountOfFood());
    }


    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
