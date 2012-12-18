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
public class ProductionFocusTool extends AbstractTool{
    private Game game;
    private final Point productionIconPoint;
    private Drawing drawing;

    public ProductionFocusTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
        drawing = editor.drawing();
        productionIconPoint = new Point( GfxConstants.CITY_PRODUCTION_X,
                GfxConstants.CITY_PRODUCTION_Y );
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        Figure figure = drawing.findFigure(x,y);
        if (fAnchorX == x
                && fAnchorY == y
                && figure != null
                && figure.displayBox().getLocation().equals(productionIconPoint)) {
            Position p = ((GameObserver)drawing).getTileFocus();
            City city = game.getCityAt(p);
            if (city == null || !city.getOwner().equals(game.getPlayerInTurn())) {
                return;
            }
            // Find the right unittype to toggle to.
            String oldUnittype = city.getProduction();
            Iterator<String> iter = GameConstants.COSTMAP.keySet().iterator();
            String unittypeFirst = iter.next();
            String unittype = unittypeFirst;
            boolean isFound = false;
            while (iter.hasNext()) {
                if (unittype.equals(oldUnittype)) {
                    unittype = iter.next();
                    isFound = true;
                    break;
                }
                unittype = iter.next();
            }
            if (!isFound) {
                unittype = unittypeFirst;
            }
            game.changeProductionInCityAt(p, unittype);

            ((ImageFigure)figure).set( unittype, productionIconPoint);

            editor.showStatus(game.getPlayerInTurn() + " changes production in city at " + p + " to: " + unittype);
        }
    }
}
