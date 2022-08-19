package interfaces;

import entities.Ball;
import entities.Block;

/**
 * The interface Hit listener.
 * @author Lioz Dayan. ID:315155234.
 */
public interface HitListener {

    /**
     * Hit event.
     *this method is called whenever the beingHit object is hit.
     *the hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}