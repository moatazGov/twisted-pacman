package com.hawks.twistedPacman.Model;

/**
 * The type Score.
 */
public class Score {
    private String name;
    private Integer score;

    /**
     * Instantiates a new Score with the given values
     *
     * @param name  the name
     * @param score the score
     */
    public Score(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Instantiates a new Score with empty values.
     */
    public Score() {
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
}
