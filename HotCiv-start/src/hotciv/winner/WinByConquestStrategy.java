package hotciv.winner;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.winner.WinnerStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 15-11-12
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
public class WinByConquestStrategy implements WinnerStrategy{

    @Override
    public Player winner(Game game) {

        Player winningplayer;
        City[] citylist = game.getAllCities();

        // TODO Skal laves til flere byer!!!!!
        if (citylist[0].getOwner() == citylist[1].getOwner()) {
           return citylist[0].getOwner();
        }
        /**for (int i = 0; i<=1;i++) {

            if (citylist)
            System.out.println(citylist[i].getOwner());
        }
        */
        return  null;
    }
}
