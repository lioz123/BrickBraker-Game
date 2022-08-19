package screens;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * The type Pause screen second.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
