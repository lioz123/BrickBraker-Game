package listeners;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean keyPressed = false;
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;
    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.key = key;
        this.keyboardSensor = sensor;
        this.animation = animation;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (!keyPressed && keyboardSensor.isPressed(key)) {
            keyPressed = true;
        }
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return keyPressed;
    }

}