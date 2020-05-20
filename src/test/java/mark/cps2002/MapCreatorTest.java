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


}
