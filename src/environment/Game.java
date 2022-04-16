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

import java.awt.*;
import java.util.Random;


/**
 * The type Game.
 */
public class Game {
    /**
     * The constant WINDOW_WIDTH.
     */
    public static int WINDOW_WIDTH = 800;
    /**
     * The constant WINDOW_HEIGHT.
     */
    public static int WINDOW_HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment gameEnvironment;
    private Ball ball1,ball2;
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
        Random rnd = new Random();
        int firstAngle = rnd.nextInt(360);
        int secondAngle = rnd.nextInt(360);
        gameEnvironment = new GameEnvironment();
        sprites = new SpriteCollection();
        this.ball1 = new Ball(new Point(400, 400), 5, Color.red, gameEnvironment);
        this.ball1.setVelocity(Velocity.fromAngleAndSpeed(Math.toRadians(firstAngle),10));
        this.ball2 = new Ball(new Point(400, 150), 5, Color.red, gameEnvironment);
        this.ball2.setVelocity(Velocity.fromAngleAndSpeed(Math.toRadians(secondAngle),10));
        sprites.addSprite(ball2);
        this.paddle = new Paddle(new Rectangle(new Point(400,WINDOW_HEIGHT-62),100,20,Color.red));
        paddle.addToGame(this);
        int thickness = 40;
        int space =4;

        Block upper =  new Block(new helpers.Rectangle(new helpers.Point(0, 0), WINDOW_WIDTH, thickness, Color.lightGray));
        upper.setDyMulty(-1);
        upper.setDxMulty(1);
        Block buttom = new Block(new helpers.Rectangle(new Point(thickness+space/2, WINDOW_HEIGHT - thickness), WINDOW_WIDTH-thickness*2 - space, thickness, Color.lightGray));
        Block right = new Block(new helpers.Rectangle(new helpers.Point(0, thickness + space/2 ), thickness, WINDOW_HEIGHT, Color.lightGray));
        Block left = new Block(new Rectangle(new helpers.Point(WINDOW_WIDTH - thickness, thickness + space/2), thickness, WINDOW_HEIGHT, Color.lightGray));
        left.setDxMulty(-1);
        left.setDyMulty(1);
        buttom.setDxMulty(1);
        right.setDyMulty(1);

        gameEnvironment.addCollidable(upper);
        gameEnvironment.addCollidable(buttom);
        gameEnvironment.addCollidable(right);
        gameEnvironment.addCollidable(left);
        sprites.addSprite(upper);
        sprites.addSprite(buttom);
        sprites.addSprite(right);
        sprites.addSprite(left);
        sprites.addSprite(ball1);
        RowBuilder.Build(thickness+space,200,40,20,12,Color.darkGray,this);
        RowBuilder.Build(thickness+space,222,40,20,11,Color.red,this);
        RowBuilder.Build(thickness+space,244,40,20,10,Color.yellow,this);
        RowBuilder.Build(thickness+space,266,40,20,9,Color.PINK,this);
        RowBuilder.Build(thickness+space,288,40,20,8,Color.green,this);



    }

    /**
     * Run.
     */
// Run the game -- start the animation loop.
    public void run() {
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("", WINDOW_WIDTH, WINDOW_HEIGHT);
        KeyboardSensor sensor = gui.getKeyboardSensor();
        paddle.setSensor(sensor);

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.blue);
            d.fillRectangle(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
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