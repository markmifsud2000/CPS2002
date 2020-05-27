/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * GameTest.java
 * Last Modified: v1.0.0, 01/05/2020
 *
 * Unit Tests for the various functions in the Game class.
 */


package mark.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;


public class GameTest {

    Game myGame;
    Game teamGame;
    String outputDirPath;
    File outputDir;

    @Before
    public void setup(){
        //Create a new Game with 4 players and a 10x15 board.
        myGame = new Game(4, 10,15, "Safe", 0);
        teamGame = new Game(8, 10, 15, "Safe", 3);
        outputDirPath = "playerHtmlOutput";
        outputDir = new File(outputDirPath);
    }

    @After
    public void teardown() {
        myGame = null;
        outputDirPath = null;
        outputDir.delete();
    }


    //Test Game Constructor

    @Test
    public void game_constructGame_valuesInitialised() {
        //Game should have 4 players and be on turn 0
        assertEquals(4 ,myGame.getNumberOfPlayers());
        assertEquals(0, myGame.getTurnNumber());
        assertEquals(false, myGame.isGameFinished());
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_lessThan2Players_throwsException() {
        //Game should not be constructed with less than 2 players
        Game badGame = new Game (0, 10,10, "Safe", 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_moreThan8Players_throwsException() {
        //Game should not be constructed with more than 8 players
        Game badGame = new Game (12, 10,10, "Safe", 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_boardTooSmall_throwsException() {
        //Game should not be constructed if the board is too small
        Game badGame = new Game (5, 6,6, "Safe", 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_boardTooBig_throwsException() {
        //Game should not be constructed if board is too big
        Game badGame = new Game (5, 100,100, "Safe", 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_badMapType_throwsException() {
        //Game cannot be constructed since the map type does not exist
        Game badGame = new Game (5, 10, 10, "Hello", 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_negativeTeamCount_throwsException() {
        //Game cannot be constructed since the map type does not exist
        Game badGame = new Game (5, 10, 10, "Safe", -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_moreTeamsThanPlayers_throwsException() {
        //Game cannot be constructed since the map type does not exist
        Game badGame = new Game (5, 10, 10, "Safe", 7);
    }

    @Test
    public void game_createsOutputDirectory() {
        //Game constructor was called in setup, check if the directory exists
        assertEquals(true, outputDir.exists());
        assertEquals(true, outputDir.isDirectory());
    }


    //Tests for checkPlayerInput

    @Test
    public void checkPlayerInput_playerInputsUp_returnsDirectionUp() {
        //User entering U should return Up
        Position pos = new Position(5, 5);
        assertEquals(Direction.UP, myGame.checkPlayerInput("U", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsUp_outOfBounds_returnsNull() {
        //If the user is at the top of the map, user should not be able to move up
        Position pos = new Position(5, 0);
        assertEquals(null, myGame.checkPlayerInput("U", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsDown_returnsDirectionDown() {
        //User entering D should return Down
        Position pos = new Position(5, 5);
        assertEquals(Direction.DOWN, myGame.checkPlayerInput("D", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsDown_outOfBounds_returnsNull() {
        //If the user is at the bottom of the map, user should not be able to move down
        Position pos = new Position(5, 14);
        assertEquals(null, myGame.checkPlayerInput("D", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsLeft_returnsDirectionLeft() {
        //User entering L should return Left
        Position pos = new Position(5, 5);
        assertEquals(Direction.LEFT, myGame.checkPlayerInput("L", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsLeft_outOfBounds_returnsNull() {
        //If the user is in the leftmost column of the map, user should not be able to move left
        Position pos = new Position(0, 5);
        assertEquals(null, myGame.checkPlayerInput("L", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsRight_returnsDirectionRight() {
        //User entering R should return Right
        Position pos = new Position(5, 5);
        assertEquals(Direction.RIGHT, myGame.checkPlayerInput("R", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsRight_outOfBounds_returnsNull() {
        //If the user is in the rightmost column of the map, user should not be able to move right
        Position pos = new Position(9, 5);
        assertEquals(null, myGame.checkPlayerInput("R", pos));
    }

    @Test
    public void checkPlayerInput_invalidString_returnsNull() {
        //If the user does not enter U/D/R/L, then their input was invalid.
        Position pos = new Position(5, 5);
        assertEquals(null, myGame.checkPlayerInput("Hello World", pos));
    }


    //Test Getter Methods

    @Test
    public void getNumberOfPlayers_4Players_returns4() {
        //Game has 4 players
        assertEquals(4, myGame.getNumberOfPlayers());
    }

    @Test
    public void getNumberOfTeams_NoTeams_returns0() {
        //Game has no teams
        assertEquals(0, myGame.getNumberOfTeams());
    }

    @Test
    public void getNumberOfTeams_teamGame_returns3() {
        //Game has 3 teams
        assertEquals(3, teamGame.getNumberOfTeams());
    }

    @Test
    public void getTurnNumber_gameJustStarted_returns0() {
        //No turns have been played yet, turn should be 0
        assertEquals(0, myGame.getTurnNumber());
    }

    @Test
    public void isGameFinished_gameJustStarted_returnsFalse() {
        //No turns have been played yet, so the game has not finished
        assertEquals(false, myGame.isGameFinished());
    }


    //Test html & output directory functions

    @Test
    public void generateHtml_givenPlayer1_fileIsCreated() {
        //Given player 1, the file map_player_1.html should be created
        Position start = new Position(3,2);
        Player p1 = new Player(0, 10, 15, start);

        myGame.generateHTML(p1);
        File htmlOutput = new File(outputDirPath + "/map_player_1.html");

        //Check if the file exists
        assertEquals(true, htmlOutput.exists());
    }

    @Test
    public void generateTeamHtml_givenTeam1Player1_fileIsCreated() {
        //Given player 1, the file map_player_1.html should be created
        Position start = new Position(3,2);
        Player p1 = new Player(0, 10, 15, start);
        Team t1 = new Team(0, 10, 15);

        myGame.generateTeamHTML(t1, p1);
        File htmlOutput = new File(outputDirPath + "/map_player_1.html");

        //Check if the file exists
        assertEquals(true, htmlOutput.exists());
    }

    @Test
    public void clearOutputDirectory_directoryHasFiles_directoryIsEmptied() {
        //Place files in the output directory, when it is cleared, the all files should be removed

        //Create an output file
        Position start = new Position(3,2);
        Player p1 = new Player(0, 10, 15, start);
        myGame.generateHTML(p1);

        myGame.clearOutputDirectory();
        File[] allOutputs = outputDir.listFiles();

        //Check that the list of files in the output directory is empty
        assertEquals(0, allOutputs.length);
    }
}
