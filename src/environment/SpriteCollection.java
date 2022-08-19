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
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * Add sprite.
     * a
     *
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
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> sprites = new ArrayList<>(this.sprites);
        for (Sprite sprite : sprites) {

            sprite.drawOn(d);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Clear.
     */
    public void clear() {
        this.sprites = new ArrayList<>();
    }
}
