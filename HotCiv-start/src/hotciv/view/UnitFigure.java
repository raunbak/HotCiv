package hotciv.view;

import hotciv.framework.Position;
import hotciv.framework.Unit;
import minidraw.standard.ImageFigure;

import java.awt.*;

import static hotciv.view.GfxConstants.*;

public class UnitFigure extends ImageFigure {
    protected Unit associatedUnit;

    public UnitFigure(Unit unit, Point origin) {
        super( unit == null ? "black" : unit.getTypeString() , origin);
        associatedUnit = unit;
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(fImage, fDisplayBox.x,
                fDisplayBox.y - UNIT_OFFSET_Y,
                fDisplayBox.width, fDisplayBox.height, null);
        if (associatedUnit == null) {
            return;
        }


        // Draw the owner circle
        Color color =
                getColorForPlayer(associatedUnit.getOwner());
        g.setColor(color);
        g.fillOval(fDisplayBox.x, fDisplayBox.y, 8, 6);
        g.setColor(Color.black);
        g.drawOval(fDisplayBox.x, fDisplayBox.y, 8, 6);

        Position figurePosition = getPositionFromXY(fDisplayBox.x + TILESIZE / 2, fDisplayBox.y + TILESIZE / 2);
        int distance = associatedUnit.getPosition().distanceTo(figurePosition);
        // Draw the 'movable' box
        int mc = associatedUnit.getMoveCount();
        g.setColor( mc >= distance && mc > 0 ?
                Color.green : Color.red );
        g.fillOval(fDisplayBox.x, fDisplayBox.y+7, 8, 6);
        g.setColor(Color.black);
        g.drawOval(fDisplayBox.x, fDisplayBox.y+7, 8, 6);
    }
}