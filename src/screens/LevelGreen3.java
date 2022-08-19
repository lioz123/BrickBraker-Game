package screens;

import entities.Block;
import entities.gui.Background;
import entities.gui.Tower;
import helpers.RowBuilder;
import helpers.Velocity;
import helpers.VelocityBuilder;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static environment.GameLevel.THICKNESS;
import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Level green 3.
 */
public class LevelGreen3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return VelocityBuilder.build(0, 2, 0);
    }

    @Override
    public int paddleSpeed() {
        return 30;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.green, new Tower());
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        int space = 40 + 2;
        blocks.addAll(RowBuilder.buildMirror(WINDOW_WIDTH - THICKNESS * 2, 300, 40, 20, 9, Color.gray));
        blocks.addAll(RowBuilder.buildMirror(WINDOW_WIDTH - THICKNESS * 2, 320, 40, 20, 8, Color.red));
        blocks.addAll(RowBuilder.buildMirror(WINDOW_WIDTH - THICKNESS * 2, 340, 40, 20, 7, Color.yellow));
        blocks.addAll(RowBuilder.buildMirror(WINDOW_WIDTH - THICKNESS * 2, 360, 40, 20, 6, Color.blue));
        blocks.addAll(RowBuilder.buildMirror(WINDOW_WIDTH - THICKNESS * 2, 380, 40, 20, 5, Color.white));

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 35;
    }
}
