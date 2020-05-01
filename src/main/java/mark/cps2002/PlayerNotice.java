/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * PlayerNotice.java
 * Last Modified: v1.0.0, 01/05/2020
 *
 * Values in the enumeration class represent any notifications that need to be passed on to the player at the start
 * of the next turn.
 * These represent a significant change in the players position, or any changes to the game's state.
 */


package mark.cps2002;

public enum PlayerNotice {
    NONE,   //There are no notices to display to the user
    WATER,  //The player has just hit a water tile
    WIN,    //Game is over, the player has won
    LOSE    //Game is over, the player has lost
}
