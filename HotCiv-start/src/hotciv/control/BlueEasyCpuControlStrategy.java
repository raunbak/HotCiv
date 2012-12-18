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
    private int sleepMillis;

    public BlueEasyCpuControlStrategy(int sleepMillis) {
        blueEasyPlayer = new EasyPlayerAIstrategy(this);
        this.sleepMillis = sleepMillis;
    }

    @Override
    public void playRound(ExtendedGame game) {
        sleep();
        if (game.getPlayerInTurn().equals(Player.BLUE)) {
            blueEasyPlayer.doActions(game);
        }
    }

    @Override
    public void sleep() {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
