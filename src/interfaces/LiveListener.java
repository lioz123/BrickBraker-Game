package interfaces;

/**
 * The interface Live listener.
 */
public interface LiveListener {
    /**
     * On death.
     */
    void decreaseLives();

    /**
     * Gets lives.
     *
     * @return the lives
     */
    int getLives();
}