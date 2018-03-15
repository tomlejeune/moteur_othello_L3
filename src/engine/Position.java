package engine;

/**
 * Position is a precise point on the Board which has two coordinates.
 *
 * @version 1.0
 */
public class Position {

    /**
     * Coordinate x
     */
    protected int Xcoordinate;

    /**
     * Coordinate Y
     */
    protected int Ycoordinate;

    /**
     * Constructs a position with two coordinates.
     *
     * @param Xcoordinate Coordinate x
     * @param Ycoordinate Coordinate y
     */
    public Position(int Xcoordinate, int Ycoordinate) {
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
     * String version of a Position.
     *
     * @return String version of a Position
     */
    public String toString() {
        return "X : "+this.getXCoordinate()+"\tY : "+this.getYCoordinate();
    }
}
