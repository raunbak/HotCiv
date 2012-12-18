package hotciv.tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * TODO write a file header
 */
public class EndOfTurnTool extends AbstractTool {
    private Game game;
    private Drawing drawing;
    private Point turnShieldPoint;

    public EndOfTurnTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
        this.drawing = editor.drawing();
        turnShieldPoint = new Point( GfxConstants.TURN_SHIELD_X,
                GfxConstants.TURN_SHIELD_Y );
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        Figure figure = drawing.findFigure(x,y);
        if (fAnchorX == x
                && fAnchorY == y
                && figure != null
                && figure.displayBox().getLocation().equals(turnShieldPoint)) {
            game.endOfTurn();
        }
    }
}
