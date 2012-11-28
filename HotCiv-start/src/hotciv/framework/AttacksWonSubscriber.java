package hotciv.framework;

/**
 * Classes that needs to be notified when attacks are won in ExtendedGame,
 * needs to implements this and call addAttacksWonSubscriber on game.
 */
public interface AttacksWonSubscriber {
    /**
     * The game calls this on all subscribers whenever an attack is won.
     * @param p The player who has won an attack.
     */
    public void aWonAttack(Player p);
}
