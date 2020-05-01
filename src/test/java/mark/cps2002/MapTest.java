package mark.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    Map m;

    @Before
    public void setup() {
        //Create a 10x5 Map
        m = new Map(10,5);
        m.generate();
    }

    @After
    public void teardown() {
        m = null;
    }


    //Test Map constructor

    @Test (expected = IllegalArgumentException.class)
    public void map_boardWidthNegative_throwsException(){
        //All map dimensions should be positive
        Map badMap = new Map(-10, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void map_boardHeightNegative_throwsException(){
        //All map dimensions should be positive
        Map badMap = new Map(10, -10);
    }


    //Test getTileType

    @Test
    public void getTileType_positionOutOfBounds_returnsNull() {
        //Map can only return values if the position is actually on the map
        Position badPosition = new Position(52, -1);
        assertEquals(null, m.getTileType(badPosition));
    }

    @Test
    public void getTileType_blankMap_returnsNull() {
        //If the map has not been generated yet, there are no tiles to return
        Map blankMap = new Map(10,10);
        Position pos = new Position(2,2);
        assertEquals(null, blankMap.getTileType(pos));
    }


    //Test Random start tile selection

    @Test
    public void selectRandomStartTile_get10tiles_allTilesWithinBounds() {
        //All start tiles should be within the bounds of the map

        Position startTile;

        //Pick a random start tile 10 times, all should be within the bounds of the map
        for (int i = 0; i < 10; i++) {
            startTile = m.selectRandomStartTile();

            //Check X value
            assertEquals(true, startTile.getX() >=0);
            assertEquals(true, startTile.getX() < m.getWidth());

            //Check Y value
            assertEquals(true, startTile.getY() >=0);
            assertEquals(true, startTile.getY() < m.getHeight());
        }
    }

    @Test
    public void selectRandomStartTile_get10tiles_allTilesAreGrass() {
        //All start tiles should be grass tiles

        Position startTile;

        //Pick a random start tile 10 times, all should be grass
        for (int i = 0; i < 10; i++) {
            startTile = m.selectRandomStartTile();
            assertEquals(TileType.GRASS, m.getTileType(startTile));
        }
    }

    @Test
    public void selectRandomStartTile_blankMap_returnsNull() {
        //If the map has not been generated yet, there are no tiles to return
        Map blankMap = new Map(10,10);
        assertEquals(null, blankMap.selectRandomStartTile());
    }


    //Test getters

    @Test
    public void getWidth_10x5Map_returns10() {
        //The map should have width = 10
        assertEquals(10,m.getWidth());
    }

    @Test
    public void getHeight_10x5Map_returns5() {
        //The map should have height = 5
        assertEquals(5,m.getHeight());
    }

    @Test
    public void isGenerated_blankMap_returnsFalse() {
        //A brand new map should not be generated
        Map blankMap = new Map(10,10);
        assertEquals(false, blankMap.isGenerated());
    }

    @Test
    public void isGenerated_generatedMap_returnsTrue() {
        //Once we call the generate method, the map should be marked as generated
        Map generatedMap = new Map(10,10);
        generatedMap.generate();
        assertEquals(true, generatedMap.isGenerated());
    }

}
