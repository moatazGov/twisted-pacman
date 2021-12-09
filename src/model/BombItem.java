package model;

/**
 * The type Bomb item.
 */
public class BombItem extends Item {
    private Color color;


    /**
     * Instantiates a new Bomb item.
     *
     * @param color   the color
     */
    public BombItem(boolean isThere, Color color) {
        super(isThere);
        this.color = color;
    }

    public BombItem()
    {

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



}
