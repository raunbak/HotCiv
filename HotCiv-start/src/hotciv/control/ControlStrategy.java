package hotciv.control;

import hotciv.framework.ExtendedGame;

/**
 * TODO write a file header
 */
public interface ControlStrategy {
    /**
     *
     */
    public void playRound(ExtendedGame game);

    public void sleep();
}
