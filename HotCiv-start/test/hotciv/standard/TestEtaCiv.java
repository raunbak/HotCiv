package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.ModifiableCity;
import hotciv.framework.Position;
import hotciv.gameFactory.EtaCivFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        ModifiableCity city = (ModifiableCity) game.getCityAt(p);
        city.addToPopulation(3);
        city.setWorkForceFocus(GameConstants.productionFocus);
        makeGameRunNturns(1);
        assertEquals("Should have produced 4 resources.", 4, game.getCityAt(p).getCurrentAmountOfProduction());
    }

    @Test
    public void redCityWithFoodFocusShouldProduce10perRound() {
        Position p = new Position(1,1);
        ModifiableCity city = (ModifiableCity) game.getCityAt(p);
        city.addToPopulation(3);
        makeGameRunNturns(1);
        assertEquals("Should have produced 10 food.", 10, game.getCityAt(p).getCurrentAmountOfFood());
    }

    @Test
    public void redCityShouldGrowToSize5inTwoRounds() {
        Position p = new Position(1,1);
        ModifiableCity city = (ModifiableCity) game.getCityAt(p);
        city.addToPopulation(3);
        makeGameRunNturns(1);
        assertEquals("City should still be of size 4.", 4, city.getSize());
        makeGameRunNturns(1);
        assertEquals("City should now have grown to size 5.", 5, city.getSize());
    }


    private void makeGameRunNturns(int Nturns) {
        for (int i = 0; i < 2 * Nturns; i++) {   // age should increment by 100 each time both player's turn has ended, 2*1000/100 = 20.
            game.endOfTurn();
        }
    }
}
