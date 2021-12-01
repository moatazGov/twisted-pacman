package com.hawks.twistedPacman.Model;

/**
 * The type Pacman.
 */
public class Pacman {
    private Location location;
    private Integer score;
    private Integer lives;
    private Double speed;
    private boolean canBomb;


    /**
     * Instantiates a new Pacman.
     *
     * @param location the location
     * @param score    the score
     * @param lives    the lives
     * @param speed    the speed
     * @param canBomb  the can bomb
     */
    public Pacman(Location location, Integer score, Integer lives, Double speed, boolean canBomb) {
        this.location = location;
        this.score = score;
        this.lives = lives;
        this.speed = speed;
        this.canBomb = canBomb;
    }

    /**
     * Instantiates a new Pacman.
     */
    public Pacman() {
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
     * Gets score.
     *
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Integer getLives() {
        return lives;
    }

    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    public void setLives(Integer lives) {
        this.lives = lives;
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

    /**
     * Is can bomb boolean.
     *
     * @return the boolean
     */
    public boolean isCanBomb() {
        return canBomb;
    }

    /**
     * Sets can bomb.
     *
     * @param canBomb the can bomb
     */
    public void setCanBomb(boolean canBomb) {
        this.canBomb = canBomb;
    }

    //todo document this method
    private boolean strike(){
        return true;
    }
}
