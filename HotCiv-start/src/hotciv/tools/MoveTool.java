package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.UnitImpl;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * TODO write a file header
 */
public class MoveTool extends AbstractTool {
    private final Game game;
    private Drawing drawing;
    private Figure figure;
    private Position from;

    public MoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
        this.drawing = editor.drawing();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y){
        Position p = GfxConstants.getPositionFromXY(x, y);
        if (fAnchorX == x &&
                 fAnchorY == y &&
                 game.getUnitAt(p)!= null ){
            figure = drawing.findFigure(x,y);
            from = new Position(x,y);
        }
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int x, int y){
       if ( figure != null) {
         figure.draw();
       }
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int x, int y){
        Position to = new Position(x,y);

        if (game.moveUnit(from,to) == true) {

        }
        else {

        }

        figure = null;
    }
}
