package entities.gui;

import biuoop.DrawSurface;
import interfaces.LiveListener;
import interfaces.Sprite;

import java.awt.Color;


/**
 * The type Lives.
 */
public class Lives implements Sprite {
    private final LiveListener listener;

    /**
     * Instantiates a new Lives.
     *
     * @param listener the listener
     */
    public Lives(LiveListener listener) {
        this.listener = listener;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(100, 20, "Lives: " + listener.getLives(), 15);
    }

    @Override
    public void timePassed() {

    }
}
