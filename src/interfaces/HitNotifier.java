package interfaces;

/**
 * The interface Interfaces.HitNotifier.
 *
 * @author Lioz Dayan. ID:315155234.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     * Add hl as a listener to hit events.
     * @param hl the hl
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * Remove hl from the list of listeners to hit events.
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}