package util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import model.Portal;
import model.SysData;
import view.EndGameCtrl;
import view.Main;
import constant.FileName;
import view.GameController;
import model.Map;

import java.net.URL;
import java.util.ArrayList;

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
  Map map;
  Pane mapPane;
  Canvas canvas;
  ArrayList<Portal> portal;
  URL location;
  FXMLLoader loader;
  Pane root;
  Scene gameScene;
  Stage PauseStage;
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
   * @param scene a {@link Scene} objeclpo,t
   */
  private void setScene(Scene scene) {
    Main.getPrimaryStage().setScene(scene);
  }

  /** Switched the current scene to Home. */
  public void switchToHome() {
    try {
//      MusicPlayer.INSTANCE.playBeginning();
      hideStage();
      URL location = Main.class.getResource("/resources/fxml/home-view.fxml");
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
//      MusicPlayer.INSTANCE.playSetup();
      hideStage();
      Pane root = FXMLLoader.load(getClass().getResource(FileName.VIEW_SELECT));
      Scene scene = new Scene(root);
      setScene(scene);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void returnToGame()
  {
    PauseStage.hide();
  }

  public void switchToPause()
  {
   try {
     PauseStage = new Stage();
     URL location = Main.class.getResource("/resources/fxml/pause.fxml");
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
//      map.setQuestionGridFileName("/resources/image/med_ques.png");
      hideStage();
      URL location = Main.class.getResource("/resources/fxml/game.fxml");
      FXMLLoader loader = new FXMLLoader(location);

       root = loader.load();
      gameScene = new Scene(root);
      GameController controller = loader.<GameController>getController();
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

      GameController gameController = loader.getController();
      GameManager.INSTANCE.init(map, gameController);
      gameController.setTitle("level - 1 ");

      gameScene.addEventHandler(
          KeyEvent.KEY_PRESSED, event -> GameManager.INSTANCE.handleKeyPressed(event));
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
      URL location = Main.class.getResource("/resources/fxml/end-game.fxml");
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
   * Switched the current scene to Game.
   */
  public void switchToGameLevelOne() {
    try {
      map.addPortalsToScreen(mapPane);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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


  public void exitApplication() {
    Platform.exit();
  }
}
