package environment;

import Enteties.Ball;
import Enteties.Block;
import Enteties.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import helpers.Point;
import helpers.Rectangle;
import helpers.RowBuilder;
import helpers.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;
import java.util.Random;


/**
 * The type Game.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class Game {

    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private SpriteCollection sprites;
    private GameEnvironment gameEnvironment;
    private Ball ball1, ball2;
    private Paddle paddle;

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
        generateBalls();
        generatePaddle();
        int thickness = 40;
        int space = 4;
        generateBorders(thickness, space);
        generateRows(thickness, space);


    }
    /**
     * generate the paddle.
     */
    private void generatePaddle() {
        this.paddle = new Paddle(new Rectangle(new Point(400, windowHeight - 62),
                100, 20, Color.red));
        paddle.setCurrentVelocity(new Velocity(40, 0));
        paddle.addToGame(this);
        paddle.setGameEnvironment(gameEnvironment);
    }

    /**
     * generate rows of blocks.
     *
     * @param thickness thickness of the blocks.
     * @param space     space between blocks.
     */
    private void generateRows(int thickness, int space) {
        RowBuilder.build(thickness + space, 200, 40, 20, 12, Color.darkGray, this);
        RowBuilder.build(thickness + space, 222, 40, 20, 11, Color.red, this);
        RowBuilder.build(thickness + space, 244, 40, 20, 10, Color.yellow, this);
        RowBuilder.build(thickness + space, 266, 40, 20, 9, Color.PINK, this);
        RowBuilder.build(thickness + space, 288, 40, 20, 8, Color.green, this);
    }

    /**
     * generate borders.
     *
     * @param thickness thickness of the blocks.
     * @param space     space between blocks.
     */
    private void generateBorders(int thickness, int space) {
        int divSpace = space / 2;
        Block upper = new Block(new Rectangle(new Point(0, 0),
                windowWidth, thickness, Color.lightGray));
        Block buttom = new Block(new Rectangle(new Point(thickness + divSpace,
                windowHeight - thickness), windowWidth - thickness * 2 - space, thickness, Color.lightGray));
        Block right = new Block(new Rectangle(new Point(0, thickness + divSpace),
                thickness, windowHeight, Color.lightGray));
        Block left = new Block(new Rectangle(new Point(windowWidth - thickness,
                thickness + divSpace), thickness, windowHeight, Color.lightGray));
        upper.setDyMultiplier(-1);
        upper.setDxMultiplier(1);
        left.setDxMultiplier(-1);
        left.setDyMultiplier(1);
        buttom.setDxMultiplier(1);
        right.setDyMultiplier(1);

        gameEnvironment.addCollidable(upper);
        gameEnvironment.addCollidable(buttom);
        gameEnvironment.addCollidable(right);
        gameEnvironment.addCollidable(left);
        sprites.addSprite(upper);
        sprites.addSprite(buttom);
        sprites.addSprite(right);
        sprites.addSprite(left);
    }

    /**
     * generate balls.
     */
    private void generateBalls() {
        Random rnd = new Random();
        int firstAngle = rnd.nextInt(360);
        int secondAngle = rnd.nextInt(360);
        this.ball1 = new Ball(new Point(400, 400), 5, Color.white, gameEnvironment);
        this.ball1.setVelocity(Velocity.fromAngleAndSpeed(Math.toRadians(firstAngle), 10));
        this.ball2 = new Ball(new Point(400, 150), 5, Color.white, gameEnvironment);
        this.ball2.setVelocity(Velocity.fromAngleAndSpeed(Math.toRadians(secondAngle), 10));
        sprites.addSprite(ball1);
        sprites.addSprite(ball2);
    }

    /**
     * Run.
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("", windowWidth, windowHeight);
        KeyboardSensor sensor = gui.getKeyboardSensor();
        paddle.setSensor(sensor);

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, windowWidth, windowHeight);
            ball1.moveOneStep();
            ball2.moveOneStep();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }


}