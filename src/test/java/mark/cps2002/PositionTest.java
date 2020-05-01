/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * PositionTest.java
 * Last Modified: v1.0.0, 01/05/2020
 *
 * Unit Tests for the various functions in the Position class.
 */

package mark.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {

    Position p;

    @Before
    public void setup() {
        //Create a position (3,128)
        p = new Position(3, 128);
    }

    @After
    public void teardown() {
        p = null;
    }

    @Test
    public void getX_CheckXValue() {
        //Position has x value 3
        assertEquals(3, p.getX());
    }

    @Test
    public void getY_CheckYValue() {
        //Position has y value 128
        assertEquals(128, p.getY());
    }

    @Test
    public void equals_equalPosition_returnsTrue() {
        //Position should be equal to (3, 128)
        Position otherPos = new Position(3, 128);
        assertEquals(true, p.equals(otherPos));
    }

    @Test
    public void equals_differentPosition_returnsFalse() {
        //Position is (3,128), which is not equal to (3,64)
        Position otherPos = new Position(3, 64);
        assertEquals(false, p.equals(otherPos));
    }

    @Test
    public void toString_returnedStringContainsPosition() {
        //Position (3,128) should return a string "(3,128)"
        assertEquals("(3,128)", p.toString());
    }

}
