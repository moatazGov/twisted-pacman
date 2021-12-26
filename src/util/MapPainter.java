package util;

import javafx.scene.layout.Pane;
import model.*;

import java.util.Set;

/**
 * The type Map painter.
 */
public class MapPainter {

  private Pane root;

  /**
   * Instantiates a new Map painter.
   *
   * @param root the root
   */
  public MapPainter(Pane root) {
    this.root = root; }

  /**
   * Draw walls.
   *
   * @param walls the walls
   */
  public void drawWalls(Set<Wall> walls) { root.getChildren().addAll(walls); }

  /**
   * Draw pacman.
   *
   * @param pacman the pacman
   */
  public void drawPacman(Pacman pacman) {
    root.getChildren().add(pacman);
  }

  /**
   * Draw angry pacman.
   *
   * @param pacman the pacman
   */
  public void drawAngryPacman(Pacman pacman) {
    root.getChildren().add(pacman);
  }

  /**
   * Draw cookies.
   *
   * @param cookies the cookies
   */
  public void drawCookies(Set<PacItem> cookies) {
    root.getChildren().addAll(cookies);
  }

  /**
   * Draw bombs.
   *
   * @param bombs the bombs
   */
  public void drawBombs(Set<BombItem> bombs) {
    root.getChildren().addAll(bombs);
  }

  /**
   * Draw ghost.
   *
   * @param ghosts the ghosts
   */
  public void drawGhost(Set<Ghost> ghosts) {
    root.getChildren().addAll(ghosts);
  }

  /**
   * Draw question grid.
   *
   * @param questionGrids the question grids
   */
  public void drawQuestionGrid(Set<QuestionGrid> questionGrids) {
    root.getChildren().removeIf(x -> x instanceof QuestionGrid);
    root.getChildren().addAll(questionGrids);
  }

  /**
   * Draw portals.
   *
   * @param portals the portals
   */
  public void drawPortals(Set<Portal> portals) {
    root.getChildren().removeIf(x->x instanceof Portal);
    root.getChildren().addAll(portals);
  }

}
