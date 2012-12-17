package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * TODO write a file header
 */
public class UnitActionTool extends AbstractTool {
    private Game game;

    public UnitActionTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
    }

    @Override
    public void keyDown(KeyEvent e, int key) {

    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        Position p = GfxConstants.getPositionFromXY(x,y);
        Unit u = game.getUnitAt(p);
        if (fAnchorX == x
                && fAnchorY == y
                && e.isShiftDown()
                && u != null) {
            game.performUnitActionAt(p);
        }
    }
}
