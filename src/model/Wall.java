package model;

public class Wall extends Grid {

    public Wall(Map map, double row, double column) {
        super(map, row, column);

        this.setImage(map.getWallFileName());
    }
}
