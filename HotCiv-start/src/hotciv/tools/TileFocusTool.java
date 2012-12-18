package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;

import java.awt.event.MouseEvent;

/**
 * TODO write a file header
 */
public class TileFocusTool extends AbstractTool {
    private Game game;

    public TileFocusTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        Position p = GfxConstants.getPositionFromXY(x,y);
        if (fAnchorX == x
                && fAnchorY == y
                && p.isValidWorldPosition()) {
            game.setTileFocus(p);
        }
    }


}
