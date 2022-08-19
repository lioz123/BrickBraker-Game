package entities.gui;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.*;
import java.util.ArrayList;

import static environment.GameLevel.WINDOW_HEIGHT;
import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Background.
 */
public class Background implements Sprite {
    private final Sprite sprite;
    private final Color color;

    /**
     * Instantiates a new Background.
     *
     * @param color  the color
     * @param sprite the sprite
     */
    public Background(Color color, Sprite sprite) {
        this.sprite = sprite;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        sprite.drawOn(d);

    }

    @Override
    public void timePassed() {
        sprite.timePassed();
    }
}
