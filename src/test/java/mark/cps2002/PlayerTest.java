/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * PlayerTest.java
 * Last Release: v2.0.0 27/05/2020
 *
 * Unit Tests for the various functions in the Player class.
 */

package mark.cps2002;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player p;

    @Before
    public void setup() {
        //Create a player on a 10x10 board, who starts at position (5,5)
        Position start = new Position(5,5);
        p = new Player(0,10,10,start);
    }

    @After
    public void teardown() {
        p = null;
    }


    //Test Player constructor

    @Test
    public void player_allParametersValid_PlayerCreated() {
        //If all parameters are valid, the player should be created successfully

        //Start position is on map
        Position start = new Position(1,7);
        Player myPlayer = new Player(16,10,10,start);

        //Player was created with id = 16
        assertEquals(16, myPlayer.getId());

        //All tiles on grid should be covered
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //Exclude start tile
                if (i != 1 && j != 7) {
                    assertEquals(false, myPlayer.isTileRevealed(new Position(i, j)));
                }
            }
        }

        //Check start tile is revealed
        assertEquals(true, myPlayer.isTileRevealed(start));

        //Player should be in starting position
        Position currentPosition = myPlayer.getPosition();
        assertEquals(1, currentPosition.getX());
        assertEquals(7,currentPosition.getY());

        //Player notice is None
        assertEquals(PlayerNotice.NONE, myPlayer.getNotice());
    }

    @Test (expected = Exception.class)
    public void player_boardWidthNegative_throwsException(){
        //The board dimensions must be positive
        Position start = new Position(1,7);
        Player myPlayer = new Player(16,-3,10,start);
    }

    @Test (expected = Exception.class)
    public void player_boardHeightNegative_throwsException(){
        //The board dimensions must be positive
        Position start = new Position(1,7);
        Player myPlayer = new Player(16,10,-8,start);
    }

    @Test (expected = Exception.class)
    public void player_startingPositionXOutOfBounds_throwsException(){
        //The player's starting position must be within the bounds of the given map
        Position start = new Position(20,7);
        Player myPlayer = new Player(16,10,10,start);
    }

    @Test (expected = Exception.class)
    public void player_startingPositionYOutOfBounds_throwsException(){
        //The player's starting position must be within the bounds of the given map
        Position start = new Position(7,20);
        Player myPlayer = new Player(16,10,10,start);
    }

    @Test (expected = Exception.class)
    public void player_startingPositionXNegative_throwsException(){
        //The player's starting position must be within the bounds of the given map
        Position start = new Position(-3,7);
        Player myPlayer = new Player(16,10,10,start);
    }

    @Test (expected = Exception.class)
    public void player_startingPositionYNegative_throwsException(){
        //The player's starting position must be within the bounds of the given map
        Position start = new Position(7,-3);
        Player myPlayer = new Player(16,10,10,start);
    }


    //Test the move function

    @Test
    public void move_up_changesPosition() {
        //Moving up should shift the players position up by 1 from (5,5)
        boolean b = p.move(Direction.UP);

        //Y value should increase by 1
        assertEquals(true,b);
        assertEquals(5,p.getPosition().getX());
        assertEquals(4,p.getPosition().getY());
    }

    @Test
    public void move_down_changesPosition() {
        //Moving down should shift the players position down by 1 from (5,5)
        boolean b = p.move(Direction.DOWN);

        //Y value should decrease by 1
        assertEquals(true, b);
        assertEquals(5, p.getPosition().getX());
        assertEquals(6, p.getPosition().getY());
    }

    @Test
    public void move_left_changesPosition() {
        //Moving left should shift the players position left by 1 from (5,5)
        boolean b = p.move(Direction.LEFT);

        //X value should decrease by 1
        assertEquals(true,b);
        assertEquals(4,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());
    }

    @Test
    public void move_right_changesPosition() {
        //Moving right should shift the players position right by 1 from (5,5)
        boolean b = p.move(Direction.RIGHT);

        //X value should increase by 1
        assertEquals(true,b);
        assertEquals(6,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());
    }

    @Test
    public void move_up_outOfMap() {
        //If the player is in the top row, they cannot move further up
        boolean b = true;

        //Move up until the player is out of bounds
        for (int i = 0; i < 6; i++) {
            b = p.move(Direction.UP);
        }

        //Player still should be in top row (ie. still in map)
        assertEquals(false,b);
        assertEquals(5,p.getPosition().getX());
        assertEquals(0,p.getPosition().getY());

    }

    @Test
    public void move_down_outOfMap() {
        //If the player is in the bottom row, they cannot move further down
        boolean b = true;

        //Move down until the player is out of bounds
        for (int i = 0; i < 5; i++) {
            b = p.move(Direction.DOWN);
        }

        //Player should still be in bottom row (ie. still in map)
        assertEquals(false,b);
        assertEquals(5,p.getPosition().getX());
        assertEquals(9,p.getPosition().getY());

    }

    @Test
    public void move_left_outOfMap() {
        //If the player is in the leftmost column, they cannot move further left
        boolean b = true;

        //Move left until the player is out of bounds
        for (int i = 0; i < 6; i++) {
            b = p.move(Direction.LEFT);
        }

        //Player should still be in first column (ie. still in map)
        assertEquals(false,b);
        assertEquals(0,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());

    }

    @Test
    public void move_right_outOfMap() {
        //If the player is in the rightmost column, they cannot move further right
        boolean b = true;

        //Move right until the player is out of bounds
        for (int i = 0; i < 5; i++) {
            b = p.move(Direction.RIGHT);
        }

        //Player should still be in top row (ie. still in map)
        assertEquals(false,b);
        assertEquals(9,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());

    }

    @Test (expected = NullPointerException.class)
    public void move_nullDirection() {
        //Giving null direction should throw an error
        boolean b = p.move(null);
    }


    //Test the reset function

    @Test
    public void reset_playerSentToStart() {
        //When reset, the player should be returned to the starting position

        //Move the player away from the starting position
        p.move(Direction.RIGHT);
        p.move(Direction.UP);
        p.move(Direction.UP);

        //Reset the player
        p.reset();

        //Player should be back at (5,5)
        assertEquals(5,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());

    }

    @Test
    public void reset_noTilesRevealed() {
        //When reset, the player should have all tiles covered again

        //Reveal various tiles
        p.revealTile(new Position(0,0));
        p.revealTile(new Position(5,5));
        p.revealTile(new Position(3,8));
        p.revealTile(new Position(9,2));

        //Reset the player
        p.reset();

        //All tiles on grid should be covered
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //Exclude start tile
                if (i != 5 && j != 5) {
                    assertEquals(false, p.isTileRevealed(new Position(i, j)));
                }
            }
        }
    }

    @Test
    public void reset_startTileStillRevealed() {
        //The start tile should still be revealed, as the player is not placed onto it

        p.reset();

        //Player start tile should still be revealed
        assertEquals(true, p.isTileRevealed(new Position(5, 5)));
    }


    //Test reveal tile function

    @Test
    public void revealTile_tileMarkedAsRevealed() {
        //Revealing a given tile should mark the tile as revealed

        Position pos = new Position(5, 5);
        boolean b = p.revealTile(pos);

        assertEquals(true, b);
        assertEquals(true,p.isTileRevealed(pos));
    }

    @Test
    public void revealTile_tileXOutOfBounds_returnsFalse() {
        //You cannot reveal tiles that are not on the map

        Position pos = new Position(20, 5);
        boolean b = p.revealTile(pos);

        assertEquals(false, b);
    }

    @Test
    public void revealTile_tileYOutOfBounds_returnsFalse() {
        //You cannot reveal tiles that are not on the map

        Position pos = new Position(5, 20);
        boolean b = p.revealTile(pos);

        assertEquals(false, b);
    }

    @Test
    public void revealTile_tileXNegative_returnsFalse() {
        //You cannot reveal tiles that are not on the map

        Position pos = new Position(-5, 5);
        boolean b = p.revealTile(pos);

        assertEquals(false, b);
    }

    @Test
    public void revealTile_tileYNegative_returnsFalse() {
        //You cannot reveal tiles that are not on the map

        Position pos = new Position(5, -5);
        boolean b = p.revealTile(pos);

        assertEquals(false, b);
    }

    @Test
    public void revealAllTiles_tilesMarkedAsRevealed() {
        //All tiles on the map should be marked as revealed
        p.revealAllTiles();

        //All tiles on grid should be uncovered
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(true, p.isTileRevealed(new Position(i, j)));
            }
        }
    }


    //Test returnToStart function

    @Test
    public void returnToStart_playerSentToStart() {
        //The player should be returned to the starting position

        //Move the player away from the starting position
        p.move(Direction.RIGHT);
        p.move(Direction.UP);
        p.move(Direction.UP);

        //Return the player to their start position
        p.returnToStart();

        //Player should be back at (5,5)
        assertEquals(5,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());

    }


    //Tests for getter functions

    @Test
    public void isTileRevealed_checkRevealedTile_returnsTrue() {
        //If the tile is revealed, it should return true
        Position pos = new Position(5,5);
        p.revealTile(pos);
        assertEquals(true, p.isTileRevealed(pos));
    }

    @Test
    public void isTileRevealed_checkCoveredTile_returnsFalse() {
        //When the player is created, all tiles are covered. So the tile should return false
        Position pos = new Position(6,8);
        assertEquals(false, p.isTileRevealed(pos));
    }

    @Test
    public void getId_IdMatchesConstructor() {
        //Player is created with id 10
        Player p2 = new Player(10,10,10,new Position(1,1));
        assertEquals(10, p2.getId());
    }

    @Test
    public void getPosition_CurrentPositionIsStartTile() {
        //On construction, the player should be at (5,5), on their start tile
        Position currentPosition = p.getPosition();
        assertEquals(5, currentPosition.getX());
        assertEquals(5, currentPosition.getY());
    }

    @Test
    public void getStartPosition_returnsPosition() {
        //The player's start tile is (5,5)
        Position startPosition = p.getStartPosition();
        assertEquals(5, startPosition.getX());
        assertEquals(5, startPosition.getY());
    }

    @Test
    public void getNotice_NoticeUnchanged_returnsNONE() {
        //The player was just created, they should not have any notices
        PlayerNotice n = p.getNotice();
        assertEquals(PlayerNotice.NONE, n);
    }

    @Test
    public void getNotice_NoticeSetWATER_returnsWATER() {
        //If the notice has been set to water, the returned notice should also be water
        p.setNotice(PlayerNotice.WATER);
        PlayerNotice n = p.getNotice();
        assertEquals(PlayerNotice.WATER, n);
    }

    @Test
    public void hasWon_playerHasWon_returnsTrue() {
        //If the notice is set to win, then the player has won the game
        p.setNotice(PlayerNotice.WIN);
        assertEquals(true, p.hasWon());
    }

    @Test
    public void hasWon_playerHasLost_returnsFalse() {
        //If the notice is NOT set to win, then the player has not won the game
        p.setNotice(PlayerNotice.LOSE);
        assertEquals(false, p.hasWon());
    }

}
