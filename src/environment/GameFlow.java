package environment;

import biuoop.KeyboardSensor;
import entities.Counter;
import interfaces.LevelInformation;
import interfaces.LiveListener;
import listeners.KeyPressStoppableAnimation;
import screens.FinalScreen;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow implements LiveListener {
    private int lives = 7;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    /**
     * Instantiates a new Game flow.
     *
     * @param keyboardSensor  the keyboardSensor
     * @param animationRunner the animation runner
     */
    public GameFlow(KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter scoreCounter = new Counter();

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner);
            level.setLiveListener(this);
            level.setScoreCounter(scoreCounter);

            level.initialize();

            while (level.getBlocksCounter().getValue() != 0 && lives != 0) {
                level.run();
            }
            if (lives == 0) {
                break;
            }
        }
        if (lives == 0) {
            animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new FinalScreen("Game Over. Your score is: " + scoreCounter.getValue())));
                animationRunner.close();
                return;
        }

        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new FinalScreen("You Win! Your score is: " + scoreCounter.getValue())));

        animationRunner.close();
    }

    /**
     * On death.
     */
    public void onDeath() {

    }


    @Override
    public void decreaseLives() {
        lives--;
    }

    @Override
    public int getLives() {
        return this.lives;
    }
}
