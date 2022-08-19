package interfaces;


import entities.Ball;
import helpers.Point;
import helpers.Rectangle;
import helpers.Velocity;

/**
 * The interface Interfaces.Collidable.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * Return the "collision shape" of the object.
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter
     * @param currentVelocity the current velocity
     * @param collisionPoint  the collision point
     * @return the velocity
     */
    Velocity hit(Ball hitter, Velocity currentVelocity, Point collisionPoint);


}