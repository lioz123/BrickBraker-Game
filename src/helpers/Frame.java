package helpers;

import Enteties.Ball;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type helpers.Frame.
 *
 * @author Lioz Dayan. ID:315155234. The type helpers.Frame. Responsible for holding balls with the frame's bounderies.
 */
public class Frame {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Color color;

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * From points frame.
     * Builder function that creates a frame between 2 points.
     *
     * @param start the start
     * @param end   the end
     * @param color the color
     * @return the frame
     */
    public static Frame fromPoints(Point start, Point end, Color color) {
        int x = (int) start.getX();
        int y = (int) start.getY();
        int height = (int) (end.getY() - start.getX());
        int width = (int) (end.getX() - start.getX());
        return new Frame(x, y, width, height, color);

    }

    /**
     * Instantiates a new helpers.Frame.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Frame(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }
        /**
     * Intersect.
     * Responsible for holding a ball withing the frame boundaries.
     *
     * @param ball the ball
     */
    public void intersects(Ball ball) {
        int safeDistance = 1 + ball.getRadius();
        int xBoundery = this.x + safeDistance;
        int yBoundery = this.y + safeDistance;
        int widthBoundery = this.width - safeDistance + this.x;
        int heightBoundary = this.height - safeDistance + this.y;
        if (ball.getX() >= widthBoundery || ball.getX() <= xBoundery) {
            ball.setVelocity(new Velocity(-ball.getVelocity().getDx(), ball.getVelocity().getDy()));
            if (ball.getX() >= widthBoundery) {
                ball.setCenter(new Point(widthBoundery, ball.getY()));
            } else {
                ball.setCenter(new Point(xBoundery, ball.getY()));
            }
        }

        if (ball.getY() >= heightBoundary || ball.getY() <= yBoundery) {
            ball.setVelocity(new Velocity(ball.getVelocity().getDx(), -ball.getVelocity().getDy()));
            if (ball.getY() >= heightBoundary) {
                ball.setCenter(new Point(ball.getX(), heightBoundary));
            } else {
                ball.setCenter(new Point(ball.getX(), yBoundery));
            }
        }
    }
    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        if (color == null) {
            return;
        }
        surface.setColor(color);
        surface.fillRectangle(x, y, width, height);
    }


}
