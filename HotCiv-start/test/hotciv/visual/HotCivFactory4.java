package hotciv.visual;

import hotciv.framework.Game;
import hotciv.view.CivDrawing;
import hotciv.view.MapView;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;

import javax.swing.*;

/** Factory for visual testing of various dSoftArk template code */
class HotCivFactory4 implements Factory {
  private Game game;
  public HotCivFactory4(Game g) { game = g; }

  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new MapView(editor, game);
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new CivDrawing( editor, game );
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField f = new JTextField("dSoftArk template code");
    f.setEditable(false);
    return f;
  }
}
