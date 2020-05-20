/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * Launcher.java
 * Last Modified: v1.0.0, 01/05/2020
 *
 * Contains executable main method.
 * Creates and starts a game with given parameters.
 */


package mark.cps2002;

public class Launcher {

    public static void main(String[] args) {
        //Create a game with 3 players on a 10x14 board
        Game myGame = new Game(3, 10, 14, "Safe");
        myGame.startGame();
    }

}
