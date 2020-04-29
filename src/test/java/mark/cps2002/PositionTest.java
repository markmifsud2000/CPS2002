package mark.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {

    Position p;

    @Before
    public void setup() {
        p = new Position(3, 128);
    }

    @After
    public void teardown() {
        p = null;
    }

    @Test
    public void getX_CheckXValue() {
        assertEquals(3, p.getX());
    }

    @Test
    public void getY_CheckYValue() {
        assertEquals(128, p.getY());
    }

    @Test
    public void equals_equalPosition_returnsTrue() {
        Position otherPos = new Position(3, 128);
        assertEquals(true, p.equals(otherPos));
    }

    @Test
    public void equals_differentPosition_returnsFalse() {
        Position otherPos = new Position(3, 64);
        assertEquals(false, p.equals(otherPos));
    }

    @Test
    public void toString_returnedStringContainsPosition() {
        assertEquals("(3,128)", p.toString());
    }

}
