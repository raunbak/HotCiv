package hotciv.view;

import hotciv.framework.*;
import hotciv.view.CityFigure;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.*;
import minidraw.standard.ImageFigure;
import minidraw.standard.StandardDrawing;

import java.awt.*;
//import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class CivDrawing
        extends StandardDrawing
        implements GameObserver {

    /** the Game instance that this UnitDrawing is going to render units
     * from */
    protected Game game;
    protected DrawingEditor editor;

    public CivDrawing(DrawingEditor editor, Game game) {
        super();
        this.game = game;
        this.editor = editor;

        // register this unit drawing as listener to any game state
        // changes...
        game.addObserver(this);
        // ... and build up the set of figures associated with
        // units in the game.
        defineUnitMap();

        defineCityMap();
        // and the set of 'icons' in the status panel
        defineIcons();


    }

    /** The UnitDrawing should not allow client side
     * units to add and manipulate figures; only figures
     * that renders game objects are relevant, and these
     * should be handled by observer events from the game
     * instance. Thus this method is 'killed'.
     */
    public Figure add(Figure arg0) {
        throw new RuntimeException("Should not be used...");
    }

    /** store all moveable figures visible in this drawing = units */
    protected Map<Unit,UnitFigure> unitMap = null;

    /** erase the old list of units, and build a completely new
     * one from scratch by iterating over the game world and add
     * Figure instances for each unit in the world.
     */
    private void defineUnitMap() {

        unitMap = new HashMap<Unit,UnitFigure>();
        Position p;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                p = new Position(r,c);
                Unit unit = game.getUnitAt(p);
                if ( unit != null ) {
                    // convert the unit's Position to (x,y) coordinates
                    Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                            GfxConstants.getYFromRow(p.getRow()) );
                    UnitFigure unitFigure =
                            new UnitFigure( unit, point );
                    unitFigure.addFigureChangeListener(this);
                    unitMap.put(unit, unitFigure);

                    // also insert in superclass list as it is
                    // this list that is iterated by the
                    // graphics rendering algorithms
                    super.add(unitFigure);
                }
            }
        }
    }

    protected Map<City,CityFigure> cityMap = null;

    private void defineCityMap() {

        cityMap = new HashMap<City,CityFigure>();
        Position p;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                p = new Position(r,c);
                City city = game.getCityAt(p);
                if ( city != null ) {
                    // convert the unit's Position to (x,y) coordinates
                    Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                            GfxConstants.getYFromRow(p.getRow()) );
                    CityFigure cityFigure =
                            new CityFigure( city, point );
                    cityFigure.addFigureChangeListener(this);
                    cityMap.put(city, cityFigure);

                    // also insert in superclass list as it is
                    // this list that is iterated by the
                    // graphics rendering algorithms
                    super.add(cityFigure);
                }
            }
        }
    }

    private ImageFigure turnShieldIcon;
    private void defineIcons() {
        // very much a template implementation :)
        turnShieldIcon =
                new ImageFigure( "redshield",
                        new Point( GfxConstants.TURN_SHIELD_X,
                                GfxConstants.TURN_SHIELD_Y ) );
        // insert in superclass figure list to ensure graphical
        // rendering.
        super.add(turnShieldIcon);
    }

    // === Observer Methods ===

    public void worldChangedAt(Position pos) {
        System.out.println( "UnitDrawing: world changes at "+pos);
        // this is a really brute-force algorithm: destroy
        // all known units and build up the entire set again
        for ( Figure f : cityMap.values() ) {
            super.remove(f);
        }
        for ( Figure f : unitMap.values() ) {
            super.remove(f);
        }
        // ensure no units of the old list are accidental in
        // the selection!
        clearSelection();

        defineCityMap();
        defineUnitMap();

        // TODO find the right way to correctly paint the changes made each round.. for cpuplayers
        //editor.view().checkDamage();
        /*
        Figure f = findFigure(GfxConstants.getXFromColumn(pos.getColumn()),GfxConstants.getYFromRow(pos.getRow()));
        if (f != null) {
            figureInvalidated(new FigureChangeEvent(f));
            requestUpdate();
        }
        */

        //editor.view().drawDrawing(editor.view().getGraphics());
    }

    public void turnEnds(Player nextPlayer, int age) {
        System.out.println( "UnitDrawing: turnEnds for "+
                nextPlayer+" at "+age );
        String playername = "red";
        if ( nextPlayer == Player.BLUE ) { playername = "blue"; }
        turnShieldIcon.set( playername+"shield",
                new Point( GfxConstants.TURN_SHIELD_X,
                        GfxConstants.TURN_SHIELD_Y ) );
    }

    public void tileFocusChangedAt(Position position) {
        System.out.println( "Fake it: tileFocusChangedAt "+position );
    }
}