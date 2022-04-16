package interfaces;


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
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);


}