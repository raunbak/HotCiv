package hotciv.framework;

/**
 * A class representing a key needed to mutate City and Unit objects.
 * A key-instance will be generated at runtime when a game is created and this key
 * will be held by the Game-instance and by each of the City and Unit objects.
 * This way, the game can directly mutate the objects and give the key-pointer to other
 * objects (e.g. some of the strategy-objects) that should be allowed to make modifications.
 * At the same time, the game can safely pass real City and Unit-objects-pointers
 * to a GUI without the liability of letting the GUI mutate the objects directly.
 * Another solution would have been to keep Tiles, Cities, and Units immutable,
 * but then all the mutators would need to be put in Game, which again would lead to
 * GUIs being able make direct modifications on the objects through Game. Using MutatorKeys,
 * no extra mutators are needed either in the Game-interface nor in the implementation.
 */
public class MutatorKey {
    private double doubleKey;

    public MutatorKey() {
        // Here, a random double between 0 and 1 is used as the key.
        doubleKey = Math.random();
    }

    private double getDoubleKey() {
        return doubleKey;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != MutatorKey.class) {
            return false;
        }
        MutatorKey other = (MutatorKey) o;
        return doubleKey == other.getDoubleKey();
    }
}
