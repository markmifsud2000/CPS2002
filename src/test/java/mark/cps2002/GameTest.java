package mark.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    Game myGame;

    @Before
    public void setup(){
        myGame = new Game(4, 10,15);
    }

    public void teardown() {
        myGame = null;
    }

    @Test
    public void game_constructGame_valuesInitialised() {
        assertEquals(4 ,myGame.getNumberOfPlayers());
        assertEquals(0, myGame.getTurnNumber());
        assertEquals(false, myGame.isGameFinished());
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_lessThan2Players_throwsException() {
        Game badGame = new Game (0, 10,10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_moreThan8Players_throwsException() {
        Game badGame = new Game (12, 10,10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_boardTooSmall_throwsException() {
        Game badGame = new Game (5, 6,6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void game_boardTooBig_throwsException() {
        Game badGame = new Game (5, 100,100);
    }

    @Test
    public void checkPlayerInput_playerInputsUp_returnsDirectionUp() {
        Position pos = new Position(5, 5);
        assertEquals(Direction.UP, myGame.checkPlayerInput("U", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsUp_outOfBounds_returnsNull() {
        Position pos = new Position(5, 0);
        assertEquals(null, myGame.checkPlayerInput("U", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsDown_returnsDirectionDown() {
        Position pos = new Position(5, 5);
        assertEquals(Direction.DOWN, myGame.checkPlayerInput("D", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsDown_outOfBounds_returnsNull() {
        Position pos = new Position(5, 14);
        assertEquals(null, myGame.checkPlayerInput("D", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsLeft_returnsDirectionLeft() {
        Position pos = new Position(5, 5);
        assertEquals(Direction.LEFT, myGame.checkPlayerInput("L", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsLeft_outOfBounds_returnsNull() {
        Position pos = new Position(0, 5);
        assertEquals(null, myGame.checkPlayerInput("L", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsRight_returnsDirectionRight() {
        Position pos = new Position(5, 5);
        assertEquals(Direction.RIGHT, myGame.checkPlayerInput("R", pos));
    }

    @Test
    public void checkPlayerInput_playerInputsRight_outOfBounds_returnsNull() {
        Position pos = new Position(9, 5);
        assertEquals(null, myGame.checkPlayerInput("R", pos));
    }

    @Test
    public void checkPlayerInput_invalidString_returnsNull() {
        Position pos = new Position(5, 5);
        assertEquals(null, myGame.checkPlayerInput("Hello World", pos));
    }

    @Test
    public void getNumberOfPlayers_4Players_returns4() {
        assertEquals(4, myGame.getNumberOfPlayers());
    }

    @Test
    public void getTurnNumber_gameJustStarted_returns0() {
        assertEquals(0, myGame.getTurnNumber());
    }

    @Test
    public void isGameFinished_gameJustStarted_returnsFalse() {
        assertEquals(false, myGame.isGameFinished());
    }
}
