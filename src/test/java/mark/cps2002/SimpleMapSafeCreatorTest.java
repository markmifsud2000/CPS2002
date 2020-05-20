package mark.cps2002;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SimpleMapSafeCreatorTest {

    SimpleMapSafeCreator mc;

    @Before
    public void setup() {
        mc = new SimpleMapSafeCreator();
    }

    @After
    public void teardown() {
        mc = null;
    }


    //Test Create Method

    @Test
    public void create_safeMap_returnsSafeMap() {
        //Create a 10x10 Safe Map
        Object[] args = {10, 10};
        Map m = mc.create("Safe", args);

        //Check if the created map is a SimpleSafeMap
        assertEquals(SimpleMapSafe.class, m.getClass());
    }

    @Test
    public void create_safeMap_mapIsGenerated() {
        //Create a 10x10 Safe Map
        Object[] args = {10, 10};
        Map m = mc.create("Safe", args);

        //Check if the created map has been generated
        assertEquals(true, m.isGenerated());
    }

    @Test
    public void create_10x5SafeMap_MapWidthIs10() {
        //Create a 10x5 map
        Object[] args = {10, 5};
        Map m = mc.create("Safe", args);

        //Check if the created map has width 10
        assertEquals(10, m.getWidth());
    }

    @Test
    public void create_10x5SafeMap_MapHeightIs5() {
        //Create a 10x5 map
        Object[] args = {10, 5};
        Map m = mc.create("Safe", args);

        //Check if the created map has height 5
        assertEquals(5, m.getHeight());
    }

    @Test (expected = Exception.class)
    public void create_SafeMapWithNoArgs_returnsException() {
        Object[] args = {};
        Map m = mc.create("Safe", args);
    }

    @Test (expected = Exception.class)
    public void create_SafeMapNegativeArgs_returnsException() {
        Object[] args = {-10, -5};
        Map m = mc.create("Safe", args);
    }

    @Test (expected = Exception.class)
    public void create_SafeMapArgsNotIntegers_returnsException() {
        Object[] args = {10.5, "hello"};
        Map m = mc.create("Safe", args);
    }

}
