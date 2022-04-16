package Enteties;

import biuoop.DrawSurface;
import environment.GameEnvironment;
import interfaces.Sprite;

import java.awt.Color;
import java.util.Random;

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

    /**
     * Generate balls ball [ ].
     *
     * @param sizeList the size list
     * @param frames   frames to insert the balls to.
     * @return the ball [ ]
     */
//    public static Ball[] generateBalls(String[] sizeList, Frame[] frames) {
//        if (frames == null || sizeList == null) {
//            return null;
//        }
//        Ball[] balls = new Ball[sizeList.length];
//        Random rnd = new Random();
//        int maxAngle = 360;
//        for (int i = 0; i < sizeList.length; i++) {
//            for (int j = 1; j <= frames.length; j++) {
//                if (((j * sizeList.length) / frames.length) > i) {
//
//                    int size = Integer.parseInt(sizeList[i]);
//                    int angle = rnd.nextInt(maxAngle);
//                    int x = rnd.nextInt(frames[j - 1].getWidth() + frames[j - 1].getX());
//                    int y = rnd.nextInt(frames[j - 1].getWidth() + frames[j - 1].getY());
//
//                    int speed = 15;
//                    int bigSize = 50;
//                    if (size >= bigSize) {
//                        speed -= 10;
//                    } else {
//                        speed -= size / 5;
//                    }
//                    Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
//                    Ball ball = new Ball(new Point(x, y), size, Color.blue, frames[j - 1]);
//                    ball.setVelocity(v);
//                    balls[i] = ball;
//                    break;
//                }
//            }
//
//        }
//
//        return balls;
//    }

    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private Frame frame;

    /**
     * Instantiates a new Enteties.Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     * @param frame  the frame
     */
    public Ball(Point center, int r, Color color, Frame frame) {
        this.center = center;
        this.radius = r;
        this.color = color;
        velocity = new Velocity(0, 0);
        this.frame = frame;
    }

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
        int defaultSize = 200;
        this.frame = new Frame(0, 0, defaultSize, defaultSize, null);
    }

    /**
     * Sets frame.
     *
     * @param frame the frame
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
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
        int safeDistance = 1, endXDistance = radius, endYDistance = -radius;
        if (velocity.getDx() < 0) {
            endXDistance = -endXDistance;
        }
        if (velocity.getDy() < 0) {
            endYDistance = -endYDistance;
        }
        Point start = this.center;

        Point end = new Point(start.getX() + velocity.getDx() + endXDistance, start.getY() + velocity.getDy() + endYDistance);
        Line trajectory = new Line(start, end);

        CollisionInfo info = gameEnvironment.getClosestCollision(trajectory);

        if (info != null) {
            double m = trajectory.getIncline();
            double n = trajectory.getConstantParam();
            int space = 3;
            if (trajectory.end().getX() < start.getX()) {
                space = -space;
            }
            Velocity formerVelocity = velocity;

            setVelocity(info.collisionObject().hit(info.collisionPoint(), velocity));
            setCenter(velocity.applyToPoint(info.collisionPoint()));
            CollisionInfo neoInfo = gameEnvironment.getClosestCollision(new Line(center, center));
            if (neoInfo != null) {
                setVelocity(new Velocity(-formerVelocity.getDx(), -formerVelocity.getDy()));
                setCenter(velocity.applyToPoint(info.collisionPoint()));

            }
            return;
        }


        setCenter(this.getVelocity().applyToPoint(this.center));


        //frame.intersects(this);
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
     * Gets y.e
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