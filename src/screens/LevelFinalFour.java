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
import java.util.ArrayList;
import java.util.List;

import static environment.GameLevel.THICKNESS;
import static environment.GameLevel.WINDOW_WIDTH;

/**
 * The type Level final four.
 */
public class LevelFinalFour implements LevelInformation {
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
        return 70;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.white, new SunDrawing());
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        int startY = 100;
        blocks.addAll(RowBuilder.build(THICKNESS, startY, (WINDOW_WIDTH - THICKNESS * 2) / 15,
                20, 15, Color.gray));
        blocks.addAll(RowBuilder.build(THICKNESS, startY + 20, (WINDOW_WIDTH - THICKNESS * 2) / 15,
                20, 15, Color.red));
        blocks.addAll(RowBuilder.build(THICKNESS, startY + 40, (WINDOW_WIDTH - THICKNESS * 2) / 15,
                20, 15, Color.yellow));
        blocks.addAll(RowBuilder.build(THICKNESS, startY + 60, (WINDOW_WIDTH - THICKNESS * 2) / 15,
                20, 15, Color.green));
        blocks.addAll(RowBuilder.build(THICKNESS, startY + 80, (WINDOW_WIDTH - THICKNESS * 2) / 15,
                20, 15, Color.white));
        blocks.addAll(RowBuilder.build(THICKNESS, startY + 100, (WINDOW_WIDTH - THICKNESS * 2) / 15,
                20, 15, Color.pink));
        blocks.addAll(RowBuilder.build(THICKNESS, startY + 120, (WINDOW_WIDTH - THICKNESS * 2) / 15,
                20, 15, Color.lightGray));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15 * 7;
    }
}
