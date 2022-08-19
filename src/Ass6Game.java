import environment.AnimationRunner;
import environment.GameFlow;
import interfaces.LevelInformation;
import screens.Level1;
import screens.LevelFinalFour;
import screens.LevelGreen3;
import screens.LevelWideEasy;

import java.util.ArrayList;

/**
 * The type Ass 3 game.
 *
 * @author Lioz Dayan. ID:315155234. The type Enteties.Ball.
 */
public class Ass6Game {

    /**
     * The entry point of application.
     *
     * @param arguments the input arguments
     */
    public static void main(String[] arguments) {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        for (String str : arguments) {
            try {
                int level = Integer.parseInt(str);
                switch (level) {
                    case 1:
                        levels.add(new Level1());
                        break;
                    case 2:
                        levels.add(new LevelWideEasy());
                        break;
                    case 3:
                        levels.add(new LevelGreen3());
                        break;
                    case 4:
                        levels.add(new LevelFinalFour());
                        break;
                    default:
                        break;

                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        AnimationRunner animationRunner = new AnimationRunner();
        GameFlow flow = new GameFlow(animationRunner.getKeyBoardSensor(), animationRunner);
        flow.runLevels(levels);
    }


}
