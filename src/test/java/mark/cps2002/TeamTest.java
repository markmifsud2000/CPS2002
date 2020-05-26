/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * TeamTest.java
 * Last Release: N/A
 *
 * Unit Tests for the various functions in the Team class.
 */

package mark.cps2002;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TeamTest {

    Team t;
    Player p1;
    Player p2;
    Player p3;

    @Before
    public void setup() {
        //Setup Players
        Position start = new Position(0,0);
        p1 = new Player(0, 10, 10, start);
        p2 = new Player(1, 10, 10, start);
        p3 = new Player(2, 10, 10, start);

        //Setup team with 3 players
        t = new Team(0,10,10);

        t.addPlayer(p1);
        t.addPlayer(p2);
        t.addPlayer(p3);
    }

    @After
    public void teardown() {
        t = null;

        p1 = null;
        p2 = null;
        p3 = null;
    }



    //Test Constructor

    @Test (expected = IllegalArgumentException.class)
    public void team_negativeID_throwsException() {
        Team t2 = new Team (-5, 10, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void team_negativeBoardWidth_throwsException() {
        Team t2 = new Team (2, -3, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void team_negativeBoardHeight_throwsException() {
        Team t2 = new Team (2, 10, -3);
    }



    //Test Iterator Functions


    //Test first

    @Test
    public void first_returnsFirstPlayer() {
        Player first = t.first();
        assertEquals(p1, first);
    }

    @Test
    public void first_emptyTeam_returnsNull() {
        Team t2 = new Team(1,10,10);
        Player first = t2.first();
        assertEquals(null, first);
    }

    //Test next

    @Test
    public void next_returnsNextPlayer() {
        Player next = t.next();
        assertEquals(p1, next);
    }

    @Test
    public void next_next2times_returnsPlayer2() {
        t.next();                   //p1
        Player next = t.next();     //p2
        assertEquals(p2, next);
    }

    @Test
    public void next_noNextPlayer_returnsNull() {
        t.next();   //p1
        t.next();   //p2
        t.next();   //p3

        Player next = t.next(); //null
        assertEquals(null, next);
    }

    @Test
    public void next_emptyTeam_returnsNull() {
        Team t2 = new Team(1,10,10);
        Player next = t2.next();
        assertEquals(null, next);
    }

    //Test hasNext

    @Test
    public void hasNext_nextPlayerExists_returnsTrue() {
        boolean b = t.hasNext();
        assertEquals(true, b);
    }

    @Test
    public void hasNext_emptyTeam_returnsFalse() {
        Team t2 = new Team(1,10,10);
        boolean b = t2.hasNext();
        assertEquals(false, b);
    }

    @Test
    public void hasNext_endOfTeam_returnsFalse() {
        t.next();   //p1
        t.next();   //p2
        t.next();   //p3

        boolean b = t.hasNext();
        assertEquals(false, b);
    }

    //Test getCurrent

    @Test
    public void getCurrent_returnsFirstPlayer() {
        Player p = t.getCurrent();
        assertEquals(p1, p);
    }

    @Test
    public void getCurrent_next_returnsSecondPlayer() {
        t.next();   //p1
        t.next();   //p2
        Player p = t.getCurrent();
        assertEquals(p2, p);
    }

    @Test
    public void getCurrent_emptyTeam_returnsNull() {
        Team t2 = new Team(1,10,10);
        Player p = t2.getCurrent();
        assertEquals(null, p);
    }

    @Test
    public void getCurrent_endOfTeam_returnsNull() {
        t.next();   //p1
        t.next();   //p2
        t.next();   //p3
        t.next();   //null

        Player p = t.getCurrent();
        assertEquals(null, p);
    }



    //Test team management


    //Test size

    @Test
    public void size_teamOf3Players_returns3() {
        int size = t.size();
        assertEquals(3, size);
    }

    @Test
    public void size_emptyTeam_returns0() {
        Team t2 = new Team(1,10,10);
        int size = t2.size();
        assertEquals(0, size);
    }


    //Test getId

    @Test
    public void getId_returnsId() {
        int id = t.getId();
        assertEquals(0, id);
    }


    //Test addPlayer

    @Test
    public void addPlayer_playerAddedToTeam() {
        //Create a new Player
        Position start = new Position(0,0);
        Player p4 = new Player(3, 10, 10, start);

        //Add the player
        t.addPlayer(p4);

        //get players
        t.next();   //p1
        t.next();   //p2
        t.next();   //p3
        Player addedPlayer = t.next();

        assertEquals(p4, addedPlayer);
    }

    @Test
    public void addPlayer_emptyTeam_playerAdded() {
        //Create a new Player
        Position start = new Position(0,0);
        Player p4 = new Player(3, 10, 10, start);

        //Create new team and add player
        Team t2 = new Team(1,10,10);
        t2.addPlayer(p4);


        Player added = t2.first();
        assertEquals(p4, added);
    }

    @Test
    public void addPlayer_addNullPlayer_returnsFalse() {
        Player p4 = null;
        Boolean b = t.addPlayer(p4);
        assertEquals(false, b);
    }

    //Test updateTeamMap & isTileRevealed

    @Test
    public void isTileRevealed_mapNotUpdated_noTileRevealed() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Position pos = new Position(i, j);
                assertEquals(false, t.isTileRevealed(pos));
            }
        }

    }

    @Test
    public void isTileRevealed_mapUpdated_startTileRevealed() {
        t.updateTeamMaps();
        Position start = new Position(0, 0);
        boolean b = t.isTileRevealed(start);

        assertEquals(true, b);
    }

    @Test
    public void updatePlayerMap_playerTilesRevealed_teamMapRevealed() {

        //Reveal tiles to player
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(7,2);
        Position pos3 = new Position(1,8);

        p1.revealTile(pos1);
        p2.revealTile(pos2);
        p3.revealTile(pos3);

        //Update
        t.updateTeamMaps();

        //Check if tiles are revealed
        assertEquals(true, t.isTileRevealed(pos1));
        assertEquals(true, t.isTileRevealed(pos2));
        assertEquals(true, t.isTileRevealed(pos3));

    }


}
