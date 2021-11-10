package com.hawks.twistedPacman.Model;

import java.util.Objects;

public class Wall {
    private Location from;
    private Location to;

    public Wall(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    public Wall() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall)) return false;
        Wall wall = (Wall) o;
        return getFrom().equals(wall.getFrom()) && getTo().equals(wall.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo());
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }
}
