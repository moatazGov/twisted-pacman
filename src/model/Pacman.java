package model;

import constant.Direction;
import constant.FileName;
import constant.MovableGridType;
import util.GameManager;

/**
 */
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
