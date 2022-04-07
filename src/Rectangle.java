import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        double m = line.getIncline();
        List<Point> points = new List<Point>();
        double n = line.getConstantParam();
        if (IntersectWithPoint(line.start())) {
            double space = 1;
            if (line.start().getX() > line.end()) {
                space = -space;
            }
            Point current = line.start();
            while (IntersectWithPoint(current) && current.getX() <= line.end().getX()) {
                points.add(current);
                current = new Point(current.getX() + space, current.getX() * m + n);
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
    public boolean IntersectWithPoint(Point point) {
        return withingXDimention(point) && withingYDimention(point);
    }

    /**
     * Withing y dimention boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean withingYDimention(Point point) {
        return point.getY() >= this.upperLeft.getY() && point.getY() <= this.upperLeft.getY() + height;
    }

    /**
     * Withing x dimention boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean withingXDimention(Point point) {
        return point.getX() >= this.upperLeft.getX() && point.getX() <= this.upperLeft.getX() + width;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
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
}
