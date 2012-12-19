package hotciv.playableGame;

import hotciv.framework.Game;
import hotciv.view.CivDrawing;
import hotciv.view.MapView;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;

import javax.swing.*;

/**
 * TODO write a file header
 */
public class CivDrawWithTextFactory implements Factory {
    private Game game;

    public CivDrawWithTextFactory(Game game) {
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
        JTextField f = new JTextField("HotCivilization, game variant: SemiCiv");
        f.setEditable(false);
        return f;
    }
}
