package engine;

import java.io.Serializable;

/**
 * Position is a precise point on the Board which has two coordinates.
 *
 * @version 1.0
 */
public class Position implements Serializable {

    /**
     * Coordinate x
     */
    private int Xcoordinate;

    /**
     * Coordinate Y
     */
    private int Ycoordinate;

    /**
     * Constructs a position with two coordinates.
     *
     * @param Xcoordinate Coordinate x
     * @param Ycoordinate Coordinate y
     */
    Position(int Xcoordinate, int Ycoordinate) {
        this.Xcoordinate = Xcoordinate;
        this.Ycoordinate = Ycoordinate;
    }

    /**
     * Gets the x coordinate.
     * @return The x coordinate
     */
    public int getXCoordinate() {
        return this.Xcoordinate;
    }

    /**
     * Gets the y coordinate.
     * @return The y coordinate
     */
    public int getYCoordinate() {
        return this.Ycoordinate;
    }

    /**
     * Sets the x coordinate with the given one
     * @param xCoordinate New x coordinate
     */
    void setXCoordinate(int xCoordinate) {
        this.Xcoordinate = xCoordinate;
    }

    /**
     * Sets the y coordinate with the given one
     * @param yCoordinate New y coordinate
     */
    void setYCoordinate(int yCoordinate) {
        this.Ycoordinate = yCoordinate;
    }

    /**
     * String version of a Position.
     *
     * @return String version of a Position
     */
    public String toString() {
        return "X : "+this.getXCoordinate()+"\tY : "+this.getYCoordinate();
    }
}
