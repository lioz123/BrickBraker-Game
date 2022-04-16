package Enteties;

import biuoop.DrawSurface;
import environment.GameEnvironment;
import helpers.CollisionInfo;
import helpers.Line;
import helpers.Point;
import helpers.Velocity;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Enteties.Ball.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class Ball implements Sprite {
    /**
     * The Game environment.
     */
    private GameEnvironment gameEnvironment;


    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;


    /**
     * Instantiates a new Enteties.Ball.
     *
     * @param center          the center
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }


    /**
     * Instantiates a new Enteties.Ball.
     * Note that you are not supposed to use this constructor.
     * Only in case of autoTesting.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        velocity = new Velocity(0, 0);
    }


    /**
     * Sets center.
     *
     * @param center the center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Move one step.
     * moving the ball one step.
     * Will handle intersection with boundries.
     * If out of boundary, the velocity will change.
     */
    public void moveOneStep() {
        int endXDistance = radius, endYDistance = -radius;
        if (velocity.getDx() < 0) {
            endXDistance = -endXDistance;
        }
        if (velocity.getDy() < 0) {
            endYDistance = -endYDistance;
        }
        Point start = this.center;

        Point end = new Point(start.getX() + velocity.getDx() + endXDistance,
                start.getY() + velocity.getDy() + endYDistance);
        Line trajectory = new Line(start, end);

        CollisionInfo info = gameEnvironment.getClosestCollision(trajectory);

        if (info != null) {
            Velocity formerVelocity = velocity;
            setVelocity(info.collisionObject().hit(info.collisionPoint(), velocity));
            setCenter(velocity.applyToPoint(info.collisionPoint()));
            protectFromColission(info, formerVelocity);
            return;
        }


        setCenter(this.getVelocity().applyToPoint(this.center));


    }

    private void protectFromColission(CollisionInfo info, Velocity formerVelocity) {
        CollisionInfo neoInfo = gameEnvironment.getClosestCollision(new Line(center, center));
        if (neoInfo != null) {
            setVelocity(new Velocity(-formerVelocity.getDx(), -formerVelocity.getDy()));
            setCenter(velocity.applyToPoint(info.collisionPoint()));
        }
    }


    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y location.
     *
     * @return the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return radius * 2;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), radius);
        surface.setColor(Color.red);
        surface.fillCircle(getX(), getY(), 1);
    }

    @Override
    public void timePassed() {

    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

}