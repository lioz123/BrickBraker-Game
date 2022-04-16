package helpers;

/**
 * @author Lioz Dayan. ID:315155234.
 * The type helpers.Velocity.
 * helpers.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Create a new Instance of helpers.Velocity from angle and speed.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle);
        double dy = -speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }
    /**
     * Instantiates a new helpers.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply to point.
     * Take a point with position (x,y) and return a new point.
     * @param p the that needs to be changed.
     * @return the changed point
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        }
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    public double getAngle() {
        return Math.atan(getDx()/getDy());
    }

    public double getSpeed(){
        return Math.sqrt(Math.pow(dx,2)  + Math.pow(dy,2));
    }
}