import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The type Multiple bouncing balls animation.
 *
 * @author Lioz Dayan. ID:315155234. The type Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final int windowWidth = 400;
        final int windowHeight = 400;
        Frame frame1 = new Frame(0, 0, windowWidth, windowHeight, null);
        Frame[] frames = {frame1};
        drawAnimation(Ball.generateBalls(args, frames), windowWidth, windowHeight);
    }


//    /**
//     * Generate balls ball [ ].
//     * Generates a list of balls, with random locations, speed, angle.
//     * The bigger the ball the slower he is.
//     * @param sizeList     the size list
//     * @param frame   the frame
//     * @param windowWidth  the window width
//     * @param windowHeight the window height
//     * @return the ball [ ]
//     */
//    static Ball[] generateBalls(String[] sizeList, Frame frame, int windowWidth, int windowHeight) {
//        Ball[] balls = new Ball[sizeList.length];
//        Random rnd = new Random();
//        for (int i = 0; i < sizeList.length; i++) {
//            int size = Integer.parseInt(sizeList[i]);
//            int angle = rnd.nextInt(360);
//            int x = rnd.nextInt(windowWidth);
//            int y = rnd.nextInt(windowHeight);
//
//            int speed = 10;
//            if (size >= 50) {
//                speed -= 10;
//            } else {
//                speed -= size / 5;
//            }
//            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
//            Ball ball = new Ball(new Point(x, y), size, Color.blue, frame);
//            ball.setVelocity(v);
//            balls[i] = ball;
//        }
//        return balls;
//    }

    /**
     * Draw animation.
     *
     * @param balls        the balls
     * @param windowWidth  the window width
     * @param windowHeight the window height
     */
    static void drawAnimation(Ball[] balls, int windowWidth, int windowHeight) {
        GUI gui = new GUI("title", windowWidth, windowHeight);
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {

                ball.drawOn(d);
                ball.moveOneStep();

            }
            gui.show(d);
            sleeper.sleepFor(50);

        }
    }


}