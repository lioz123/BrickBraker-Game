package environment;


import entities.*;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import entities.gui.Lives;
import helpers.Point;
import helpers.Rectangle;
import helpers.RowBuilder;
import helpers.Velocity;
import interfaces.*;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.KeyPressStoppableAnimation;
import listeners.ScoreCounterListener;
import screens.CountdownAnimation;
import screens.PauseScreen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Game.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class GameLevel implements Animation {

    /**
     * The constant BALL_RADIUS.
     */
    public static final int BALL_RADIUS = 5;
    /**
     * The constant WINDOW_WIDTH.
     */
    public static final int WINDOW_WIDTH = 800;
    /**
     * The constant WINDOW_HEIGHT.
     */
    public static final int WINDOW_HEIGHT = 600;
    /**
     * The constant THICKNESS.
     */
    public static final int THICKNESS = 40;

    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;
    private Lives lives;
    private entities.Counter blocksCounter;
    private entities.Counter ballsCounter;
    private ScoreIndecator score;
    private entities.Counter scoreCounter;
    private ScoreCounterListener scoreTrackingListener;
    private BlockRemover blockRemover;
    private KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
    private Sprite background;
    private LiveListener liveListener;
    private String title;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation the level information
     * @param keyboardSensor   the keyboard sensor
     * @param animationRunner  the animation runner
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.levelInformation = levelInformation;
        this.background = levelInformation.getBackground();
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Gets game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * Gets paddle.
     *
     * @return the paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Gets blocks counter.
     *
     * @return the blocks counter
     */
    public entities.Counter getBlocksCounter() {
        return blocksCounter;
    }

    /**
     * Gets block remover.
     *
     * @return the block remover
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * Gets balls counter.
     *
     * @return the balls counter
     */
    public entities.Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * Gets score counter.
     *
     * @return the score counter
     */
    public entities.Counter getScoreCounter() {
        return scoreCounter;
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
    public ArrayList<Ball> getBalls() {
        return balls;
    }

    /**
     * Gets balls remover.
     *
     * @return the balls remover
     */
    public BallRemover getBallsRemover() {
        return ballsRemover;

    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public ScoreIndecator getScore() {
        return score;
    }

    /**
     * Gets score tracking listener.
     *
     * @return the score tracking listener
     */
    public ScoreCounterListener getScoreTrackingListener() {
        return scoreTrackingListener;
    }

    private ArrayList<Ball> balls = new ArrayList<>();
    private BallRemover ballsRemover;

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        gameEnvironment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * initialize
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        gameEnvironment = new GameEnvironment();
        sprites = new SpriteCollection();
        blocksCounter = new entities.Counter();
        ballsCounter = new entities.Counter();
        ballsCounter.increase(levelInformation.numberOfBalls());
        ballsRemover = new BallRemover(this, ballsCounter);
        generateDeathRegion();
        scoreTrackingListener = new ScoreCounterListener(scoreCounter);
        score = new ScoreIndecator(new Rectangle(new Point(0, 0), WINDOW_WIDTH, 30), scoreCounter);
        blockRemover = new BlockRemover(this, blocksCounter);
        generateBorders(0);
        setLevel(levelInformation);
        generateBalls(levelInformation.initialBallVelocities());

        run();
    }

    /**
     * Set score counter.
     *
     * @param scoreCounter the score counter
     */
    public void setScoreCounter(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    private void setLives(Lives lives) {
        this.lives = lives;
    }

    /**
     * generate the paddle.
     *
     * @param level
     */
    private void generatePaddle(LevelInformation level) {

        this.paddle = new Paddle(new Rectangle(new Point((WINDOW_WIDTH - level.paddleWidth()) / 2, WINDOW_HEIGHT - 22),
                level.paddleWidth(), 20, Color.red));
        paddle.setCurrentVelocity(new Velocity(level.paddleSpeed(), 0));
        paddle.addToGame(this);
        paddle.setGameEnvironment(gameEnvironment);
    }

    /**
     * generate rows of blocks.
     *
     * @param space space between blocks.
     */
    private void generateRows(int space) {
        RowBuilder.build(GameLevel.THICKNESS + space, 200, 40, 20, 12,
                Color.darkGray, this);
        RowBuilder.build(GameLevel.THICKNESS + space, 222, 40, 20,
                11, Color.red, this);
        RowBuilder.build(GameLevel.THICKNESS + space, 244, 40, 20,
                10, Color.yellow, this);
        RowBuilder.build(GameLevel.THICKNESS + space, 266, 40, 20,
                9, Color.PINK, this);
        RowBuilder.build(GameLevel.THICKNESS + space, 288, 40, 20,
                8, Color.green, this);
    }

    /**
     * generate borders.
     *
     * @param space space between blocks.
     */
    private void generateBorders(int space) {
        int divSpace = space / 2;
        Block upper = new Block(new Rectangle(new Point(0, 0),
                WINDOW_WIDTH, GameLevel.THICKNESS, Color.lightGray));

        Block right = new Block(new Rectangle(new Point(0, GameLevel.THICKNESS + divSpace),
                GameLevel.THICKNESS, WINDOW_HEIGHT, Color.lightGray));
        Block left = new Block(new Rectangle(new Point(WINDOW_WIDTH - GameLevel.THICKNESS,
                GameLevel.THICKNESS + divSpace), GameLevel.THICKNESS, WINDOW_HEIGHT, Color.lightGray));
        upper.setDyMultiplier(-1);
        upper.setDxMultiplier(1);
        left.setDxMultiplier(-1);
        left.setDyMultiplier(1);
        right.setDyMultiplier(1);
        addBlock(upper);

        addBlock(right);
        addBlock(left);
    }

    /**
     * Generate death region.
     */
    public void generateDeathRegion() {

        Block block = new Block(new Rectangle(new Point(0, WINDOW_HEIGHT + BALL_RADIUS * 2),
                WINDOW_WIDTH, 40, Color.red));
        addBlock(block);
        block.addHitListener(this.ballsRemover);
    }

    /**
     * Add block.
     *
     * @param block the block
     */
    public void addBlock(Block block) {
        this.sprites.addSprite(block);
        this.gameEnvironment.addCollidable(block);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        gameEnvironment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * generate balls.
     *
     * @param velocities velocities
     */
    private void generateBalls(List<Velocity> velocities) {
        balls = new ArrayList<>();
        Rectangle paddleRect = paddle.getCollisionRectangle();
        double radius = 80;
        for (Velocity velocity : velocities) {
            Ball ball = new Ball(new Point(paddleRect.getUpperLeft().getX() +
                    paddleRect.getWidth() / 2,
                    paddleRect.getUpperLeft().getY() - radius),
                    BALL_RADIUS, Color.red, gameEnvironment);
            ball.setVelocity(velocity);
            this.balls.add(ball);
            this.sprites.addSprite(ball);
        }

    }

    /**
     * Run.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        this.runner.run(this);
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(LevelInformation level) {
        this.title = level.levelName();
        setBlocks(level.blocks());
        blocksCounter.set(levelInformation.numberOfBlocksToRemove());
        generatePaddle(level);
    }

    /**
     * Blocks.
     *
     * @param blocks the blocks
     */
    public void setBlocks(List<Block> blocks) {
        System.out.println(blocks.size());
        for (Block block : blocks) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            addBlock(block);
        }

    }


    @Override
    public void doOneFrame(DrawSurface d) {
        System.out.println(ballsCounter.getValue());
        keyboard = runner.getKeyBoardSensor();
        paddle.setSensor(keyboard);
        if (keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
            return;
        }
        background.drawOn(d);
        if (running && blocksCounter.getValue() == 0) {
            scoreCounter.increase(100);
            running = false;
        }
        if (running && ballsCounter.getValue() == 0) {
            if (liveListener.getLives() == 0) {
                running = false;
                return;
            }
            liveListener.decreaseLives();
            clear();
            return;

        }
        moveBalls();

        this.sprites.drawAllOn(d);
        score.drawOn(d);
        lives.drawOn(d);
        this.sprites.notifyAllTimePassed();
        d.setColor(Color.black);
        d.drawText(540, 20, levelInformation.levelName(), 18);

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * Clear.
     */
    public void clear() {
        balls = new ArrayList<>();
        ballsCounter.increase(levelInformation.numberOfBalls());
        generateBalls(levelInformation.initialBallVelocities());
    }

    private void moveBalls() {
        ArrayList<Ball> balls = new ArrayList<>(this.balls);
        for (Ball ball : balls) {
            ball.moveOneStep();
        }
    }


    /**
     * Remove ball.
     *
     * @param hitter the hitter
     */
    public void removeBall(Ball hitter) {
        this.balls.remove(hitter);
    }

    /**
     * Sets live listener.
     *
     * @param liveListener the live listener
     */
    public void setLiveListener(LiveListener liveListener) {
        this.liveListener = liveListener;
        this.lives = new Lives(liveListener);
    }
}