/**
 * The type Point.
 *
 * @author Lioz Dayan. ID:315155234. The type Point.
 */
public class Point {
    private double x, y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x.
     * @param y the y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * distance -- return the distance of this point to the other point.
     *
     * @param other the other.
     * @return the double.
     */
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }


    /**
     * Equals boolean.
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other the other.
     * @return the boolean.
     */
    public boolean equals(Point other) {
        return other.x == x && other.y == y;
    }

    /**
     * Gets x.
     *
     * @return the x.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y.
     */
    public double getY() {
        return y;
    }

    /**
     * toString.
     * @return the object as a String object, inorder to print his params.
     */
    public String toString() {
        return "x: " + this.x + " y: " + y;
    }
}