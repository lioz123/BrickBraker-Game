package screens;

import Enteties.Ball;
import Enteties.Block;
import environment.Game;
import environment.GameEnvironment;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import helpers.Point;
import helpers.Rectangle;
import helpers.Velocity;

import java.awt.*;

public class CollisionTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

//    /**
//     * Draw animation.
//     *
//     * @param start the starting point.
//     * @param angle the angle, the direction of the ball.
//     * @param speed the speed of the ball.
//     */
//    static void drawAnimation(helpers.Point start, double angle, double speed) {
//        GameEnvironment gameEnvironment = new GameEnvironment();
//
//
//
//        GUI gui = new GUI("title", size, size);
//        Sleeper sleeper = new Sleeper();
//        Ball ball = new Ball(start, 30, java.awt.Color.BLACK, gameEnvironment);
//        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
//        ball.setVelocity(v);
//        gameEnvironment.addSprite(ball);
//
//        while (true) {
//            ball.moveOneStep();
//            DrawSurface d = gui.getDrawSurface();
//            gameEnvironment.drawOn(d);
//
//            gui.show(d);
//            sleeper.sleepFor(50);  // wait for 50 milliseconds.
//        }
//    }
}
