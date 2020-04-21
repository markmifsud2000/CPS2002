package mark.cps2002;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player p;

    @Before
    public void setup() {
        Position start = new Position(5,5);
        p = new Player(0,10,10,start);
    }

    @After
    public void teardown() {
        p = null;
    }

    @Test
    public void player_allParametersValid_PlayerCreated() {
        Position start = new Position(1,7);
        Player myPlayer = new Player(16,10,10,start);

        assertEquals(16, myPlayer.getId());

        //All tiles on grid should be covered
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(false, myPlayer.isTileRevealed(new Position(i, j)));
            }
        }

        //Player should be in starting position
        Position currentPosition = myPlayer.getPosition();
        assertEquals(1, currentPosition.getX());
        assertEquals(7,currentPosition.getY());
    }

    @Test (expected = Exception.class)
    public void player_boardWidthNegative_throwsException(){
        Position start = new Position(1,7);
        Player myPlayer = new Player(16,-3,10,start);
    }

    @Test (expected = Exception.class)
    public void player_boardHeightNegative_throwsException(){
        Position start = new Position(1,7);
        Player myPlayer = new Player(16,10,-8,start);
    }

    @Test (expected = Exception.class)
    public void player_startingPositionOutOfBounds_throwsException(){
        Position start = new Position(20,7);
        Player myPlayer = new Player(16,10,10,start);
    }

    @Test
    public void move_up_changesPosition() {
        boolean b = p.move(Direction.UP);

        //Y value should increase by 1
        assertEquals(true,b);
        assertEquals(5,p.getPosition().getX());
        assertEquals(4,p.getPosition().getY());
    }

    @Test
    public void move_down_changesPosition() {
        boolean b = p.move(Direction.DOWN);

        //Y value should decrease by 1
        assertEquals(true, b);
        assertEquals(5, p.getPosition().getX());
        assertEquals(6, p.getPosition().getY());
    }

    @Test
    public void move_left_changesPosition() {
        boolean b = p.move(Direction.LEFT);

        //X value should decrease by 1
        assertEquals(true,b);
        assertEquals(4,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());
    }

    @Test
    public void move_right_changesPosition() {
        boolean b = p.move(Direction.RIGHT);

        //X value should increase by 1
        assertEquals(true,b);
        assertEquals(6,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());
    }

    @Test
    public void move_up_outOfMap() {
        boolean b = true;

        //Move up until the player is out of bounds
        for (int i = 0; i < 6; i++) {
            b = p.move(Direction.UP);
        }

        //Player should be in top row (ie. still in map)
        assertEquals(false,b);
        assertEquals(5,p.getPosition().getX());
        assertEquals(0,p.getPosition().getY());

    }

    @Test
    public void move_down_outOfMap() {
        boolean b = true;

        //Move down until the player is out of bounds
        for (int i = 0; i < 5; i++) {
            b = p.move(Direction.DOWN);
        }

        //Player should be in bottom row (ie. still in map)
        assertEquals(false,b);
        assertEquals(5,p.getPosition().getX());
        assertEquals(9,p.getPosition().getY());

    }

    @Test
    public void move_left_outOfMap() {
        boolean b = true;

        //Move left until the player is out of bounds
        for (int i = 0; i < 6; i++) {
            b = p.move(Direction.LEFT);
        }

        //Player should be in first column (ie. still in map)
        assertEquals(false,b);
        assertEquals(0,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());

    }

    @Test
    public void move_right_outOfMap() {
        boolean b = true;

        //Move right until the player is out of bounds
        for (int i = 0; i < 5; i++) {
            b = p.move(Direction.RIGHT);
        }

        //Player should be in top row (ie. still in map)
        assertEquals(false,b);
        assertEquals(9,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());

    }

    @Test
    public void reset_playerSentToStart() {
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
                assertEquals(false, p.isTileRevealed(new Position(i, j)));
            }
        }
    }

    @Test
    public void reset_clearNotice() {
        p.setNotice(PlayerNotice.WATER);
        p.reset();

        //Player notice should be none
        assertEquals(PlayerNotice.NONE,p.getNotice());
    }

    @Test
    public void revealTile_tileMarkedAsRevealed() {
        Position pos = new Position(5, 5);
        boolean b = p.revealTile(pos);

        assertEquals(true, b);
        assertEquals(true,p.isTileRevealed(pos));
    }

    @Test
    public void revealTile_tileOutOfBounds_returnsFalse() {
        Position pos = new Position(20, 20);
        boolean b = p.revealTile(pos);

        assertEquals(false, b);
    }

    @Test
    public void revealAllTiles_tilesMarkedAsRevealed() {
        p.revealAllTiles();

        //All tiles on grid should be uncovered
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(true, p.isTileRevealed(new Position(i, j)));
            }
        }
    }

    @Test
    public void isTileRevealed_checkRevealedTile_returnsTrue() {
        Position pos = new Position(5,5);
        p.revealTile(pos);
        assertEquals(true, p.isTileRevealed(pos));
    }

    @Test
    public void isTileRevealed_checkCoveredTile_returnsFalse() {
        Position pos = new Position(5,5);
        assertEquals(false, p.isTileRevealed(pos));
    }

    @Test
    public void getId_IdMatchesConstructor() {
        Player p2 = new Player(10,10,10,new Position(1,1));
        assertEquals(10, p2.getId());
    }

    @Test
    public void getPosition_CurrentPositionIsStartTile() {
        Position currentPosition = p.getPosition();
        assertEquals(5, currentPosition.getX());
        assertEquals(5, currentPosition.getY());
    }

    @Test
    public void getNotice_NoticeUnchanged_returnsNONE() {
        PlayerNotice n = p.getNotice();
        assertEquals(PlayerNotice.NONE, n);
    }

    @Test
    public void getNotice_NoticeSetWATER_returnsWATER() {
        p.setNotice(PlayerNotice.WATER);
        PlayerNotice n = p.getNotice();
        assertEquals(PlayerNotice.WATER, n);
    }

    @Test
    public void hasWon_playerHasWon_returnsTrue() {
        p.setNotice(PlayerNotice.WIN);
        assertEquals(true, p.hasWon());
    }

    @Test
    public void hasWon_playerHasLost_returnsFalse() {
        p.setNotice(PlayerNotice.LOSE);
        assertEquals(false, p.hasWon());
    }

}
