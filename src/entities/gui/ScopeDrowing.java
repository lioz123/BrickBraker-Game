package entities.gui;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Scope drowing.
 */
public class ScopeDrowing implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.drawCircle(WINDOW_WIDTH / 2, 200, 40);
        d.drawCircle(WINDOW_WIDTH / 2, 200, 80);
        d.drawCircle(WINDOW_WIDTH / 2, 200, 120);
        d.fillRectangle(WINDOW_WIDTH / 2, 200 - 144, 2, 120);

        d.fillRectangle(WINDOW_WIDTH / 2, 200 + 24, 2, 120);


        d.fillRectangle(WINDOW_WIDTH / 2 - 142, 200, 120, 2);
        d.fillRectangle(WINDOW_WIDTH / 2 + 22, 200, 120, 2);


    }

    @Override
    public void timePassed() {

    }
}
