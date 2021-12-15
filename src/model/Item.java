package model;

public class Item {

    public static int ID = 1;
    private boolean isThere;

    public Item(boolean isThere) {
        this.isThere = isThere;
        ID++;
    }

    public Item()
    {
        ID++;
        this.isThere = false;
    }

    public boolean isThere() {
        return isThere;
    }

    public void setThere(boolean there) {
        isThere = there;
    }

    @Override
    public String toString() {
        return "Item{" +
                "isThere=" + isThere +
                '}';
    }
}
