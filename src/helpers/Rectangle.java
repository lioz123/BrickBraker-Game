package helpers;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type helpers.Rectangle.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class Rectangle {
    private helpers.Point upperLeft;
    private final double width;
    private final double height;
    private Color color = null;

    /**
     * Instantiates a new helpers.Rectangle.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(helpers.Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * Instantiates a new helpers.Rectangle.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color.
     */
    public Rectangle(helpers.Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
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
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }

    /**
     * Sets upper left.
     *
     * @param point the point
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }

    /**
     * Intersection points java . util . list.
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<helpers.Point> intersectionPoints(Line line) {
        double m = line.getIncline();
        List<helpers.Point> points = new ArrayList<>();
        double n = line.getConstantParam();
        if (intersectWithPoint(line.start())) {
            double space = 1;
            helpers.Point start = line.start();
            helpers.Point end = line.end();
            if (line.start().getX() > line.end().getX()) {
                end = start;
                space = -space;
            }
            Point current = line.start();
            while (intersectWithPoint(current) && current.getX() <= end.getX()) {
                points.add(current);
                current = new helpers.Point(current.getX() + space, current.getX() * m + n);
            }
        }
        return points;
    }


    /**
     * Intersect with point boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean intersectWithPoint(helpers.Point point) {
        return withingXDimention(point) && withingYDimention(point);
    }

    /**
     * Withing y dimention boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean withingYDimention(helpers.Point point) {
        return point.getY() >= this.upperLeft.getY() && point.getY() <= this.upperLeft.getY() + height;
    }

    /**
     * Withing x dimention boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean withingXDimention(helpers.Point point) {
        return point.getX() >= this.upperLeft.getX() && point.getX() <= this.upperLeft.getX() + width;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public helpers.Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
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
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
