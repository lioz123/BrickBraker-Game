package Enteties;

import biuoop.DrawSurface;
import helpers.*;
import interfaces.*;

/**
 * The type Enteties.Block.
 */
public class Block implements Collidable,Sprite {
    double epsilon = 10.0/((double) (10^12));
    private Rectangle rectangle;
    int dxMulty = -1;
    int dyMulty = -1;
    boolean protectionX = false;
    boolean protectionY = false;
    public void setDxMulty(int dxMulty) {
        this.dxMulty = dxMulty;
    }

    public void setDyMulty(int dyMulty) {
        this.dyMulty = dyMulty;
    }


    public int getDxMulty() {
        return dxMulty;
    }

    public int getDyMulty() {
        return dyMulty;
    }


    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
       // if(protectionY&&collisionPoint.getY()-rectangle.getUpperLeft().getY())
        return new Velocity(currentVelocity.getDx()* dxMulty,currentVelocity.getDy()* dyMulty);
    }






    @Override
    public void drawOn(DrawSurface d) {

        rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {

    }
}
