/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * Map.java
 * Last Release: v1.0.0, 01/05/2020
 *
 * Represents the map that the players play on.
 * The map is randomly generated.
 */

package mark.cps2002;

public interface Map {

    /**
     * Get the current instance of the Map class.
     * @return The instance of Map.
     */
    Map getInstance();

    /**
     * Randomly generates the contents of the map.
     * @param boardWidth The width of the map.
     * @param boardHeight The height of the map.
     */
    void generate(int boardWidth, int boardHeight);

    /**
     * Returns the type of tile at a given position.
     * @param pos The position of the tile.
     * @return The tile's type.
     */
    TileType getTileType(Position pos);

    /**
     * Randomly selects a valid start tile from the map.
     * @return The random start tile.
     */
    Position selectRandomStartTile();

    /**
     * Gets the total width of the map.
     * @return The width.
     */
    int getWidth();

    /**
     * Gets the total height of the map.
     * @return The Height.
     */
    int getHeight();

    /**
     * Returns whether or not the map has actually been generated.
     * @return True if the map is generated, false otherwise.
     */
    boolean isGenerated();

}
