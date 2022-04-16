package interfaces;

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public interface Sprite {
    /**
     * Draw on.
     * draw the sprite to the screen.
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notify the sprite that time has passed.
     */
    void timePassed();
}