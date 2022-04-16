package Enteties;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import environment.Game;
import helpers.Point;
import helpers.Rectangle;
import helpers.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

public class Paddle implements Sprite, Collidable {
    public KeyboardSensor getSensor() {
        return sensor;
    }


    public void setSensor(KeyboardSensor sensor) {
        this.sensor = sensor;
    }

    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setCurrentVelocity(Velocity currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    private KeyboardSensor sensor = null;
    private Point collisionPoint;
    private Rectangle rectangle;
    private Velocity currentVelocity;

    public Paddle(Rectangle rectangle) {
        this.rectangle = rectangle;
        collisionPoint = null;
        currentVelocity=new Velocity(5,0);
    }


    public void moveLeft(){
        currentVelocity= new Velocity(-5,0);

        rectangle.setUpperLeft(currentVelocity.applyToPoint(rectangle.getUpperLeft()));

    }

    public void moveRight() {
        currentVelocity= new Velocity(5,0);
        rectangle.setUpperLeft(currentVelocity.applyToPoint(rectangle.getUpperLeft()));

    }

    @Override
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d);
    }

    // Sprite
    public void timePassed() {
            if(sensor==null) {
                System.out.println("null sensora");
                return;
            }
            if( sensor.isPressed(KeyboardSensor.RIGHT_KEY)){
                moveRight();
            }else if( sensor.isPressed(KeyboardSensor.LEFT_KEY )){
                moveLeft();
            }
        }




    // Collidable
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        System.out.println("collision point distance:" + (((collisionPoint.getX()-rectangle.getUpperLeft().getX()))));
        double space = rectangle.getWidth()/5;

        if(collisionPoint.distance(rectangle.getUpperLeft())<space){
            return Velocity.fromAngleAndSpeed(Math.toRadians(150), currentVelocity.getSpeed());
        }else if(collisionPoint.distance(rectangle.getUpperLeft())<space*2) {
            return Velocity.fromAngleAndSpeed(Math.toRadians(120), currentVelocity.getSpeed());

        }else if(collisionPoint.distance(rectangle.getUpperLeft())<space*3) {
            return Velocity.fromAngleAndSpeed(Math.toRadians(90), currentVelocity.getSpeed());

        }else if(collisionPoint.distance(rectangle.getUpperLeft())<space*4) {
            return Velocity.fromAngleAndSpeed(Math.toRadians(30), currentVelocity.getSpeed());

        }
        return Velocity.fromAngleAndSpeed(Math.toRadians(60), currentVelocity.getSpeed());


    }


    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


}