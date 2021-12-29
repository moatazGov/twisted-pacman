package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.SysData;
import util.SceneSwitch;

/** The {@link Main} class for the pacman application. */
public class Main extends Application {
    SysData sysData =  SysData.getInstance();
    /** The primary stage of the application. */
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

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    /**
     * Starts the application.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;

        primaryStage.setResizable(false);
        primaryStage.setTitle("Pacman");

        // set up start scene

        SceneSwitch.INSTANCE.switchToHome();
//        SceneSwitch.INSTANCE.switchToGame();
    }
}
