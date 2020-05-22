/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * SimpleMapHazard.java
 * Last Release: N/A
 *
 * Represents the map that the players play on.
 * Takes a rectangular shape, inherits structure from SimpleMap.java
 * Randomly generates a map with 30% water tiles.
 */

package mark.cps2002;

public class SimpleMapHazard extends SimpleMap{

    final double WATER_TILE_CHANCE = 0.3;   //The chance of a tile being water

    /**
     * Create a simple map.
     */
    private SimpleMapHazard(){
        super();
    }

    /**
     * Get the current working instance of the SimpleMapSafe class.
     * @return The current instance.
     */
    public static SimpleMapHazard getInstance() {
        return new SimpleMapHazard();
    }

    /**
     * Randomly generates the map.
     * @param boardWidth The width of the map.
     * @param boardHeight The height of the map.
     */
    public void generate(int boardWidth, int boardHeight){
        super.generateSimpleMap(boardWidth, boardHeight, WATER_TILE_CHANCE);
    }


}
