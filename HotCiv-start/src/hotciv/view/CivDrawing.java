package hotciv.view;

import hotciv.framework.*;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.ImageFigure;
import minidraw.standard.NullTool;
import minidraw.standard.StandardDrawing;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static hotciv.view.GfxConstants.*;

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
    private TextFigure ageText;
    private ImageFigure turnShieldIcon;
    private ImageFigure unitShieldIcon;
    private ImageFigure cityShieldIcon;
    private ImageFigure productionIcon;
    private ImageFigure workForceIcon;
    private Position tileFocus;
    private TextFigure unitMoveCountText;

    public CivDrawing(DrawingEditor editor, Game game) {
        super();
        this.game = game;
        this.editor = editor;

        cityMap = new HashMap<Position,CityFigure>();
        unitMap = new HashMap<Position, UnitFigure>();
        // register this unit drawing as listener to any game state
        // changes...
        game.addObserver(this);
        // ... and build up the set of figures associated with
        // units in the game.
        defineUnitMap();

        defineCityMap();
        // and the set of 'icons' in the status panel
        defineIcons();

        // Add the text figure for displaying the age.
        int age = game.getAge();
        ageText = new TextFigure(age < 0 ? -age+" BC" : age+" AC",
                new Point(AGE_TEXT_X, AGE_TEXT_Y) );
        super.add(ageText);

        unitMoveCountText = new TextFigure( "", new Point(UNIT_COUNT_X, UNIT_COUNT_Y));
        super.add(unitMoveCountText);

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
    protected Map<Position,UnitFigure> unitMap = null;

    /** erase the old list of units, and build a completely new
     * one from scratch by iterating over the game world and add
     * Figure instances for each unit in the world.
     */
    private void defineUnitMap() {
        for (UnitFigure f : unitMap.values()) {
            super.remove(f);
        }
        unitMap.clear();
        Position p;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                p = new Position(r,c);
                Unit unit = game.getUnitAt(p);
                if ( unit == null ) {
                    continue;
                }
                // convert the unit's Position to (x,y) coordinates
                Point point = new Point( getXFromColumn(p.getColumn()),
                                            getYFromRow(p.getRow()) );

                UnitFigure unitFigure = new UnitFigure( unit, point );
                unitFigure.addFigureChangeListener(this);
                unitMap.put(p, unitFigure);

                // also insert in superclass list as it is
                // this list that is iterated by the
                // graphics rendering algorithms
                super.add(unitFigure);
            }
        }
    }

    protected Map<Position,CityFigure> cityMap = null;

    private void defineCityMap() {
        for (CityFigure f : cityMap.values()) {
            super.remove(f);
        }
        cityMap.clear();
        Position p;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                p = new Position(r,c);
                City city = game.getCityAt(p);
                if ( city == null ) {
                    continue;
                }
                // convert the unit's Position to (x,y) coordinates
                Point point = new Point( getXFromColumn(p.getColumn()),
                        getYFromRow(p.getRow()) );

                CityFigure cityFigure = new CityFigure( city, point );
                cityFigure.addFigureChangeListener(this);
                cityMap.put(p, cityFigure);

                // also insert in superclass list as it is
                // this list that is iterated by the
                // graphics rendering algorithms
                super.add(cityFigure);
            }
        }
    }

    private void defineIcons() {
        super.remove(turnShieldIcon);
        super.remove(unitShieldIcon);
        super.remove(cityShieldIcon);
        super.remove(productionIcon);
        super.remove(workForceIcon);

        turnShieldIcon = new ImageFigure( Player.RED.toString().toLowerCase()+SHIELD, new Point( TURN_SHIELD_X, TURN_SHIELD_Y ) );

        unitShieldIcon = new ImageFigure( NOTHING, new Point( UNIT_SHIELD_X, UNIT_SHIELD_Y ) );

        cityShieldIcon = new ImageFigure( NOTHING, new Point( CITY_SHIELD_X, CITY_SHIELD_Y ) );

        productionIcon = new ImageFigure( NOTHING, new Point( CITY_PRODUCTION_X, CITY_PRODUCTION_Y ) );

        workForceIcon = new UnitFigure( null, new Point( WORKFORCEFOCUS_X, WORKFORCEFOCUS_Y ) );

        // insert in superclass figure list to ensure graphical
        // rendering.
        super.add(turnShieldIcon);
        super.add(unitShieldIcon);
        super.add(cityShieldIcon);
        super.add(productionIcon);
        super.add(workForceIcon);
    }

    // === Observer Methods ===

    public void worldChangedAt(Position pos) {
        // convert the unit's Position to (x,y) coordinates
        int x = getXFromColumn(pos.getColumn());
        int y = getYFromRow(pos.getRow());

        // remove old city figure and add new one.
        CityFigure cityFigure = cityMap.get(pos);
        if (cityFigure != null) {
            super.remove(cityFigure);
            cityMap.remove(pos);
        }
        City city = game.getCityAt(pos);
        if (city != null) {
            Point point = new Point(x, y);
            cityFigure = new CityFigure( city, point );
            cityFigure.addFigureChangeListener(this);
            cityMap.put(pos, cityFigure);
            super.add(cityFigure);
        }

        // remove old unit figure and add new one.
        UnitFigure unitFigure = unitMap.get(pos);
        if (unitFigure != null) {
            super.remove(unitFigure);
            unitMap.remove(pos);
        }
        unitMoveCountText.setText("");
        Unit unit = game.getUnitAt(pos);
        if (unit != null) {
            unitMoveCountText.setText(""+unit.getMoveCount());
            Point point = new Point(x, y);
            unitFigure = new UnitFigure( unit, point );

            unitFigure.addFigureChangeListener(this);
            unitMap.put(pos, unitFigure);
            super.add(unitFigure);
        }

        checkWinnerStatus();
    }

    public void turnEnds(Player nextPlayer, int age) {
        editor.showStatus("Age: "+age+", player in turn: "+nextPlayer);
        defineIcons();
        turnShieldIcon.set( nextPlayer.toString().toLowerCase() + SHIELD,
                new Point( TURN_SHIELD_X, TURN_SHIELD_Y ) );

        // Update the age-text
        String text = age < 0 ? -age+" BC" : age+" AC";
        ageText.setText(text);

        unitMoveCountText.setText("");

        checkWinnerStatus();
    }

    public void tileFocusChangedAt(Position position) {
        tileFocus = position;

        // Unit shield (owner)
        Unit unit = game.getUnitAt(position);
        String colorUnit = unit != null ? unit.getOwner()+SHIELD : NOTHING;
        unitShieldIcon.set( colorUnit.toLowerCase(), new Point( UNIT_SHIELD_X, UNIT_SHIELD_Y ) );

        // Set the movecount text for the unit
        String movecount = unit != null ? ""+unit.getMoveCount() : "";
        unitMoveCountText.setText(movecount);


        // City shield (owner)
        City city = game.getCityAt(position);
        String colorCity = city != null ? city.getOwner()+SHIELD : NOTHING;
        cityShieldIcon.set( colorCity.toLowerCase(), new Point( CITY_SHIELD_X, CITY_SHIELD_Y ) );

        // Production icon (unittype)
        String unittype = city != null ? city.getProduction() : NOTHING;
        unittype = unittype != null ? unittype : NOUNIT;
        productionIcon.set( unittype, new Point( CITY_PRODUCTION_X, CITY_PRODUCTION_Y ) );

        // Workforce focus (prioritize food, production, or...)
        String focus = city != null ? city.getWorkForceFocus() : null;
        focus = focus != null ? focus : NOTHING;
        workForceIcon.set( focus, new Point( WORKFORCEFOCUS_X, WORKFORCEFOCUS_Y ) );
    }

    private void checkWinnerStatus() {
        Player winner = game.getWinner();
        if (winner != null) {
            editor.showStatus(winner+" wins the game!");
            editor.view().drawDrawing(editor.view().getGraphics());
            editor.setTool(new NullTool());
        }
    }

    @Override
    public Position getTileFocus() {
        return tileFocus;
    }
}