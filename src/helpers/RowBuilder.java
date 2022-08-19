package helpers;

import entities.Block;
import environment.GameLevel;

import java.awt.Color;
import java.util.ArrayList;


/**
 * The type Row builder.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class RowBuilder {
    /**
     * Build.
     * Builds a row of blocks.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param number the number
     * @param color  the color
     * @param game   the game
     */
    public static void build(int x, int y, int width, int height, int number, Color color, GameLevel game) {
        int space = 2;
        int divSpace = 1;
        for (int i = 0; i < number; i++) {
            Block block;
            if (i == 0) {
                block = new Block(new Rectangle(new Point(x + divSpace, y), width - space, height, color));

            } else {
                block = new Block(new Rectangle(new Point(x + width * i + space, y), width - space, height, color));
            }
            block.addHitListener(game.getBlockRemover());
            game.getBlockRemover().getCountingBlocks().increase(1);
            block.setDxMultiplier(1);
            block.addHitListener(game.getScoreTrackingListener());
            game.addSprite(block);
            game.addCollidable(block);

        }

    }

    /**
     * Build array list.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param number the number
     * @param color  the color
     * @return the array list
     */
    public static ArrayList<Block> build(int x, int y, int width, int height, int number, Color color) {
        int space = 2;
        int divSpace = 1;
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Block block;
            if (i == 0) {
                block = new Block(new Rectangle(new Point(x + divSpace, y), width - space, height, color));

            } else {
                block = new Block(new Rectangle(new Point(x + width * i + space, y), width - space, height, color));
            }
            block.setDxMultiplier(1);
            blocks.add(block);
        }
        System.out.println(blocks.size());
        return blocks;
    }

    /**
     * Build mirror array list.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param number the number
     * @param color  the color
     * @return the array list
     */
    public static ArrayList<Block> buildMirror(int x, int y, int width, int height, int number, Color color) {
        int space = 2;
        int divSpace = 1;
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Block block;
            if (i == 0) {
                block = new Block(new Rectangle(new Point(x - divSpace, y), width + space, height, color));

            } else {
                block = new Block(new Rectangle(new Point(x - width * i - space, y), width + space, height, color));
            }
            block.setDxMultiplier(1);
            blocks.add(block);
        }
        System.out.println(blocks.size());
        return blocks;
    }
}
