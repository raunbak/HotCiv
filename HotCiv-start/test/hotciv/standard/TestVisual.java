package hotciv.standard;

import hotciv.GameFactory.AlphaCivFactory;
import hotciv.GameFactory.SemiCivFactory;
import hotciv.framework.*;
import hotciv.GameFactory.PlayerVsEasyCpuCivFactory;
import hotciv.tools.MultiTool;
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

        editor.open();

        editor.setTool(new MultiTool(editor, game));
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