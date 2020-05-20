/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * SimpleMapSafe.java
 * Last Release: N/A
 *
 * Represents the map that the players play on.
 * Takes a rectangular shape, inherits structure from SimpleMap.java
 * Randomly generates a map with 10% water tiles.
 */

package mark.cps2002;

public class SimpleMapSafe extends SimpleMap{

    final double WATER_TILE_CHANCE = 0.1;   //The chance of a tile being water

    /**
     * Create a simple map of a given size.
     * @param boardWidth The width of the map.
     * @param boardHeight The height of the map.
     */
    public SimpleMapSafe(int boardWidth, int boardHeight){
        super(boardWidth, boardHeight);
    }

    /**
     * Randomly generate the map.
     */
    public void generate(){
        super.generateSimpleMap(WATER_TILE_CHANCE);
    }

}
