package screens;

import biuoop.DrawSurface;
import biuoop.GUI;
import helpers.Line;
import helpers.Point;

import java.awt.Color;
import java.util.Random;

/**
 * The type Abstract art drawing.
 *
 * @author Lioz Dayan. ID:315155234. The type Abstract art drawing.
 */
public class AbstractArtDrawing {
    private final int width = 400;
    private final int height = 300;
    private final int radius = 3;

    /**
     * Draw random lines.
     */
    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        GUI gui = new GUI("Random Circles Example", width, height);
        DrawSurface d = gui.getDrawSurface();
        int linesLength = 10;
        Line[] lines = new Line[linesLength];
        for (int i = 0; i < linesLength; ++i) {
            Line line = drawRandomLine(rand, d);
            lines[i] = line;
            drawIntersections(d, lines, i, line);
        }
        gui.show(d);
    }

    /**
     * drawIntersections.
     * @param d DrawSurface.
     * @param lines lines array.
     * @param end number of current lines.
     * @param line a line to compare with.
     */
    private void drawIntersections(DrawSurface d, Line[] lines, int end, Line line) {
        d.setColor(Color.RED);
        for (int j = 0; j < end; j++) {
            if (line.isIntersecting(lines[j])) {
                Point interPoint = line.intersectionWith(lines[j]);
                d.fillCircle((int) interPoint.getX(), (int) interPoint.getY(), radius);
            }
        }
    }

    /**
     * drawRandomLine.
     * @param rand Random object.
     * @param d DrawSurface.
     * @return generated random line.
     */
    private Line drawRandomLine(Random rand, DrawSurface d) {
        d.setColor(Color.black);
        int x1 = rand.nextInt(width) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(height) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(width) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(height) + 1; // get integer in range 1-300
        Line line = new Line(new Point(x1, y1), new Point(x2, y2));

        d.drawLine(x1, y1, x2, y2);

        d.setColor(Color.blue);
        d.fillCircle((int) line.middle().getX(), (int) line.middle().getY(), radius);
        return line;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}
