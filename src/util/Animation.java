package util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import view.MainCtrl;

/**
 *object of utility to do some animations with the game running.
 */
public class Animation {
  private int stageX = 0;
  private int stageY = 0;

  /** Shake the primary stage. */
  public void shakeStage() {
    Timeline timelineX =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.5),
                t -> {
                  if (stageX == 0) {
                    MainCtrl.getPrimaryStage().setX(MainCtrl.getPrimaryStage().getX() + 10);
                    stageX = 1;
                  } else {
                    MainCtrl.getPrimaryStage().setX(MainCtrl.getPrimaryStage().getX() - 10);
                    stageX = 0;
                  }
                }));

    int cycleCount = 4;
    timelineX.setCycleCount(cycleCount);
    timelineX.setAutoReverse(false);
    timelineX.play();

    Timeline timelineY =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.5),
                t -> {
                  if (stageY == 0) {
                    MainCtrl.getPrimaryStage().setY(MainCtrl.getPrimaryStage().getY() + 10);
                    stageY = 1;
                  } else {
                    MainCtrl.getPrimaryStage().setY(MainCtrl.getPrimaryStage().getY() - 10);
                    stageY = 0;
                  }
                }));

    timelineY.setCycleCount(cycleCount);
    timelineY.setAutoReverse(false);
    timelineY.play();
  }
}
