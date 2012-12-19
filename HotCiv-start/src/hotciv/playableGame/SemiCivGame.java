package hotciv.playableGame;

import hotciv.GameFactory.SemiCivFactory;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.tools.MultiTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/**
 * TODO write a file header
 */
public class SemiCivGame {
    public static void main(String args[]) {
        Game game = new GameImpl(new SemiCivFactory());

        DrawingEditor editor =
                new MiniDrawApplication("SemiCivGame", new CivDrawWithTextFactory(game));

        editor.open();

        editor.setTool(new MultiTool(editor, game));
    }
}
