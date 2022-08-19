package screens;

import entities.Block;
import entities.gui.Background;
import entities.gui.ScopeDrowing;
import helpers.RowBuilder;
import helpers.Velocity;
import helpers.VelocityBuilder;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.List;

import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return VelocityBuilder.build(1, 1, 1);
    }

    @Override
    public int paddleSpeed() {
        return 20;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.black, new ScopeDrowing());
    }

    @Override
    public List<Block> blocks() {
        return RowBuilder.build(WINDOW_WIDTH / 2 - 20, 190, 40, 20, numberOfBlocksToRemove(), Color.red);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
