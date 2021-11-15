package com.hawks.twistedPacman.Model;

import java.util.Objects;

public class PacItem extends Item {
    private Integer score;

    public PacItem(boolean isThere, Integer score) {
        super(isThere);
        this.score = score;
    }

    public PacItem() {
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }




    //todo document this method
    private boolean eaten(){
        return true;
    }
}
