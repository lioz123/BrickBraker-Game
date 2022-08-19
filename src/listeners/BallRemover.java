package listeners;

import entities.Ball;
import entities.Block;
import entities.Counter;
import environment.GameLevel;
import interfaces.HitListener;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter counter;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game    the game
     * @param counter the counter
     */
    public BallRemover(GameLevel game, Counter counter) {
        this.game = game;
        this.counter = counter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeBall(hitter);
        counter.decrease(1);
    }
}
