package hotciv.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite-role in a composite pattern used for easier notification of GameObservers in Game implementations.
 */
public class GameObsComposite implements GameObserver {
    private List<GameObserver> observers;

    public GameObsComposite() {
        observers = new ArrayList<GameObserver>();
    }

    public void addObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void worldChangedAt(Position pos) {
        for (GameObserver obs : observers) {
            obs.worldChangedAt(pos);
        }
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        for (GameObserver obs : observers) {
            obs.turnEnds(nextPlayer, age);
        }
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        for (GameObserver obs : observers) {
            obs.tileFocusChangedAt(position);
        }
    }

    @Override
    public Position getTileFocus() {
        return null; // not meant to be used for this composite class.
    }
}
