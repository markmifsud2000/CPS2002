/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * TileType.java
 * Last Modified: v1.0.0, 01/05/2020
 *
 * TileType enumeration class provides a simple way to refer to different tiles on the map.
 * Each different tile type results in a different affect when a player lands on it.
 */


package mark.cps2002;

public enum TileType {
    GRASS,      //The tile is grass, nothing happens when a player lands on this
    WATER,      //The tile is water, if a player lands on it, they should be sent back to the start
    TREASURE    //The tile is the one treasure tile, if a player lands on it, they have won and the game is over
}
