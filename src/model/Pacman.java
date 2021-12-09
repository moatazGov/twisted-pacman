package model;

import constant.Direction;
import constant.FileName;
import constant.MovableGridType;
import util.GameManager;

/**
 */
public class Pacman extends MovableGrid {

    /**
     *     from 0
     */
    public Pacman(Map map, double row, double column) {
        super(map, row, column, MovableGridType.PACMAN);

        this.setImage(FileName.IMAGE_PACMAN);
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
}
