package entities.gui;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Sun drowing.
 */
public class SunDrawing implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {


        d.setColor(Color.YELLOW);
        d.fillCircle(80, 80, 100);
        for (int i = 0; i < 100; i++) {
            d.drawLine(80, 80,   i * 10, 300 );
        }
        d.setColor(Color.ORANGE);
        d.fillCircle(80, 80, 90);
    }

    @Override
    public void timePassed() {

    }
}
