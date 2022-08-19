package environment;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import interfaces.Animation;

import static environment.GameLevel.WINDOW_HEIGHT;
import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private final GUI gui = new GUI("", WINDOW_WIDTH, WINDOW_HEIGHT);
    private int framesPerSecond;
    private final Sleeper sleeper = new Sleeper();
    private String title;
    private  final int millisecondsPerFrame = 24;

    /**
     * Gets milliseconds per frame.
     *
     * @return the milliseconds per frame
     */
    public int getMillisecondsPerFrame() {
        return millisecondsPerFrame;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }


    /**
     * Gets key board sensor.
     *
     * @return the key board sensor
     */
    public KeyboardSensor getKeyBoardSensor() {
        return gui.getKeyboardSensor();
    }


    /**
     * Sets title.
     *
     * @param levelName the level name
     */
    public void setTitle(String levelName) {
        this.title = levelName;
    }

    /**
     * Gets draw surface.
     *
     * @return the draw surface
     */
    public DrawSurface getDrawSurface() {
        return gui.getDrawSurface();
    }

    /**
     * Close.
     */
    public void close() {
        gui.close();
    }
}
