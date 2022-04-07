import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The type Ball.
 *
 * @author Lioz Dayan. ID:315155234. The type Ball.
 */
public class Ball {

    /**
     * Generate balls ball [ ].
     *
     * @param sizeList the size list
     * @param frames   frames to insert the balls to.
     * @return the ball [ ]
     */
    static Ball[] generateBalls(String[] sizeList, Frame[] frames) {
        if (frames == null || sizeList == null) {
            return null;
        }
        Ball[] balls = new Ball[sizeList.length];
        Random rnd = new Random();
        int maxAngle = 360;
        for (int i = 0; i < sizeList.length; i++) {
            for (int j = 1; j <= frames.length; j++) {
                if (((j * sizeList.length) / frames.length) > i) {

                    int size = Integer.parseInt(sizeList[i]);
                    int angle = rnd.nextInt(maxAngle);
                    int x = rnd.nextInt(frames[j - 1].getWidth() + frames[j - 1].getX());
                    int y = rnd.nextInt(frames[j - 1].getWidth() + frames[j - 1].getY());

                    int speed = 15;
                    int bigSize = 50;
                    if (size >= bigSize) {
                        speed -= 10;
                    } else {
                        speed -= size / 5;
                    }
                    Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                    Ball ball = new Ball(new Point(x, y), size, Color.blue, frames[j - 1]);
                    ball.setVelocity(v);
                    balls[i] = ball;
                    break;
                }
            }

        }

        return balls;
    }

    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private Frame frame;

    /**
     * Instantiates a new Ball.
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
     * Instantiates a new Ball.
     * Note that you are not supposed to use this constructor.
     * Only in case of autoTesting.
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
        setCenter(this.getVelocity().applyToPoint(this.center));
        frame.intersects(this);
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
     * Gets y.
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