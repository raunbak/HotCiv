package hotciv.framework;


/**
 * Decorator for ExtendedGame that add a transcript functionality.
 */
@SuppressWarnings("unused")
public class ExtendedGameDecorator {
    private ExtendedGame extendedGame;
    private boolean transcribing;

    public ExtendedGameDecorator (ExtendedGame extendedGame) {
        this.extendedGame = extendedGame;
        transcribing = true;

    }

    public void changeTranscribingMode(){
        transcribing = !transcribing;
    }

    // Methods that are transcribed.

    public boolean moveUnit(Position from, Position to) {
        String unittypeMoved = extendedGame.getUnitAt(from).getTypeString();
        boolean moveSuccessful = extendedGame.moveUnit(from, to);

        // Only transcribe, if the move actually happened.
        if (transcribing && moveSuccessful) {
            System.out.println(extendedGame.getPlayerInTurn()+
                    " moves "+unittypeMoved+
                    " from "+from+ " to "+ to);
        }

        return moveSuccessful;
    }

    public void endOfTurn() {
        if (transcribing) {
            System.out.println(extendedGame.getPlayerInTurn()+" ends turn");
        }
        extendedGame.endOfTurn();
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        if (transcribing) {
            System.out.println(extendedGame.getPlayerInTurn()+" changes production in city at "+
                    p+" to "+unitType);
        }
        extendedGame.changeProductionInCityAt(p, unitType);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        if (transcribing) {
            System.out.println(extendedGame.getPlayerInTurn()+" changes work force focus in city at "+
                    p+" to "+balance);
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