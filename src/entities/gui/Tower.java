package entities.gui;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

import static environment.GameLevel.THICKNESS;
import static environment.GameLevel.WINDOW_HEIGHT;

/**
 * The type Tower.
 */
public class Tower implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(40 + THICKNESS, WINDOW_HEIGHT - 200, 150, 200);

        d.setColor(Color.gray);
        d.fillRectangle(40 + THICKNESS + 75 - 17, WINDOW_HEIGHT - 240, 34, 40);

        d.fillRectangle(40 + THICKNESS + 75 - 17 + 5, WINDOW_HEIGHT - 360, 20, 120);
        d.setColor(Color.red);
        d.fillCircle(40 + THICKNESS + 75 - 17 + 11, WINDOW_HEIGHT - 380, 20);
        d.setColor(Color.white);
        d.fillCircle(40 + THICKNESS + 75 - 17 + 11, WINDOW_HEIGHT - 380, 5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(40 + THICKNESS + j * 25 + 10, WINDOW_HEIGHT - 200 + i * 45 + 20, 20, 40);
            }
        }
    }

    @Override
    public void timePassed() {

    }
}
