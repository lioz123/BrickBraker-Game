package listeners;

import entities.Ball;
import entities.Block;
import entities.Counter;
import environment.GameLevel;
import interfaces.HitListener;

/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;

    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }

    /**
     * Gets counting blocks.
     *
     * @return the counting blocks
     */
    public Counter getCountingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Sets remaining blocks.
     *
     * @param remainingBlocks the remaining blocks
     */
    public void setRemainingBlocks(int remainingBlocks) {
        this.remainingBlocks.set(remainingBlocks);
    }
}