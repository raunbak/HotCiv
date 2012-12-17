package hotciv.view;

import java.awt.*;

import minidraw.standard.ImageFigure;

import hotciv.framework.*;

public class UnitFigure extends ImageFigure {
    protected Unit associatedUnit;

    public UnitFigure(Unit unit, Point origin) {
        super( unit.getTypeString(), origin);
        associatedUnit = unit;
    }

    public Unit getAssociatedUnit() {
        return associatedUnit;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(fImage, fDisplayBox.x,
                fDisplayBox.y - GfxConstants.UNIT_OFFSET_Y,
                fDisplayBox.width, fDisplayBox.height, null);

        // Draw the owner circle
        Color color =
                GfxConstants.getColorForPlayer(associatedUnit.getOwner());
        g.setColor(color);
        g.fillOval(fDisplayBox.x, fDisplayBox.y, 8, 6);
        g.setColor(Color.black);
        g.drawOval(fDisplayBox.x, fDisplayBox.y, 8, 6);

        // Draw the 'movable' box
        g.setColor( associatedUnit.getMoveCount() > 0 ?
                Color.green : Color.red );
        g.fillOval(fDisplayBox.x, fDisplayBox.y+7, 8, 6);
        g.setColor(Color.black);
        g.drawOval(fDisplayBox.x, fDisplayBox.y+7, 8, 6);
    }
}