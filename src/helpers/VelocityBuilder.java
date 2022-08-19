package helpers;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Velocity builder.
 */
public class VelocityBuilder {
    /**
     * Build.
     *
     * @param numberOfSlowBalls  the number of slow balls
     * @param numberOfFastBalls  the number of fast balls
     * @param numberOfSuperBalls the super balls
     * @return the array list
     */
    public static ArrayList<Velocity> build(int numberOfSlowBalls, int numberOfFastBalls, int numberOfSuperBalls) {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.addAll(generateBallsVelocity(numberOfSlowBalls, 5));
        velocities.addAll(generateBallsVelocity(numberOfFastBalls, 7));
        velocities.addAll(generateBallsVelocity(numberOfSuperBalls, 9));
        return velocities;
    }

    /**
     * @param numOfBalls
     * @param speed
     * @return
     */
    private static ArrayList<Velocity> generateBallsVelocity(int numOfBalls, int speed) {
        ArrayList<Velocity> velocities = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < numOfBalls; i++) {
            int angle = rnd.nextInt(120) + 60;
            Velocity velocity = Velocity.fromAngleAndSpeed(Math.toRadians(angle), speed);
            velocities.add(velocity);
        }
        return velocities;
    }
}
