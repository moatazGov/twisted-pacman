package model;

import constant.FileName;

import java.util.Timer;
import java.util.TimerTask;

/**
 * PacItem, also referred to as PacItem.
 */
public class PacItem extends Grid {

    /**
     * a timer to make the cookies reappear after 30 seconds
     */
    Timer timer = new Timer();
    /**
     * The value of {@link Score} of this cookie.
     */
    private int value;

    /**
     *
     */
    public PacItem(Map map, double row, double column) {
        super(map, row, column);
        this.setImage(FileName.IMAGE_BIG);
        this.value = 1;
    }

    /**
     *
     */
    public int getValue() {
        return value;
    }

    /**
     * Makes this {@link } eaten and disappear in the screen.
     *
     * <p>The principle of this method is just make it invisible.
     */
    public void eat() {
        setVisible(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setVisible(true);
            }
        }, 30 * 1000);
    }

    /**
     * Tests if this {@link } still exists (i.e. not eaten yet) in the screen.
     *
     * @return {@code true} if this {@link } still exists; {@code false} otherwise.
     */
    public boolean isExisting() {
        return isVisible();
    }
}
