package Enteties;

import biuoop.DrawSurface;
import helpers.Point;
import helpers.Rectangle;
import helpers.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;


/**
 * The type Enteties.Block.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class Block implements Collidable, Sprite {
    private  Rectangle rectangle;
    private   int dxMultiplier = -1;
    private  int dyMultiplier = -1;

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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        return new Velocity(currentVelocity.getDx() * dxMultiplier, currentVelocity.getDy() * dyMultiplier);
    }


    @Override
    public void drawOn(DrawSurface d) {

        rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {

    }
}
