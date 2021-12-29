package model;

import constant.FileName;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Bomb item.
 */
public class BombItem extends Grid {

    /**
     * a timer to make the cookies reappear after 30 seconds
     */
    Timer timer = new Timer();

    /**
     *
     * @param map
     * @param row
     * @param column
     */
    public BombItem(Map map, double row, double column) {
        super(map, row, column);
        this.setImage(FileName.IMAGE_BOMB);
    }


    /**
     * Makes this {@link } eaten and disappear in the screen.
     *
     * <p>The principle of this method is just make it invisible,
     * and make it reappear after 30 seconds.
     *
     */
    public void eat() {
        setVisible(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setVisible(true);
            }
        }, 30*1000);
    }

    /**
     * @return True if the item is visible on the screen.
     */
    public boolean isExisting() {
        return isVisible();
    }
}
