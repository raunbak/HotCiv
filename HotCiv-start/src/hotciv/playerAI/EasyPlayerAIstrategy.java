package hotciv.playerAI;

import hotciv.control.ControlStrategy;
import hotciv.framework.ExtendedGame;
import hotciv.framework.Position;
import hotciv.framework.Vector;

/**
 * TODO write a file header
 */
public class EasyPlayerAIstrategy implements PlayerAIstrategy {
    private ControlStrategy control;

    public EasyPlayerAIstrategy(ControlStrategy controlStrategy) {
        control = controlStrategy;
    }

    @Override
    public void doActions(ExtendedGame game) {
        // TODO something interesting
        Vector v = new Vector(-1,0);
        for (Position p : game.getUnitPositions()) {
            // Make sure not to try to move outside the world.
            while(!v.addToPosition(p).isValidWorldPosition()) {
                v.rotate45clockwise();
            }
            boolean succesfulMove = game.moveUnit(p, v.addToPosition(p));
            if (succesfulMove) {
                control.sleep();
            }
            v.rotate45clockwise();
        }
        game.endOfTurn();
    }
}
