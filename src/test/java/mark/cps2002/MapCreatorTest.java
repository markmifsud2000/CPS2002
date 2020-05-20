package mark.cps2002;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapCreatorTest {

    MapCreator mc;

    @Before
    public void setup() {
        mc = new MapCreator();
    }

    @After
    public void teardown() {
        mc = null;
    }


    //Test Create Method

    @Test
    public void create_safe_returnsSafeMap() {
        //Create a 10x10 Safe Map
        Object[] args = {10, 10};
        Map m = mc.create("Safe", args);

        //Check if the created map is a SimpleSafeMap
        assertEquals(SimpleMapSafe.class, m.getClass());
    }


    @Test
    public void create_hazard_returnsHazardMap() {
        //Create a 10x10 Hazard Map
        Object[] args = {10, 10};
        Map m = mc.create("Hazard", args);

        //Check if the created map is a SimpleHazardMap
        assertEquals(SimpleMapHazard.class, m.getClass());
    }

    @Test
    public void create_NoType_returnsNull() {
        //Create a map without setting the type
        Object[] args = {10, 10};
        Map m = mc.create("", args);

        //Check if the created map is null
        assertEquals(null, m);
    }

    @Test (expected = Exception.class)
    public void create_SafeMapWithNoArgs_returnsException() {
        Object[] args = {};
        Map m = mc.create("Safe", args);
    }

    @Test (expected = Exception.class)
    public void create_HazardMapWithNoArgs_returnsException() {
        Object[] args = {};
        Map m = mc.create("Hazard", args);
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

    @Test
    public void create_10x5HazardMap_MapWidthIs10() {
        //Create a 10x5 map
        Object[] args = {10, 5};
        Map m = mc.create("Hazard", args);

        //Check if the created map has width 10
        assertEquals(10, m.getWidth());
    }

    @Test
    public void create_10x5HazardMap_MapHeightIs5() {
        //Create a 10x5 map
        Object[] args = {10, 5};
        Map m = mc.create("Hazard", args);

        //Check if the created map has height 5
        assertEquals(5, m.getHeight());
    }

}
