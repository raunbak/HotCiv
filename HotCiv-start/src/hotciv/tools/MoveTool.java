package hotciv.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;

/**
 * TODO write a file header
 */
public class MoveTool extends AbstractTool {
    private final Game game;

    public MoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.game = game;
    }
}
