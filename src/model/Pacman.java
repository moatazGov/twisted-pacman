package model;

import constant.Direction;
import constant.FileName;
import constant.MovableGridType;
import util.GameManager;

import javax.security.auth.callback.PasswordCallback;
import java.util.Set;

public class Pacman extends MovableGrid {

    private int bombCount = 0;


    /**
     *     from 0
     *     Pacman starts in his position.
     *     Pacman has normal color and doesn't have a bomb.
     */
    public Pacman(Map map, double row, double column) {
        super(map, row, column, MovableGridType.PACMAN);
        this.setImage(FileName.IMAGE_PACMAN);
        setWidth(40);
        this.setX(row * getParentMap().getMapConfig().getGridLength() );
        this.setY(column * getParentMap().getMapConfig().getGridLength() );
        setHeight(40);

    }


    /**
     * Gets bombCount.
     *
     * @return the bombCount
     */
    public int getBombCount() {
        return bombCount;
    }

    /**
     * Sets bombCount.
     *
     * @param bombCount the question
     */
    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    @Override
    public boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids, double padding, double gridLength) {
        // calculate next step based on direction
        double nextX = getX();
        double nextY = getY();
        switch (direction) {
            case RIGHT:
                nextX = (getX() + getStep()) - (getX() % gridLength) + gridLength ;
                break;
            case LEFT:
                nextX = (getX() - getStep()) - (getX() % gridLength);
                break;
            case UP:
                nextY = (getY() - getStep()) - (getY() % gridLength);
                break;
            case DOWN:
                nextY = (getY() + getStep()) - (getY() % gridLength) + gridLength;
                break;
            default:
        }
        // generate a mock grid at the next step
        Pacman nextPositionGrid = new Pacman(getParentMap(), nextX , nextY );
        nextPositionGrid.setX(nextX);
        nextPositionGrid.setY(nextY);

        // check if the mock grid overlaps any obstacle
        for (Grid grid : grids) {
            if (nextPositionGrid.isTouching(grid, padding) && grid instanceof Wall) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isTouching(Grid grid, double padding) {
        double myX = this.getX();
        double myY = this.getY();
        double itsX = grid.getX();
        double itsY = grid.getY();
        double gridLength = getParentMap().getMapConfig().getGridLength();

        return myX + getStep() < itsX + gridLength
                && myX - getStep()  > itsX - gridLength
                && myY + getStep()  < itsY + gridLength
                && myY - getStep() > itsY - gridLength;
    }

    /**
     * Tests if this {@link MovableGrid} is touching a {@link Portal}. If true, sends this {@link
     * MovableGrid} to the position where the twin portal (using {@link Portal#getTwinPortal()} to
     * retrieve) stays.
     *
     * <p>This method gets all {@link Portal}s from the parent {@link Map} and calls {@link
     * Grid#isTouching(Grid, double)} to check it one by one.
     */
    @Override
    public void checkIfTouchingPortal() {
        for (Portal portal : getParentMap().getPortals()) {
            if (isTouching(portal, getParentMap().getMapConfig().getGridLength() * 0.8)) {
                if (portal.isOpen()) {
                    // send to another portal
                    setX(portal.getTwinPortal().getX());
                    setY(portal.getTwinPortal().getY());
                    // close portal
                    portal.getTwinPortal().close();
                } else {
                }
                return;
            } else {

            }
        }

        // open portals if leaving portals
        for (Portal portal : getParentMap().getPortals()) {
            portal.open();

        }
    }
    /**
     * @param direction the current direction of moving
     */
    @Override
    public void handleMove(Direction direction) {
        switch (direction) {
            case UP:
                setRotate(270);
                break;
            case DOWN:
                setRotate(90);
                break;
            case LEFT:
                setRotate(180);
                break;
            case RIGHT:
                setRotate(0);
                break;
            default:
        }
        checkTouchingCookies();
        checkTouchingBomb();
        checkHoldingBomb();

    }

    /**
     */
    private void checkTouchingCookies() {
        for (PacItem cookie : getParentMap().getPacItems()) {
            if (cookie.isExisting()
                    && isTouching(cookie, getParentMap().getMapConfig().getCookiePadding())) {
                GameManager.INSTANCE.handlePacItemTouched(cookie);
                return;
            }
        }
    }

    /**
     * if pacman touches a bomb he takes it and changes color.
     */
    private void checkTouchingBomb() {
        for (BombItem bomb : getParentMap().getBombItems()) {
            if (bomb.isExisting()
                    && isTouching(bomb, getParentMap().getMapConfig().getBombPadding())) {
                GameManager.INSTANCE.handleBombItemTouched(bomb);
                bombCount++;
                return;
            }
        }
    }

    private void checkHoldingBomb(){
        if(bombCount > 0){
            this.setImage(FileName.IMAGE_ANGRYPAC);
        }
        else{
            this.setImage(FileName.IMAGE_PACMAN);
        }
    }
}
