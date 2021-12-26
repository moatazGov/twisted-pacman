package constant;

/**
 *  enum object to store constant names of direction.
 */
public enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT;

  /**
   * Returns the opposite direction of the given one.
   *
   * @param direction a direction
   * @return the opposite direction of the given one
   */
  public static Direction getOpposite(Direction direction) {
    Direction oppositeDirection;
    switch (direction) {
      case UP:
        oppositeDirection = DOWN;
        break;
      case DOWN:
        oppositeDirection = UP;
        break;
      case LEFT:
        oppositeDirection = RIGHT;
        break;
      case RIGHT:
        oppositeDirection = LEFT;
        break;
      default:
        oppositeDirection = RIGHT;
    }
    return oppositeDirection;
  }
}
