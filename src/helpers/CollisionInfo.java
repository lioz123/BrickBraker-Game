package helpers;

import interfaces.Collidable;

/**
 * The type Collision info.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class CollisionInfo {
    private final Point point;
    private final Collidable object;

    /**
     * Instantiates a new Collision info.
     *
     * @param point  the point
     * @param object the object
     */
    public CollisionInfo(Point point, Collidable object) {
        this.object = object;
        this.point = point;
    }

    /**
     * Collision point point.
     * the point at which the collision occurs.
     *
     * @return the point\
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Collision object collidable.
     * the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.object;
    }
}