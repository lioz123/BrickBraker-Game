package entities;

import biuoop.DrawSurface;
import environment.GameLevel;
import helpers.Point;
import helpers.Rectangle;
import helpers.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Enteties.Block.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private int dxMultiplier = -1;
    private int dyMultiplier = -1;
    private final ArrayList<HitListener> hitListeners = new ArrayList<>();

    /**
     * Sets dx multiplier.
     *
     * @param dxMultiplier the dx multiplier
     */
    public void setDxMultiplier(int dxMultiplier) {
        this.dxMultiplier = dxMultiplier;
    }

    /**
     * Sets dy multiplier.
     *
     * @param dyMultiplier the dy multiplier
     */
    public void setDyMultiplier(int dyMultiplier) {
        this.dyMultiplier = dyMultiplier;
    }


    /**
     * Gets dx multiplier.
     *
     * @return the dx multiplier
     */
    public int getDxMultiplier() {
        return dxMultiplier;
    }

    /**
     * Gets dy multiplierplayer.
     *
     * @return the dy multiplier
     */
    public int getDyMultiplier() {
        return dyMultiplier;
    }


    /**
     * Instantiates a new Block.
     *
     * @param rectangle the rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }


    @Override
    public Velocity hit(Ball hitter, Velocity currentVelocity, Point collisionPoint) {
        this.notifyHit(hitter);
        return new Velocity(currentVelocity.getDx() * dxMultiplier, currentVelocity.getDy() * dyMultiplier);
    }

    /**
     * Remove from game.
     * removes this block from the game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d);
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * notify hit.
     * Notify all listeners about a hit event.
     * Make a copy of the hitListeners before iterating over them.
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);

    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        rectangle.setColor(color);
    }
}
