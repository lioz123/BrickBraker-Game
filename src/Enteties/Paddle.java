package Enteties;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import environment.Game;
import environment.GameEnvironment;
import helpers.CollisionInfo;
import helpers.Velocity;
import helpers.Point;
import helpers.Rectangle;
import helpers.Line;
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
        if (gameEnvironment != null) {
            Point start = currentVelocity.applyToPoint(new Point(rectangle.getUpperLeft().getX() ,
                    rectangle.getUpperLeft().getY()));
            Line line = new Line(start, rectangle.getUpperLeft());
            CollisionInfo info = gameEnvironment.getClosestCollision(line);
            boolean collided = false;
            int counter = 0;
            if(info!=null){
                System.out.println("first colission" +info.collisionPoint());

            }

            while (info != null && !(info.collisionObject() instanceof Ball)) {
                counter++;
                collided=true;
                currentVelocity = new Velocity(0, 0);
                rectangle.setUpperLeft(new Point(info.collisionPoint().getX() + 1, rectangle.getUpperLeft().getY()));
                System.out.println("collision left");
                 start = currentVelocity.applyToPoint(new Point(rectangle.getUpperLeft().getX() ,
                        rectangle.getUpperLeft().getY()));
                line = new Line(start,start);
                info = gameEnvironment.getClosestCollision(line);
                if(info!=null){
                    System.out.println("second colission" +info.collisionPoint());

                }
            }
            System.out.println("collision counter: " + counter);
            if(collided){
                return;

            }
        }
        rectangle.setUpperLeft(currentVelocity.applyToPoint(rectangle.getUpperLeft()));

    }

    /**
     * Move right.
     */
    public void moveRight() {
        currentVelocity = new Velocity(speed, 0);
        if (gameEnvironment != null) {
            Point start = new Point(rectangle.getUpperLeft().getX() + rectangle.getWidth(),
                    rectangle.getUpperLeft().getY());
            Point end = currentVelocity.applyToPoint(start);
            Line line = new Line(start, end);
            System.out.println("upperleft: " + rectangle.getUpperLeft().toString() + "  start" + start.toString() + " end: " + end.toString());

            CollisionInfo info = gameEnvironment.getClosestCollision(line);
            if (info != null && !(info.collisionObject() instanceof Ball) && !(info.collisionObject() instanceof Paddle)) {
                currentVelocity = new Velocity(0, 0);
                System.out.println("collision right");
                rectangle.setUpperLeft(new Point(info.collisionPoint().getX() - 1 - rectangle.getWidth(),
                        rectangle.getUpperLeft().getY()));
                System.out.println("collisionPoint: " + info.collisionPoint().toString());

                return;
            }
        }

        rectangle.setUpperLeft(currentVelocity.applyToPoint(rectangle.getUpperLeft()));
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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


}