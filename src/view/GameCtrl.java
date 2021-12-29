package view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Map;

public class GameCtrl {

  /**
   * Player's nickName.
   */
  @FXML private Text nickName;
  /*
   * current playing map.
   */

  /**
   * The title shown on the screen.
   */
  @FXML private Text title;

  /**
   * The count of life shown on the screen.
   */
  @FXML private Text lifeCount;

  /**
   * The count of score shown on the screen.
   */
  @FXML private Text scoreCount;

  /**
   * The count of the bombs that the pacman is currently holding.
   */

  /**
   * Changes the title shown on the screen.
   * @param title the desired title
   */
  @FXML
  public void setTitle(String title) {
    this.title.setText(title);
  }


  /**
   * Changes the title shown on the screen.
   * @param nickName the desired title
   */
  @FXML
  public void setNickName(String nickName) {
    this.nickName.setText(" - " + nickName);
  }

  public Text getNickName() {
    return nickName;
  }

  /**
   * Changes the count of life shown on the screen.
   * @param remaining remaining life
   * @param total total life
   */
  @FXML
  public void setLifeCount(int remaining, int total) {
    this.lifeCount.setText(remaining + "/" + total);
  }

  /**
   * Changes the count of score shown on the screen.
   * @param scoreCount the count of score
   */
  @FXML
  public void setScoreCount(int scoreCount) {
    this.scoreCount.setText(Integer.toString(scoreCount));
  }

  public Text getScoreCount() {
    return scoreCount;
  }

}
