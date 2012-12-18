package hotciv.tools;

import hotciv.framework.*;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;
import minidraw.standard.ImageFigure;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

/**
 * TODO write a file header
 */
public class WorkForceFocusTool extends AbstractTool {
    private Game game;
    private final Drawing drawing;
    private final Point workForceIconPoint;

    public WorkForceFocusTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
        drawing = editor.drawing();
        workForceIconPoint = new Point( GfxConstants.WORKFORCEFOCUS_X,
                GfxConstants.WORKFORCEFOCUS_Y );
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        Figure figure = drawing.findFigure(x,y);
        if (fAnchorX == x
                && fAnchorY == y
                && figure != null
                && figure.displayBox().getLocation().equals(workForceIconPoint)) {
            Position p = ((GameObserver)drawing).getTileFocus();

            City city = game.getCityAt(p);
            if (city == null || !city.getOwner().equals(game.getPlayerInTurn())) {
                return;
            }
            // Find the right focus to toggle to.
            String oldFocus = city.getWorkForceFocus();
            Iterator<String> iter = GameConstants.BALANCE_DESCRIPTIONMAP.keySet().iterator();
            String focusFirst = iter.next();
            String focus = focusFirst;
            boolean isFound = false;
            while (iter.hasNext()) {
                if (focus.equals(oldFocus)) {
                    focus = iter.next();
                    isFound = true;
                    break;
                }
                focus = iter.next();
            }
            if (!isFound) {
                focus = focusFirst;
            }
            game.changeWorkForceFocusInCityAt(p, focus);

            ((ImageFigure)figure).set(focus, workForceIconPoint);

            editor.showStatus(game.getPlayerInTurn()+" changes workforcefocus in city at "+p+" to: "+GameConstants.BALANCE_DESCRIPTIONMAP.get(focus));
        }
    }
}
