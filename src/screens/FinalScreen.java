package screens;

import biuoop.DrawSurface;
import environment.AnimationRunner;
import interfaces.Animation;
import listeners.KeyPressStoppableAnimation;

import java.awt.*;

import static environment.GameLevel.WINDOW_HEIGHT;
import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Final screen.
 */
public class FinalScreen implements Animation {
    private final String message;

    /**
     * Instantiates a new Final screen.
     *
     * @param message the message
     */
    public FinalScreen(String message) {
        this.message = message;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        d.setColor(Color.white);
        d.drawText(WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2, message, 24);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
