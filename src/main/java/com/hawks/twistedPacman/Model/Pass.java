package com.hawks.twistedPacman.Model;

import java.util.Objects;

public class Pass {
    private Location from;
    private Location to;

    public Pass(Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    public Pass() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pass)) return false;
        Pass pass = (Pass) o;
        return Objects.equals(getFrom(), pass.getFrom()) && Objects.equals(getTo(), pass.getTo());
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
