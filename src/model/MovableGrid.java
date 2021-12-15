package model;

import com.sun.scenario.animation.AbstractMasterTimer;
import constant.Direction;
import constant.MovableGridType;
import javafx.animation.AnimationTimer;

import java.util.Set;
import java.util.Timer;

/**
 *
 *
 * <h1>MovableGrid</h1>
 *
 * <p>A {@link MovableGrid} is a {@link Grid} located in a {@link Map} with the ability to move.
 *
 * <p>This class extends {@link Grid}.
 *
 */
public abstract class MovableGrid extends Grid {

  /** The {@link AnimationTimer} instance for moving left */
  public AnimationTimer moveLeft;
  /** The {@link AnimationTimer} instance for moving right */
  public AnimationTimer moveRight;
  /** The {@link AnimationTimer} instance for moving up */
  public AnimationTimer moveUp;
  /** The {@link AnimationTimer} instance for moving down */
  public AnimationTimer moveDown;
  /** The step to go every time moving happens, in px */
  private double step;

  /**
   */
  public MovableGrid(Map map, double row, double column, MovableGridType movableGridType) {
    super(map, row, column);

    // set step rate according to type
    initConfig(movableGridType);

    // set animation
    this.moveLeft = this.move(Direction.LEFT);
    this.moveRight = this.move(Direction.RIGHT);
    this.moveUp = this.move(Direction.UP);
    this.moveDown = this.move(Direction.DOWN);
  }

  /**
   * Initialize the step configuration based on the given {@link MovableGridType}.
   *
   * @param movableGridType a {@link MovableGridType} indicates the role; possible values: {@link
   *     MovableGridType#GHOST}, {@link MovableGridType#PACMAN}
   */
  private void initConfig(MovableGridType movableGridType) {
    switch (movableGridType) {
      case GHOST:
        step = getParentMap().getMapConfig().getGhostStep();
        break;
      case PACMAN:
        step = getParentMap().getMapConfig().getPacmanStep();
        break;
      default:
    }
  }

  /**
   * Returns an {@link AnimationTimer} with {@link AnimationTimer#handle(long)} overridden to handle
   * the moving.
   *
   * @param direction the {@link Direction} indicating where to go
   * @return an {@link AnimationTimer} instance handling the moving of the given direction
   */
  private AnimationTimer move(Direction direction) {
    return new AnimationTimer() {
      public void handle(long currentNanoTime) {
        Set<Grid> obstacles = (Set<Grid>) (Set<?>) MovableGrid.this.getParentMap().getWalls();
        double length = MovableGrid.this.getParentMap().getMapConfig().getGridLength();
        switch (direction) {
          case RIGHT:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.RIGHT, obstacles, length)) {
              MovableGrid.this.setX(MovableGrid.this.getX() + step);
              MovableGrid.this.handleMove(Direction.RIGHT);
            } else {
              MovableGrid.this.handleCantMove(Direction.RIGHT);
            }
            break;
          case LEFT:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.LEFT, obstacles,length)) {
              MovableGrid.this.setX(MovableGrid.this.getX() - step);
              MovableGrid.this.handleMove(Direction.LEFT);
            } else {
              MovableGrid.this.handleCantMove(Direction.LEFT);
            }
            break;
          case UP:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.UP, obstacles,length)) {
              MovableGrid.this.setY(MovableGrid.this.getY() - step);
              MovableGrid.this.handleMove(Direction.UP);
            } else {
              MovableGrid.this.handleCantMove(Direction.UP);
            }
            break;
          case DOWN:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.DOWN, obstacles,length)) {
              MovableGrid.this.setY(MovableGrid.this.getY() + step);
              MovableGrid.this.handleMove(Direction.DOWN);
            } else {
              MovableGrid.this.handleCantMove(Direction.DOWN);
            }
            break;
        }
  checkIfTouchingPortal();
      }
    };
  }

  /**
   * Tests if this {@link MovableGrid} is touching a {@link Portal}. If true, sends this {@link
   * MovableGrid} to the position where the twin portal (using {@link Portal#getTwinPortal()} to
   * retrieve) stays.
   *
   * <p>This method gets all {@link Portal}s from the parent {@link Map} and calls {@link
   * Grid#isTouching(Grid, double)} to check it one by one.
   */
  private void checkIfTouchingPortal() {
    for (Portal portal : getParentMap().getPortals()) {
      if (isTouching(portal, getParentMap().getMapConfig().getGridLength() * 0.8)) {
        if (portal.isOpen()) {
          // send to another portal
          setX(portal.getTwinPortal().getX());
          setY(portal.getTwinPortal().getY());
          // close portal
          portal.getTwinPortal().close();
        }else{
          System.out.println("here");
        }
        return;
      }else{
        System.out.println("there");

      }
    }

    // open portals if leaving portals
    for (Portal portal : getParentMap().getPortals()) {
      if (isTouching(portal, 0)) {
        portal.open();
      }
    }
  }

  /**
   * Tests if this {@link MovableGrid} is going to touch a set of another given {@link Grid}.
   *
   * <p>The algorithm: this method generates a mock grid in a position where this {@link
   * MovableGrid} will be after the next move calculated based on the given direction, and checks if
   * the mock one overlaps any grids in the given set.
   *
   * @param direction the current {@link Direction} of moving
   * @param grids a set of {@link Grid} to test if touching
   * @param padding a permissible error range in px indicating a range to allow overlapping to some
   *     extend, in px
   * @return {@code true} if this {@link MovableGrid} is going to touch a set of another given
   *     {@link Grid}; {@code false} otherwise
   */
  public boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids, double padding, double gridLength) {
    // calculate next step based on direction
    double nextX = getX();
    double nextY = getY();
    switch (direction) {
      case RIGHT:
        nextX += step;
        break;
      case LEFT:
        nextX -= step;
        break;
      case UP:
        nextY -= step;
        break;
      case DOWN:
        nextY += step;
        break;
      default:
    }
    // generate a mock grid at the next step
    Grid nextPositionGrid = new Grid(getParentMap(), nextX / gridLength, nextY / gridLength);
    nextPositionGrid.setX(nextX);
    nextPositionGrid.setY(nextY);

    // check if the mock grid overlaps any obstacle
    for (Grid grid : grids) {
      if (grid.isTouching(nextPositionGrid, padding) && grid instanceof Wall) {
        return true;
      }
    }

    return false;
  }

  /**
   * Tests if this {@link MovableGrid} is going to touch a set of another given {@link Grid}.
   *
   * does. But with the third parameter {@code padding} being {@code 1} (which is a recommended
   * minimal value).
   *
   * @param direction the current {@link Direction} of moving
   * @param grids a set of {@link Grid} to test if touching
   * @return {@code true} if this {@link MovableGrid} is going to touch a set of another given *
   *     {@link Grid}; {@code false} otherwise
   */
  public boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids, double gridLength) {
    return isGoingToTouchGrids(direction, grids, 1, gridLength); // padding = 1 for the error
  }

  /**
   * Handles what should be done whenever moving.
   *
   * <p>This method is called inside {@link #move(Direction)}. This doesn't do anything as default,
   * and is designed to be overridden.
   *
   * @param direction the current direction of moving
   * @see Ghost#handleMove(Direction)
   * @see Pacman#handleMove(Direction)
   */
  public void handleMove(Direction direction) {}

  /**
   * Handles what should be done whenever cannot move.
   *
   * <p>This method is called inside {@link #move(Direction)}. This doesn't do anything as default,
   * and is designed to be overridden.
   *
   * @param direction the current direction of moving
   * @see Ghost#handleCantMove(Direction)
   */
  public void handleCantMove(Direction direction) {

  }
}
