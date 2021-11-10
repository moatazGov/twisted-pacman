package com.hawks.twistedPacman.Model;


import java.util.Objects;

/**
 * The type Location.
 */
public class Location {

    private Integer row;
    private Integer column;

    /**
     * Instantiates a new Location.
     *
     * @param row    the row
     * @param column the column
     */
    public Location(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Instantiates a new Location.
     */
    public Location() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getRow().equals(location.getRow()) && getColumn().equals(location.getColumn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public Integer getRow() {
        return row;
    }

    /**
     * Sets row.
     *
     * @param row the row
     */
    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(Integer column) {
        this.column = column;
    }
}
