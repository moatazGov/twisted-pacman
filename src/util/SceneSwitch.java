package util;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import view.EndGameCtrl;
import view.MainCtrl;
import view.GameCtrl;
import view.QuestionCtrl;
import java.net.URL;

import static constant.FileName.VIEW_HOME;

/**

 *  object of utility to provide some methods to switch between
 * different scenes in the primary stage ({@link MainCtrl#getPrimaryStage()}).

 */
public enum SceneSwitch {
  /** The shared instance for global use. */
  INSTANCE;
  Map map;
  Pane mapPane;
  Canvas canvas;
  Pane root;
  Scene gameScene;
  Stage PauseStage;
  Stage questionStage;
  /** Hides the primary stage. */
  private void hideStage() {
    MainCtrl.getPrimaryStage().hide();
  }

  /** Shows the primary stage. */
  private void showStage() {
    MainCtrl.getPrimaryStage().show();
  }

  /**
   * Changes the scene in the primary stage to the given one.
   *
   * @param scene a {@link Scene} objeclpo,t
   */
  private void setScene(Scene scene) {
    MainCtrl.getPrimaryStage().setScene(scene);
  }

  /** Switched the current scene to Home. */
  public void switchToHome() {
    try {
      hideStage();
      URL location = MainCtrl.class.getResource(VIEW_HOME);
      Pane root = FXMLLoader.load(location);
      Scene scene = new Scene(root);
      setScene(scene);
      showStage();
    } catch (Exception e) {
      e.getMessage();
      e.printStackTrace();
    }
  }

  /**
   * removes the pause stage view and returns to the previous state of the game.
   */
  public void returnToGame() {
    PauseStage.hide();
  }


  /**
   * Exit question.
   */
  public void exitQuestion() {
    questionStage.hide();
  }

  /**
   * Pauses the game and opens the paused game dialog.
   */
  public void switchToPause()
  {
   try {
     PauseStage = new Stage();
     URL location = MainCtrl.class.getResource("/resources/fxml/pause.fxml");
     FXMLLoader loader = new FXMLLoader(location);
     Parent root2 = loader.load();
     Scene PauseScene = new Scene(root2);
     PauseStage.setScene(PauseScene);
     PauseStage.show();
   }
     catch (Exception e) {
    e.printStackTrace();
  }
  }

  /**
   * pauses the game and opens a question dialog according to the eaten grid
   * @param questionGrid
   */
  public void switchToQuestion(QuestionGrid questionGrid)
  {
    try {
      questionStage = new Stage();
      URL location = MainCtrl.class.getResource("/resources/fxml/question.fxml");
      FXMLLoader loader = new FXMLLoader(location);
      Parent root2 = loader.load();

      QuestionCtrl controller = loader.<QuestionCtrl>getController();
      controller.setQuestion(questionGrid.getQuestion());
      Scene PauseScene = new Scene(root2);
      questionStage.setScene(PauseScene);
      questionStage.show();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Switched the current scene to Game.
   *
   */
  public void switchToGameLevelZero(String nickName) {
    try {
      map = new Map();
      map.setNickname(nickName);
      map.setFileName("/resources/pacman/map/map1.txt");
      map.setBackgroundFileName("/resources/image/floor/bedrock.png");
      map.setWallFileName("/resources/image/obstacle/bricks.png");
      hideStage();
      URL location = MainCtrl.class.getResource("/resources/fxml/game.fxml");
      FXMLLoader loader = new FXMLLoader(location);

      root = loader.load();
      gameScene = new Scene(root);
      GameCtrl controller = loader.<GameCtrl>getController();
      controller.setNickName(nickName);
      setScene(gameScene);

      mapPane = (Pane) gameScene.lookup("#map");
      canvas = new Canvas();
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

      GameCtrl gameCtrl = loader.getController();
      Controller.INSTANCE.init(map, gameCtrl);
      gameCtrl.setTitle("level - 1 ");

      gameScene.addEventHandler(
          KeyEvent.KEY_PRESSED, event -> Controller.INSTANCE.handleKeyPressed(event));
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Switched the current scene to Game.
   *
   */
  public void switchToEndGame(String nickName, String status, String score) {
    try {
      hideStage();

      SysData.getInstance().load();
      URL location = MainCtrl.class.getResource("/resources/fxml/game-over.fxml");
      FXMLLoader loader = new FXMLLoader(location);
      Pane root = loader.load();
      Scene gameScene = new Scene(root);
      EndGameCtrl controller = loader.<EndGameCtrl>getController();
      controller.setNickName(nickName);
      controller.setStatus(status);
      controller.setScoreText(score);
      setScene(gameScene);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Switches the current scene to Game with the first level.
   */
  public void switchToGameLevelOne() {
    try {
      map.addPortalsToScreen(mapPane);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Switches the current scene to Game with the second level.
   */
  public void switchToGameLevelTwo() {
    try {
      map.removePortalsToScreen(mapPane);
      map.getMapConfig().setPacmanStepRate( map.getMapConfig().getPacmanStepRate() + 0.1);
      map.getPacman().setStep(map.getMapConfig().getPacmanStep());
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Switches the current scene to Game with the third level.
   */
  public void switchToGameLevelThree() {
    try {
      map.removePortalsToScreen(mapPane);
      map.getMapConfig().setGhostStepRate( map.getMapConfig().getGhostStepRate() + 0.1);
      map.getGhosts().forEach(ghost->{
        ghost.setStep(map.getMapConfig().getGhostStep());
      });
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Exits the game.
   */
  public void exitApplication() {
    Platform.exit();
  }
}
