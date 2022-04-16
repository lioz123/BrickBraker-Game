package environment;

import helpers.CollisionInfo;
import helpers.Line;
import helpers.Point;
import interfaces.Collidable;
import biuoop.DrawSurface;
import helpers.*;
import interfaces.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    public GameEnvironment() {
        this.collidables = new ArrayList<>();

           }

    /**
     * Add collidable.
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }


    /**
     * Get closest collision collision info.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the collision info
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        boolean collided = false;
        Collidable closestObject = null;
        Point closestPoint = null;
        for (Collidable collidable : collidables) {
            List<Point> collisionPoints = collidable.getCollisionRectangle().intersectionPoints(trajectory);
            if (!collided && collisionPoints != null && !collisionPoints.isEmpty()) {
                closestObject = collidable;
                closestPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                collided = true;
            } else if (collisionPoints != null) {
                Point neoPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                if (neoPoint == null) {
                    continue;
                }
                if (neoPoint.distance(trajectory.end()) < closestPoint.distance(trajectory.end())) {
                    closestObject = collidable;
                    closestPoint = neoPoint;
                }
            }
        }
        if (closestObject == null) {
            return null;
        }
        System.out.println(closestObject.toString());
        return new CollisionInfo(closestPoint, closestObject);
    }


}
