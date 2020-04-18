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
    public void move_up_changes_position() {
        boolean b = p.move(Direction.UP);

        //Y value should increase by 1
        assertEquals(true,b);
        assertEquals(5,p.getPosition().getX());
        assertEquals(4,p.getPosition().getY());
    }

    @Test
    public void move_down_changes_position() {
        boolean b = p.move(Direction.DOWN);

        //Y value should decrease by 1
        assertEquals(true, b);
        assertEquals(5, p.getPosition().getX());
        assertEquals(6, p.getPosition().getY());
    }

    @Test
    public void move_left_changes_position() {
        boolean b = p.move(Direction.LEFT);

        //X value should decrease by 1
        assertEquals(true,b);
        assertEquals(4,p.getPosition().getX());
        assertEquals(5,p.getPosition().getY());
    }

    @Test
    public void move_right_changes_position() {
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

}
