package entities;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import environment.GameLevel;
import environment.GameEnvironment;
import helpers.Velocity;
import helpers.Point;
import helpers.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;


/**
 * The type Paddle.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class Paddle implements Sprite, Collidable {

    private GameEnvironment gameEnvironment = null;
    private int speed = 10;

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets sensor.
     *
     * @return the sensor
     */
    public KeyboardSensor getSensor() {
        return sensor;
    }


    /**
     * Sets sensor.
     *
     * @param sensor the sensor
     */
    public void setSensor(KeyboardSensor sensor) {
        this.sensor = sensor;
    }

    /**
     * Sets collision point.
     *
     * @param collisionPoint the collision point
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * Sets rectangle.
     *
     * @param rectangle the rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Sets current velocity.
     *
     * @param currentVelocity the current velocity
     */
    public void setCurrentVelocity(Velocity currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    private KeyboardSensor sensor = null;
    private Point collisionPoint;
    private Rectangle rectangle;
    private Velocity currentVelocity;

    /**
     * Instantiates a new Paddle.
     *
     * @param rectangle the rectangle
     */
    public Paddle(Rectangle rectangle) {
        this.rectangle = rectangle;
        collisionPoint = null;
        currentVelocity = new Velocity(0, 0);
    }


    /**
     * Move left.
     */
    public void moveLeft() {
        currentVelocity = new Velocity(-speed, 0);
        Point collideWithEdge = collideWithBorder();
        if (collideWithEdge != null) {
            rectangle.setUpperLeft(collideWithEdge);
            return;
        }
        rectangle.setUpperLeft(currentVelocity.applyToPoint(rectangle.getUpperLeft()));

    }

    /**
     * Move right.
     */
    public void moveRight() {
        currentVelocity = new Velocity(speed, 0);
        Point collideWithEdge = collideWithBorder();
        if (collideWithEdge != null) {
            rectangle.setUpperLeft(collideWithEdge);
            return;
        }
        rectangle.setUpperLeft(currentVelocity.applyToPoint(rectangle.getUpperLeft()));
    }

    /**
     * Collide with border point.
     *
     * @return the point
     */
    public Point collideWithBorder() {
        if (currentVelocity.applyToPoint(rectangle.getUpperLeft()).getX() + rectangle.getWidth() >= GameLevel.WINDOW_WIDTH - GameLevel.THICKNESS - 1) {
            return new Point(GameLevel.WINDOW_WIDTH - 1 - GameLevel.THICKNESS - rectangle.getWidth(),
                    rectangle.getUpperLeft().getY());
        } else if (currentVelocity.applyToPoint(rectangle.getUpperLeft()).getX() <= GameLevel.THICKNESS + 1) {
            return new Point(GameLevel.THICKNESS + 1, rectangle.getUpperLeft().getY());
        }
        return null;
    }


    @Override
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {
        if (sensor == null) {
            return;
        }
        if (sensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        } else if (sensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Velocity currentVelocity, Point collisionPoint) {
        double space = rectangle.getWidth() / 5;

        if (collisionPoint.distance(rectangle.getUpperLeft()) < space) {
            return Velocity.fromAngleAndSpeed(Math.toRadians(150), currentVelocity.getSpeed());
        } else if (collisionPoint.distance(rectangle.getUpperLeft()) < space * 2) {
            return Velocity.fromAngleAndSpeed(Math.toRadians(120), currentVelocity.getSpeed());

        } else if (collisionPoint.distance(rectangle.getUpperLeft()) < space * 3) {
            return Velocity.fromAngleAndSpeed(Math.toRadians(90), currentVelocity.getSpeed());

        } else if (collisionPoint.distance(rectangle.getUpperLeft()) < space * 4) {
            return Velocity.fromAngleAndSpeed(Math.toRadians(30), currentVelocity.getSpeed());

        }
        return Velocity.fromAngleAndSpeed(Math.toRadians(60), currentVelocity.getSpeed());


    }


    /**
     * Add to game.
     * Add this paddle to the game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    /**
     * Sets location.
     *
     * @param paddleStartingPoint the paddle starting point
     */
    public void setUpperLeft(Point paddleStartingPoint) {
        rectangle.setUpperLeft(paddleStartingPoint);
    }
}