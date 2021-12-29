package model;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {

    /**
     * The value of the score.
     */
    private int value;
    /**
     * The player's name.
     */
    private String nickname;
    /**
     * The time when the score settles.
     */
    private Date time;

    /**
     * Allocates a new {@link Score} object.
     *
     * <p>This constructor initializes the {@link #value} to {@code 0}, records the {@link #nickname}
     * and sets the {@link #time} to the current time.
     *
     * @param nickname the player's nickname.
     */
    public Score(String nickname) {
        this.value = 0;
        this.nickname = nickname;
        this.time = new Date();
    }

    /**
     * Allocates a new {@link Score} object.
     *
     * <p>This constructor does the same thing as {@link Score} does, but with a default {@link
     * #nickname} {@code "Unknown Player"} initialized.
     */
    public Score() {
        this("Unknown Player");
    }

    /**
     * Increases the {@link #value} by the given {@code value}.
     *
     * @param value an integer to be added to the {@link #value}.
     */
    public Integer gain(int value) {
        this.value += value;
        return this.value;
    }

    /**
     * Decreases the {@link #value} by the given {@code value}.
     *
     * @param value an integer to be added to the {@link #value}.
     */
    public void lose(int value) {
        this.value -= value;
    }

    /**
     * Returns the {@link #value}.
     *
     * @return an integer indicating the value of score.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the player's {@link #nickname}.
     *
     * @return the player's {@link #nickname}.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Returns the time when the {@link Score} is settled.
     *
     * @return the time when the {@link Score} is settled.
     */
    public Date getTime() {
        return time;
    }
}
