package hotciv.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * TODO write a file header
 */
public class MultiTool extends AbstractTool {
    private EndOfTurnTool endOfTurnTool;
    private MoveTool moveTool;
    private TileFocusTool tileFocusTool;
    private UnitActionTool unitActionTool;
    private Game game;

    public MultiTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
        endOfTurnTool = new EndOfTurnTool(editor, game);
        moveTool = new MoveTool(editor, game);
        tileFocusTool = new TileFocusTool(editor, game);
        unitActionTool = new UnitActionTool(editor, game);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        endOfTurnTool.mouseDown(e,x,y);
        moveTool.mouseDown(e,x,y);
        tileFocusTool.mouseDown(e,x,y);
        unitActionTool.mouseDown(e,x,y);
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        // mouse drag only relevant for moveTool...?
        //endOfTurnTool.mouseDrag(e,x,y);
        moveTool.mouseDrag(e, x, y);
        //tileFocusTool.mouseDrag(e,x,y);
        //unitActionTool.mouseDrag(e,x,y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        endOfTurnTool.mouseUp(e, x, y);
        moveTool.mouseUp(e, x, y);
        tileFocusTool.mouseUp(e, x, y);
        unitActionTool.mouseUp(e, x, y);
    }

}
