package model;

/**
 *
 *
 * <h1>Spawn</h1>
 *
 * A Spawn is a invisible Grid located in a Map. It indicates where the
 * Pacman were born, so that when thePacman is dead, the Pacman will respawn there.
 */
public class Spawn extends Grid {

  /**
   * Allocates a new {@link Spawn} object.
   *
   * <p>This constructor sets the {@link Pacman} in the given position of the given {@link Map}.
   *
   * @param map the {@link Map} where this {@link Spawn} stays
   * @param row the row index in the {@link Map} where this {@link Spawn} stays, starting from 0
   * @param column the column index in the {@link Map} where this {@link Spawn} stays, starting from
   *     0
   */
  public Spawn(Map map, double row, double column) {
    super(map, row, column);
  }
}
