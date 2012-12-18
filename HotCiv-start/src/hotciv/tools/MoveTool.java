package hotciv.tools;

import hotciv.framework.*;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

import static hotciv.view.GfxConstants.TILESIZE;
import static hotciv.view.GfxConstants.getPositionFromXY;

/**
 * TODO write a file header
 */
public class MoveTool extends AbstractTool {
    private final Game game;
    private Drawing drawing;
    private Figure figure;
    private Position from;
    private int oldX, oldY;


    public MoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
        this.drawing = editor.drawing();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y){
        Position p = getPositionFromXY(x, y);
        Unit unit = game.getUnitAt(p);
        if (unit != null
                && unit.getOwner().equals(game.getPlayerInTurn())){
            figure = drawing.findFigure(x,y);
            from = p;
            oldX = x;
            oldY = y;
            game.setTileFocus(p);
        }
    }

    @Override
    public void mouseDrag(MouseEvent mouseEvent, int x, int y){
       if ( figure != null) {
           figure.moveBy(x - oldX, y - oldY);
           oldX = x;
           oldY = y;
       }
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent, int x, int y){
        if (figure == null) {
            return;
        }
        Rectangle rect = figure.displayBox();
        Position to = getPositionFromXY(rect.x + TILESIZE/2, rect.y + TILESIZE/2);

        String unittypeMoved = game.getUnitAt(from).getTypeString();

        boolean moveSuccessful = game.moveUnit(from,to);
        if (moveSuccessful) {
            Player winner = game.getWinner();
            if (winner != null) {
                editor.showStatus(winner+" wins the game!");
                editor.setTool(new NullTool());
            } else {
                editor.showStatus(game.getPlayerInTurn()+
                        " moves "+unittypeMoved+
                        " from "+from+ " to "+ to);
            }
        } else {
            ((GameObserver)drawing).worldChangedAt(from);
        }

        figure = null;
    }
}
