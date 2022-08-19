package screens;

import entities.Block;
import entities.gui.Background;
import entities.gui.SunDrawing;
import helpers.RowBuilder;
import helpers.Velocity;
import helpers.VelocityBuilder;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.List;

import static environment.GameLevel.THICKNESS;
import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Level wide easy.
 */
public class LevelWideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return VelocityBuilder.build(10, 0, 0);
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return WINDOW_WIDTH - 200;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.white, new SunDrawing());

    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = RowBuilder.build(THICKNESS, 250, (WINDOW_WIDTH - THICKNESS * 2) / numberOfBlocksToRemove(), 20, numberOfBlocksToRemove(), Color.red);
        blocks.get(2).setColor(Color.orange);
        blocks.get(3).setColor(Color.orange);
        blocks.get(4).setColor(Color.yellow);
        blocks.get(5).setColor(Color.yellow);
        blocks.get(6).setColor(Color.green);
        blocks.get(7).setColor(Color.green);
        blocks.get(8).setColor(Color.green);
        blocks.get(9).setColor(Color.blue);
        blocks.get(10).setColor(Color.blue);
        blocks.get(11).setColor(Color.pink);
        blocks.get(12).setColor(Color.pink);
        blocks.get(13).setColor(Color.lightGray);
        blocks.get(14).setColor(Color.lightGray);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
