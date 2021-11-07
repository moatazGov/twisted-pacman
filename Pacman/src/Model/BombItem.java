package Model;

/**
 * The type Bomb item.
 */
public class BombItem {
    private Color color;
    private boolean isThere;


    /**
     * Instantiates a new Bomb item.
     *
     * @param color   the color
     * @param isThere the is there
     */
    public BombItem(Color color, boolean isThere) {
        this.color = color;
        this.isThere = isThere;
    }

    /**
     * Instantiates a new Bomb item.
     */
    public BombItem() {
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Is there boolean.
     *
     * @return the boolean
     */
    public boolean isThere() {
        return isThere;
    }

    /**
     * Sets there.
     *
     * @param there the there
     */
    public void setThere(boolean there) {
        isThere = there;
    }
}
