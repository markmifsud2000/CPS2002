/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * SimpleMapHazardCreatorTest.java
 * Last Release: v2.0.0 27/05/2020
 *
 * Unit Tests for the various functions in the SimpleMapHazardCreator class.
 */

package mark.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class SimpleMapHazardCreatorTest {

    SimpleMapHazardCreator mc;

    @Before
    public void setup() {
        mc = new SimpleMapHazardCreator();
    }

    @After
    public void teardown() {
        mc = null;
    }


    //Test Create Method

    @Test
    public void create_HazardMap_returnsHazardMap() {
        //Create a 10x10 Hazard Map
        Object[] args = {10, 10};
        Map m = mc.create("Hazard", args);

        //Check if the created map is a SimpleHazardMap
        assertEquals(SimpleMapHazard.class, m.getClass());
    }

    @Test
    public void create_HazardMap_mapIsGenerated() {
        //Create a 10x10 Safe Map
        Object[] args = {10, 10};
        Map m = mc.create("Hazard", args);

        //Check if the created map has been generated
        assertEquals(true, m.isGenerated());
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

    @Test (expected = Exception.class)
    public void create_HazardMapWithNoArgs_returnsException() {
        Object[] args = {};
        Map m = mc.create("Hazard", args);
    }

    @Test (expected = Exception.class)
    public void create_HazardMapNegativeArgs_returnsException() {
        Object[] args = {-10, -5};
        Map m = mc.create("Hazard", args);
    }

    @Test (expected = Exception.class)
    public void create_HazardMapArgsNotIntegers_returnsException() {
        Object[] args = {10.5, "hello"};
        Map m = mc.create("Hazard", args);
    }

}
