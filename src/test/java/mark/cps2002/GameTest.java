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
