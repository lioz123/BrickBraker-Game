package helpers;

import java.util.List;

/**
 * The type helpers.Line.
 *
 * @author Lioz Dayan. ID:315155234. The type helpers.Line.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Instantiates a new helpers.Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new helpers.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * Length double.
     * Return the length of the line
     *
     * @return the double
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     * Returns the middle point of the line.
     *
     * @return the point
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;

        return new Point(middleX, middleY);
    }

    /**
     * Start point.
     * Returns the start point of the line.
     *
     * @return the point.
     */
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return end;
    }

    /**
     * Is intersecting boolean.
     * Returns true if the lines intersect, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }

        double m1 = getIncline();
        double n1 = getConstantParam();
        double m2 = other.getIncline();
        double n2 = other.getConstantParam();
        // in case the lines have the same incline and the same n constant parameter
        if (m1 == m2) {
            if (n1 == n2) {
                if (isBetween(other.start)) {
                    return true;
                } else if (isBetween(other.end)) {
                    return true;
                } else if (other.isBetween(start)) {
                    return true;
                }
                return other.isBetween(end);
            }

            return false;
        }
        double interX = (n2 - n1) / (m1 - m2);
        double interY = m1 * interX + n1;
        Point interPoint = new Point(interX, interY);
        return isBetween(interPoint) && other.isBetween(interPoint);
    }

    /**
     * isBetween.
     * Check's if a point is withing the line's range.
     *
     * @param point the cutting point.
     * @return true if it's withing range, else false.
     */
    private Boolean isBetween(Point point) {
        if (point == null) {
            return false;
        }
        double maxX = Math.max(start.getX(), end.getX());
        double minX = Math.min(start.getX(), end.getX());
        double maxY = Math.max(start.getY(), end.getY());
        double minY = Math.min(start.getY(), end.getY());
        return point.getX() >= minX && point.getX() <= maxX && point.getY() >= minY && point.getY() <= maxY;
    }

    /**
     * Gets constant param.
     *
     * @return the constant param
     */
    public double getConstantParam() {
        return start.getY() - start.getX() * getIncline();
    }

    /**
     * Gets incline.
     *
     * @return the incline
     */
    public double getIncline() {
        return (start.getY() - end.getY()) / (start.getX() - end.getX());
    }

    /**
     * Intersection with point.
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        double m1 = getIncline();
        double n1 = getConstantParam();
        double m2 = other.getIncline();
        double n2 = other.getConstantParam();
        if (m1 == m2) {
            return null;
        }
        double interX = (n2 - n1) / (m1 - m2);
        double interY = m1 * interX + n1;
        Point interPoint = new Point(interX, interY);
        if (isBetween(interPoint) && other.isBetween(interPoint)) {
            return interPoint;
        }
        return null;
    }

    /**
     * Equals boolean.
     * equals -- return true is the lines are equal, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return other.start.equals(start) && other.end.equals(end) || other.end.equals(start) && other.start.equals(end);
    }

    /**
     * Closest intersection to start of line point.
     * If this line does not intersect with the rectangle, return null.
     *  Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points == null || points.isEmpty()) {
            return null;
        }
        Point bestPoint = points.get(0);
        for (Point point : points) {
            if (bestPoint.distance(start) > point.distance(start)) {
                bestPoint = point;
            }
        }
        return bestPoint;
    }

    /**
     * toString.
     *
     * @return returns a string with the values of the parameter.
     */
    public String toString() {
        return "start: " + this.start.toString() + " end: " + this.end;
    }
}