package screens;

import biuoop.DrawSurface;
import environment.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private SpriteCollection gameScreen;
    private int countFrom;
    private int frameCounter = 0;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.countFrom = countFrom;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.orange);
        d.drawText(400, 400, "Game Starts In:" + countFrom + " Seconds", 24);

        if (frameCounter == 0) {
            countFrom--;
        }
        frameCounter++;
        if (frameCounter == 24 * 2) {
            frameCounter = 0;
            numOfSeconds--;
        }


    }

    @Override
    public boolean shouldStop() {
        if (countFrom == 0) {
            return true;
        }
        return false;
    }
}
