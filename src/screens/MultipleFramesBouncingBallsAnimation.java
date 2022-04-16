package screens;

import Enteties.Ball;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import helpers.Point;
import helpers.*;
import interfaces.*;
import java.awt.Color;

/**
 * The type Multiple frames bouncing balls animation.
 *
 * @author Lioz Dayan. ID:315155234. The type Multiple frames bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int windowWidth = 1100;
        int windowHeight = 1000;

        Frame frame1 = Frame.fromPoints(new Point(50, 50), new Point(500, 500), Color.gray);
        Frame frame2 = Frame.fromPoints(new Point(450, 450), new Point(600, 600), Color.yellow);
        Frame[] frames = {frame1, frame2};
        drawAnimation(Ball.generateBalls(args, frames), windowWidth, windowHeight, frames);
    }


    /**
     * Draw animation.
     *
     * @param balls        the balls
     * @param windowWidth  the window width
     * @param windowHeight the window height
     * @param frames       the frames
     */
    static void drawAnimation(Ball[] balls, int windowWidth, int windowHeight, Frame[] frames) {
        GUI gui = new GUI("title", windowWidth, windowHeight);
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Frame frame : frames) {
                frame.drawOn(d);
            }
            for (Ball ball : balls) {

                ball.drawOn(d);
                ball.moveOneStep();

            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.

        }
    }


}
