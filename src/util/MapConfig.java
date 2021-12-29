package util;

import model.Map;

/**
 * object consisting of configuration of maps.
 */
public class MapConfig {

  /** The title of the map */
  private String title;

  /** The grid length in this {@link Map} */
  private double gridLength;

  /** The pacman step in this {@link Map} */
  private double pacmanStep;

  /** The ghost step in this {@link Map} */
  private double ghostStep;

  /** The cookie padding in this {@link Map} */
  private double cookiePadding;

  /** The bomb padding in this {@link Map} */
  private double bombPadding;

  /** The ghost padding in this {@link Map} */
  private double ghostPadding;

  /** The pacman step rate in this {@link Map} */
  private double pacmanStepRate = 0.05;

  /** The ghost step rate in this {@link Map} */
  private double ghostStepRate = 0.06;

  /** The cookie padding rate in this {@link Map} */
  private double cookiePaddingRate = 0.3;

  /** The bomb padding rate in this {@link Map} */
  private double bombPaddingRate = 0.3;

  /** The ghost padding rate in this {@link Map} */
  private double ghostPaddingRate = 0.7;

  /** The Question Grid  padding rate in this {@link Map} */
  private double questionGridPadding=0.3;

  /**
   * Allocates a new {@link MapConfig} object.
   *
   * @param gridLength the grid length in this {@link Map}
   */
  public MapConfig(double gridLength) {
    this.gridLength = gridLength ;
    this.calculate();
  }


  /**
   * Gets pacman step rate.
   *
   * @return the pacman step rate
   */
  public double getPacmanStepRate() {
    return pacmanStepRate;
  }

  /** Updates all configuration based on the given ones. */
  public void calculate() {
    updateGhostStep();
    updatePacmanStep();
    updateCookiePadding();
    updateGhostPadding();
  }

  /** Updates the pacman step. */
  private void updatePacmanStep() {
     pacmanStep = gridLength * pacmanStepRate;
  }

  /** Updates the ghost step. */
  private void updateGhostStep() {
    ghostStep = gridLength * ghostStepRate;
  }

  /** Updates the cookie padding. */
  private void updateCookiePadding() {
    cookiePadding = gridLength * cookiePaddingRate;
  }

  /** Updates the bomb padding. */
  private void updateBombPadding() {
    bombPadding = gridLength * bombPaddingRate;
  }

  /** Updates the ghost padding. */
  private void updateGhostPadding() {
    ghostPadding = gridLength * ghostPaddingRate;
  }

  /**
   * Returns the cookie padding in this {@link Map}.
   *
   * @return the cookie padding in this {@link Map}
   */
  public double getCookiePadding() {
    return cookiePadding;
  }

  /**
   * Returns the bomb padding in this {@link Map}.
   *
   * @return the bomb padding in this {@link Map}
   */
  public double getBombPadding() {
    return bombPadding;
  }

  /**
   * Get question grid padding double.
   *
   * @return the double
   */
  public double getQuestionGridPadding(){
    return questionGridPadding;
  }

  /**
   * Returns the ghost padding in this {@link Map}.
   *
   * @return the ghost padding in this {@link Map}
   */
  public double getGhostPadding() {
    return ghostPadding;
  }

  /**
   * Returns the ghost step in this {@link Map}.
   *
   * @return the ghost step in this {@link Map}
   */
  public double getGhostStep() {
    return ghostStep;
  }

  /**
   * Returns the pacman step in this {@link Map}.
   *
   * @return the pacman step in this {@link Map}
   */
  public double getPacmanStep() {
    return pacmanStep;
  }

  /**
   * Returns the grid length in this {@link Map}.
   *
   * @return the grid length in this {@link Map}
   */
  public double getGridLength() {
    return gridLength;
  }

  /**
   * Changes the grid length in this {@link Map}.
   *
   * @param gridLength the grid length in this {@link Map}
   */
  public void setGridLength(double gridLength) {
    this.gridLength = gridLength;
  }

  /**
   * Returns the title of this {@link Map}.
   *
   * @return the title of this {@link Map}
   */
  public String getTitle() {
    return title;
  }

  /**
   * Changes the title of this {@link Map}.
   *
   * @param title the title of this {@link Map}
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Changes the pacman step rate in this {@link Map}, and updates the pacman step.
   *
   * @param pacmanStepRate the pacman step rate in this {@link Map}
   */
  public void setPacmanStepRate(double pacmanStepRate) {
    this.pacmanStepRate = pacmanStepRate;
    updatePacmanStep();
  }

  /**
   * Changes the ghost step rate in this {@link Map}, and updates the ghost step.
   *
   * @param ghostStepRate the ghost step rate in this {@link Map}
   */
  public void setGhostStepRate(double ghostStepRate) {
    this.ghostStepRate = ghostStepRate;
    updateGhostStep();
  }


  /**
   * Gets ghost step rate.
   *
   * @return the ghost step rate
   */
  public double getGhostStepRate() {
    return ghostStepRate;
  }

  /**
   * Changes the ghost padding in this {@link Map}, and updates the ghost padding.
   *
   * @param ghostPadding the ghost padding in this {@link Map}
   */
  public void setGhostPaddingRate(double ghostPadding) {
    this.ghostPaddingRate = ghostPadding;
    updateGhostPadding();
  }

  /**
   * Changes the cookie padding in this {@link Map}, and updates the cookie padding.
   * @param cookiePadding the cookie padding in this {@link Map}
   */
  public void setCookiePaddingRate(double cookiePadding) {
    this.cookiePaddingRate = cookiePadding;
    updateCookiePadding();
  }
}
