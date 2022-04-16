package environment;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class SpriteCollection {
    private final List<Sprite> sprites = new ArrayList<>();

    /**
     * Add sprite.
     *a
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on.
     * call drawOn(d) on all sprites.
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }


}
