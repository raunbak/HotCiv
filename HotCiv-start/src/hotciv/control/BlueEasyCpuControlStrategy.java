package hotciv.control;

import hotciv.framework.ExtendedGame;
import hotciv.framework.Player;
import hotciv.playerAI.EasyPlayerAIstrategy;
import hotciv.playerAI.PlayerAIstrategy;

/**
 * TODO write a file header
 */
public class BlueEasyCpuControlStrategy implements ControlStrategy {
    private PlayerAIstrategy blueEasyPlayer;

    public BlueEasyCpuControlStrategy() {
        blueEasyPlayer = new EasyPlayerAIstrategy(this);
    }

    @Override
    public void playRound(ExtendedGame game) {
        if (game.getPlayerInTurn().equals(Player.BLUE)) {
            blueEasyPlayer.doActions(game);
        }
    }

    @Override
    public void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
