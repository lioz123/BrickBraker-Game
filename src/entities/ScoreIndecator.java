package entities;

import biuoop.DrawSurface;
import helpers.Rectangle;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Score.
 */
public class ScoreIndecator implements Sprite {
    private final Rectangle rectangle;
    private final Counter scoreCounter;


    /**
     * Instantiates a new Score.
     *
     * @param rectangle             the rectangle
     * @param scoreCounter the score tracking listener
     */
    public ScoreIndecator(Rectangle rectangle, Counter scoreCounter) {
        this.rectangle = rectangle;
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(((int) this.rectangle.getUpperLeft().getX()), ((int) this.rectangle.getUpperLeft().getY()),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawText((int) rectangle.getWidth() / 2, (int) rectangle.getHeight() / 2, "Score: "
                + scoreCounter.getValue(), 15);


    }

    @Override
    public void timePassed() {

    }
}
