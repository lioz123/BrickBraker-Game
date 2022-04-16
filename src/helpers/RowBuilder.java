package helpers;

import Enteties.Block;
import environment.Game;

import java.awt.*;

/**
 * The type Row builder.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class RowBuilder {
    /**
     * Build.
     * Builds a row of blocks.
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param number the number
     * @param color  the color
     * @param game   the game
     */
    public static void Build(int x, int y, int width, int height, int number, Color color, Game game) {
        int space = 2;
        for (int i = 0; i < number; i++) {
            Block block;
            if(i==0){
                 block =new Block(new Rectangle(new Point(x + width*i+space/2 , y), width-space, height, color));

            }else{
                 block =new Block(new Rectangle(new Point(x + width*i+space , y), width-space, height, color));

            }
            block.setDxMulty(1);
            game.addSprite(block);
            game.addCollidable(block);

        }

    }
}
