import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Bouncing ball animation.
 * Will show a screen with a bouncing ball on it.
 * @author Lioz Dayan. ID:315155234. The type Bouncing ball animation.
 */
public class BouncingBallAnimation {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        drawAnimation(new Point(6, 8), 40, 20);
    }

    /**
     * Draw animation.
     *
     * @param start the starting point.
     * @param angle the angle, the direction of the ball.
     * @param speed the speed of the ball.
     */
    static void drawAnimation(Point start, double angle, double speed) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Frame frame = new Frame(0, 0, 200, 200, Color.white);
        Ball ball = new Ball(start, 30, java.awt.Color.BLACK, frame);
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        ball.setVelocity(v);
        while (true) {

            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
