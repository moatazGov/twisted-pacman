package util;

import javafx.scene.layout.Pane;
import model.*;

import java.util.Set;

/**
 *
 *
 * <h1>MapPainter</h1>
 *
 * <p>A {@link MapPainter} is an object of utility to painter a {@link Map} to a {@link Pane}.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Pane
 */
public class MapPainter {

  /** The {@link Pane} that is expected to be drawn with the map on it */
  private Pane root;

  /**
   * Allocates a new {@link MapPainter} object.
   *
   * @param root the {@link Pane} that is expected to be drawn with the map on it
   */
  public MapPainter(Pane root) {
    this.root = root;
  }


  public void drawWalls(Set<Wall> Wall) {
    root.getChildren().addAll(Wall);
  }

  /**
   * Draws the given {@link Pacman}.
   *
   * @param pacman the {@link Pacman} to be drawn.
   */
  public void drawPacman(Pacman pacman) {
    root.getChildren().add(pacman);
  }

  /**
   * Draws the given {@link model.PacItem}s.
   *
   * @param cookies the {@link model.PacItem}s to be drawn.
   */
  public void drawCookies(Set<PacItem> cookies) {
    root.getChildren().addAll(cookies);
  }

  /**
   * Draws the given {@link Ghost}s.
   *
   * @param ghosts the {@link Ghost}s to be drawn.
   */
  public void drawGhost(Set<Ghost> ghosts) {
    root.getChildren().addAll(ghosts);
  }

  /**
   * Draws the given {@link Portal}s.
   *
   * @param portals the {@link Portal}s to be drawn.
   */
  public void drawPortals(Set<Portal> portals) {
    root.getChildren().addAll(portals);
  }
}
