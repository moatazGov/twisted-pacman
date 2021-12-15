package model;

import constant.FileName;
import constant.PortalType;

public class Portal extends Grid {

  /**
   * The twin {@link Portal} which is linked to be a position indicator for any grid touches this
   * {@link Portal} to teleport to.
   */
  private Portal twinPortal;

  /**
   * A flag indicates whether or not this {@link Portal} is open. Used to prevent blinking between
   * the twins.
   */
  private boolean isOpen;

  /**
   */
  public Portal(Map map, double row, double column, PortalType portalType) {
    super(map, row, column);
    // set image
    switch (portalType) {
      case A:
        setImage(FileName.IMAGE_PORTAL_A);
        break;
      case B:
        setImage(FileName.IMAGE_PORTAL_B);
        break;
      default:
        setImage(FileName.IMAGE_PORTAL_A);
    }
    // init status
    isOpen = true;
  }

  /**
   * Returns the twin of this {@link Portal}.
   *
   * @return the twin of this {@link Portal}
   */
  public Portal getTwinPortal() {
    return twinPortal;
  }

  /**
   * Sets the twin of this {@link Portal}.
   *
   * @param portal the twin of this {@link Portal}
   */
  public void setTwinPortal(Portal portal) {this.twinPortal = portal;}

  /** Closes this {@link Portal}. */
  public void close() {
    isOpen = false;
  }

  /** Opens this {@link Portal}. */
  public void open() {
    isOpen = true;
  }

  /**
   * Returns whether or not this {@link Portal} is open.
   *
   * @return {@code true} if this {@link Portal} is open; {@code false} otherwise.
   */
  public boolean isOpen() {
    return isOpen;
  }
}
