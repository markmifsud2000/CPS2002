/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * Launcher.java
 * Last Release: v2.0.0, 27/05/2020
 *
 * Contains executable main method.
 * Creates and starts a game with given parameters.
 */


package mark.cps2002;

public class Launcher {

    public static void main(String[] args) {
        //Create a game with 3 players on a 10x14 board
        Game myGame = new Game(5, 8, 8, "Safe", 2);
        myGame.startGame();
    }

}
