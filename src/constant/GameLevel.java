package constant;

public enum GameLevel {
  ZERO,
  PASSED_ONE,
  PASSED_TWO,
  PASSED_THREE;

  /**
   * Returns the opposite direction of the given one.
   *
   * @param level a level
   * @return the next level
   */
  public static GameLevel getNext(GameLevel level) {
    GameLevel next = null;
    switch (level) {
      case ZERO:
        next = ZERO;
        break;
      case PASSED_ONE:
        next = PASSED_ONE;
        break;
      case PASSED_TWO:
        next = PASSED_TWO;
        break;
      default:
        next = PASSED_THREE;
    }
    return next;
  }
}
