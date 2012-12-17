package hotciv.standard;

import hotciv.framework.*;
import hotciv.GameFactory.PlayerVsEasyCpuCivFactory;
import hotciv.view.CivDrawing;
import hotciv.view.MapView;
import minidraw.framework.*;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * TODO write a file header
 */
public class TestVisual {

    public static void main(String args[]) {
        Game game = new GameImpl(new PlayerVsEasyCpuCivFactory());

        DrawingEditor editor =
                new MiniDrawApplication("TestVisual", new TestFactory(game));

        // TODO figure out why doing endOfTurn in a MouseEvent in a Tool makes graphics act differently
        //editor.setTool(new UpdateTool(game));

        editor.open();

        game.endOfTurn();
    }
}

class UpdateTool extends NullTool {
    private Game game;

    public UpdateTool(Game game) {
        this.game = game;
    }

    private int count = 0;
    public void mouseUp(MouseEvent e, int x, int y) {
        switch(count) {
            case -1:  // Not in use right now
                System.out.println("Moving a unit...");
                game.moveUnit(new Position(2,0), new Position(1,1));
                System.out.println("Unit at position 'to': "+game.getUnitAt(new Position(1,1)).getTypeString());
                break;

            case 1:
                System.out.println("end of turn...");
                game.endOfTurn();
                break;

            case 2:
                System.out.println("inspect position 4,1 (blue city)");
                game.setTileFocus(new Position(4,1));
                break;

        }
        count++;
    }
}

class TestFactory implements Factory {
    private Game game;

    public TestFactory(Game game) {
        this.game = game;
    }

    @Override
    public DrawingView createDrawingView(DrawingEditor editor) {
        return new MapView(editor, game);
    }

    @Override
    public Drawing createDrawing(DrawingEditor editor) {
        return new CivDrawing(editor, game);
    }

    @Override
    public JTextField createStatusField(DrawingEditor editor) {
        return null;
    }
}