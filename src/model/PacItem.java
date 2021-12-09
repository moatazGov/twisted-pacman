package model;

import constant.FileName;

/**
 *
 *
 * <h1>Cookie</h1>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public class PacItem extends Grid {

    /** The value of {@link Score} of this cookie. */
    private int value;

    /**
     */
    public PacItem(Map map, double row, double column, int value) {
        super(map, row, column);

        switch (value) {
            case 1:
                this.setImage(FileName.IMAGE_COOKIE_SMALL);
                break;
            case 5:
                this.setImage(FileName.IMAGE_COOKIE_MEDIUM);
                break;
            case 10:
                this.setImage(FileName.IMAGE_COOKIE_BIG);
                break;
            default:
                this.setImage(FileName.IMAGE_COOKIE_SMALL);
        }

        this.value = value;
    }

    /**
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
