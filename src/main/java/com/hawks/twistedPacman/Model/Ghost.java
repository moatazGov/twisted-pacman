package com.hawks.twistedPacman.Model;

/**
 * The type Ghost.
 */
public class Ghost {
    /**
     * The Location of the ghost during the game.
     */
    private Location location;
    /**
     * The Speed of the ghost, changes according to the game's state.
     */
    private Double speed;


    /**
     * Instantiates a new Ghost with empty fields.
     */
    public Ghost() {
    }

    /**
     * Instantiates a new Ghost with the given location and speed.
     *
     * @param location the location
     * @param speed    the speed
     */
    public Ghost(Location location, Double speed) {
        this.location = location;
        this.speed = speed;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }


    //todo document the method
    boolean strike(){
        return true;
    }
}
