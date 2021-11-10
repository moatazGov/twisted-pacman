package com.hawks.twistedPacman.Model;

import java.util.Objects;

public class PacItem {
    private Integer score;
    private boolean isThere;

    public PacItem(Integer score, boolean isThere) {
        this.score = score;
        this.isThere = isThere;
    }

    public PacItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PacItem)) return false;
        PacItem pacItem = (PacItem) o;
        return isThere() == pacItem.isThere() && Objects.equals(getScore(), pacItem.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScore(), isThere());
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isThere() {
        return isThere;
    }

    public void setThere(boolean there) {
        isThere = there;
    }

    //todo document this method
    private boolean eaten(){
        return true;
    }
}
