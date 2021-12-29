package view;

import javafx.application.Application;
import javafx.stage.Stage;
import util.SceneSwitch;

/**
 * The {@link MainCtrl} class for the pacman application.
 */
public class MainCtrl extends Application {

    /**
     * The primary stage of the application.
     */
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Returns the primary stage of the application.
     *
     * @return the primary stage of the application
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Starts the application.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        MainCtrl.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        primaryStage.setTitle("Pacman");
        SceneSwitch.INSTANCE.switchToHome();
    }
}
