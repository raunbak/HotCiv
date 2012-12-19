package hotciv.standard;

import hotciv.GameFactory.PlayerVsEasyCpuCivFactory;
import hotciv.GameFactory.SemiCivFactory;
import hotciv.framework.Game;
import hotciv.tools.MultiTool;
import hotciv.view.CivDrawing;
import hotciv.view.MapView;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;

import javax.swing.*;

/**
 * TODO write a file header
 */
public class TestVisual {

    public static void main(String args[]) {
        int sleepTimeMillis = 0;  // pause between actions.
        Game game = new GameImpl(new PlayerVsEasyCpuCivFactory(sleepTimeMillis));

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
        JTextField f = new JTextField("HotCivilization, game variant: PlayerVsEasyCpuCivFactory");
        f.setEditable(false);
        return f;
    }
}