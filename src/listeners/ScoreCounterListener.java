package listeners;

import entities.Ball;
import entities.Block;
import interfaces.HitListener;

/**
 * The type Score tracking listener.
 */
public class ScoreCounterListener implements HitListener {
    /**
     * Gets current score.
     *
     * @return the current score
     */
    public entities.Counter getCurrentScore() {
        return currentScore;
    }

    private final entities.Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreCounterListener(entities.Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(10);
    }
}