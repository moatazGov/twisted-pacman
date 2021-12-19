package util;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.*;

import java.util.Set;
import java.util.function.Predicate;

public class MapPainter {

  private Pane root;

  public MapPainter(Pane root) {
    this.root = root;
  }

  public void drawWalls(Set<Wall> walls) { root.getChildren().addAll(walls); }

  public void drawPacman(Pacman pacman) {
    root.getChildren().add(pacman);
  }

  public void drawAngryPacman(Pacman pacman) {
    root.getChildren().add(pacman);
  }


  public void drawCookies(Set<PacItem> cookies) {
    root.getChildren().addAll(cookies);
  }

  public void drawBombs(Set<BombItem> bombs) {
    root.getChildren().addAll(bombs);
  }

  public void drawGhost(Set<Ghost> ghosts) {
    root.getChildren().addAll(ghosts);
  }

  public void drawQuestion(Set<Question> questions){ //TODO
    //root.getChildren().addAll(questions);
  }
  public void drawQuestionGrid(Set<QuestionGrid> questionGrids) {
    root.getChildren().addAll(questionGrids);
  }

  public void drawPortals(Set<Portal> portals) {
    root.getChildren().addAll(portals);
  }

}
