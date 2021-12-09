package util;

import controller.ScoreBoardController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Main;
import constant.FileName;
import view.GameController;
import model.Map;

import java.net.URL;

/**
 *
 *
 * <h1>SceneSwitch</h1>
 *
 * <p>A {@link SceneSwitch} is an object of utility to provide some methods to switch between
 * different scenes in the primary stage ({@link Main#getPrimaryStage()}).
 *
 * <p><b>Note:</b> this class is implemented an {@link Enum}, thus to be a singleton class.
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    ()
 */
public enum SceneSwitch {
  /** The shared instance for global use. */
  INSTANCE;

  /** Hides the primary stage. */
  private void hideStage() {
    Main.getPrimaryStage().hide();
  }

  /** Shows the primary stage. */
  private void showStage() {
    Main.getPrimaryStage().show();
  }

  /**
   * Changes the scene in the primary stage to the given one.
   *
   * @param scene a {@link Scene} object
   */
  private void setScene(Scene scene) {
    Main.getPrimaryStage().setScene(scene);
  }

  /** Switched the current scene to Home. */
  public void switchToHome() {
    try {
//      MusicPlayer.INSTANCE.playBeginning();
      hideStage();
      URL location = Main.class.getResource("/resources/fxml/game.fxml");
      Pane root = FXMLLoader.load(location);
      Scene scene = new Scene(root);
      setScene(scene);
      showStage();
    } catch (Exception e) {
      e.getMessage();
      e.printStackTrace();
    }
  }

  /** Switched the current scene to Select. */
  public void switchToSelect() {
    try {
      MusicPlayer.INSTANCE.playSetup();
      hideStage();
      Pane root = FXMLLoader.load(getClass().getResource(FileName.VIEW_SELECT));
      Scene scene = new Scene(root);
      setScene(scene);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Switched the current scene to Game.
   *
   */
  public void switchToGame() {
    try {
      Map map = new Map();
      map.setNickname("Unknown Player");
      map.setFileName("/resources/pacman/map/#002 Easy Again?.txt");
      map.setBackgroundFileName("/resources/image/floor/bedrock.png");
      map.setWallFileName("/resources/image/obstacle/bricks.png");
      hideStage();
      URL location = Main.class.getResource("/resources/fxml/game.fxml");
      FXMLLoader loader = new FXMLLoader(location);
      Pane root = loader.load();
      Scene gameScene = new Scene(root);
      setScene(gameScene);

      Pane mapPane = (Pane) gameScene.lookup("#map");
      Canvas canvas = new Canvas();
      mapPane.getChildren().add(canvas);
      map.draw(mapPane);
      mapPane.setStyle(
          "-fx-background-image: url('"
              + map.getBackgroundFileName()
              + "');"
              + "-fx-background-size: "
              + map.getMapConfig().getGridLength()
              + "px "
              + map.getMapConfig().getGridLength()
              + "px;");

      GameController gameController = loader.getController();
      GameManager.INSTANCE.init(map, gameController);

      gameScene.addEventHandler(
          KeyEvent.KEY_PRESSED, event -> GameManager.INSTANCE.handleKeyPressed(event));
      gameScene.addEventHandler(
          KeyEvent.KEY_RELEASED, event -> GameManager.INSTANCE.handleKeyReleased(event));
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Switched the current scene to ScoreBoard.
   *
   * @param title the current title of this level
   */
  public void popupScoreBoard(String title) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(FileName.VIEW_SCOREBOARD));
      Pane root = loader.load();
      Scene scoreBoardScene = new Scene(root);
      Stage popup = new Stage();
      popup.setScene(scoreBoardScene);
      popup.initModality(Modality.WINDOW_MODAL);
      popup.initOwner(Main.getPrimaryStage().getScene().getWindow());
      popup.setResizable(false);
      popup.setTitle("Score Board");

      ScoreBoardController scoreBoardController = loader.getController();
      scoreBoardController.setTitle(title);
      scoreBoardController.initUi();
      popup.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void exitApplication() {
    Platform.exit();
  }
}
