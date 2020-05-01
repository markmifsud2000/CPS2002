package mark.cps2002;

public class Position {

    private int x;  //x-coordinate
    private int y;  //y-coordinate


    /**
     * Constructor for Position
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Get the x-coordinate of the position
     * @return The x-coordinate
     */
    public int getX() {
        return this.x;
    }


    /**
     * Get the y-coordinate of the position
     * @return The y-coordinate
     */
    public int getY() {
        return this.y;
    }


    /**
     * Check if two positions are representing the same coordinates
     * @param p The position being compared to.
     * @return True if the positions are the same, false otherwise.
     */
    public boolean equals(Position p) {
        return (this.getX()==p.getX()) && (this.getY()==p.getY());
    }


    /**
     * Creates a string representation of the position
     * @return The string position
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
