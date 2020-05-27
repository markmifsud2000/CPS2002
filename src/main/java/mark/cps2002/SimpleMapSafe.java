/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * SimpleMapSafe.java
 * Last Release: v2.0.0 27/05/2020
 *
 * Represents the map that the players play on.
 * Takes a rectangular shape, inherits structure from SimpleMap.java
 * Randomly generates a map with 10% water tiles.
 */

package mark.cps2002;

public class SimpleMapSafe extends SimpleMap{

    final double WATER_TILE_CHANCE = 0.1;   //The chance of a tile being water

    private static SimpleMapSafe instance;  //The singleton instance for this map class

    /**
     * Create a simple map.
     */
    private SimpleMapSafe(){
        super();
    }

    /**
     * Get the current working instance of the SimpleMapSafe class.
     * @return The current instance.
     */
    public static SimpleMapSafe getInstance() {

        //Check if an instance exists
        if (instance == null) {
            instance = new SimpleMapSafe();
        }

        //Return the working instance
        return instance;
    }

    /**
     * Randomly generate the map.
     * @param boardWidth The width of the map.
     * @param boardHeight The height of the map.
     */
    public void generate(int boardWidth, int boardHeight){
        super.generateSimpleMap(boardWidth, boardHeight, WATER_TILE_CHANCE);
    }

}
