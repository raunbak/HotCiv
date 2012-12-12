package hotciv.framework;

import hotciv.builder.Builder;


/**
 * Created with IntelliJ IDEA.
 * User: Raunbak
 * Date: 10-12-12
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */
public class ExtendedGameDecorator {
    private ExtendedGame extendedGame;
    private boolean transcribing;

    public ExtendedGameDecorator (ExtendedGame extendedGame) {
        this.extendedGame = extendedGame;
        transcribing = true;

    }

    public void changeTranscribingMode(){
        if (transcribing == true) {
            transcribing = false;
            return;
        }
        if (transcribing == false) {
            transcribing = true;
        }

    }

    // Methods that are transcribed.

    public boolean moveUnit(Position from, Position to) {
        if (transcribing == true) {
            System.out.println(extendedGame.getPlayerInTurn().toString()+
                    " moves "+extendedGame.getUnitAt(from).getTypeString()+
                    " from "+from.toString()+ " to "+ to.toString());
        }


        return extendedGame.moveUnit(from, to);
    }

    public void endOfTurn() {
        if (transcribing == true) {
            System.out.println(extendedGame.getPlayerInTurn()+" ends turn");
        }
        extendedGame.endOfTurn();
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        if (transcribing == true) {
            System.out.println(extendedGame.getPlayerInTurn()+" changes production in city at "+
                    p.toString()+" to "+unitType);
        }
        extendedGame.changeProductionInCityAt(p, unitType);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        if (transcribing == true) {
            System.out.println(extendedGame.getPlayerInTurn()+" changes work force focus in city at"+
                    p.toString()+" to "+balance);
        }
        extendedGame.changeWorkForceFocusInCityAt(p, balance);
    }

    // Methods that are not transcribed.

    public int getRoundsPlayed() {
        return extendedGame.getRoundsPlayed();
    }

    public City getCityAt(Position p) {
        return extendedGame.getCityAt(p);
    }

    public void addAttacksWonSubscriber(AttacksWonSubscriber sub) {
        extendedGame.addAttacksWonSubscriber(sub);
    }

    public Tile getTileAt(Position p) {
        return extendedGame.getTileAt(p);
    }

    public int getAge() {
        return extendedGame.getAge();
    }

    public Iterable<Position> getCityPositions() {
        return extendedGame.getCityPositions();
    }

    public Player getPlayerInTurn() {
        return extendedGame.getPlayerInTurn();
    }

    public Unit getUnitAt(Position p) {
        return extendedGame.getUnitAt(p);
    }

    public void performUnitActionAt(Position p) {
        extendedGame.performUnitActionAt(p);
    }

    public Player getWinner() {
        return extendedGame.getWinner();
    }

}