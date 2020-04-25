package mark.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapTest {

    Map m;

    @Before
    public void setup() {
        m = new Map(10,5);
        m.generate();
    }

    @After
    public void teardown() {
        m = null;
    }

    @Test (expected = IllegalArgumentException.class)
    public void map_boardWidthNegative_throwsException(){
        Map badMap = new Map(-10, 10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void map_boardHeightNegative_throwsException(){
        Map badMap = new Map(10, -10);
    }

    @Test
    public void getTileType_positionOutOfBounds_returnsNull() {
        Position badPosition = new Position(52, -1);
        assertEquals(null, m.getTileType(badPosition));
    }

    @Test
    public void getTileType_blankMap_returnsNull() {
        Map blankMap = new Map(10,10);
        Position pos = new Position(2,2);
        assertEquals(null, blankMap.getTileType(pos));
    }

    @Test
    public void selectRandomStartTile_get10tiles_allTilesWithinBounds() {
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
        Position startTile;

        //Pick a random start tile 10 times, all should be grass
        for (int i = 0; i < 10; i++) {
            startTile = m.selectRandomStartTile();
            assertEquals(TileType.GRASS, m.getTileType(startTile));
        }
    }

    @Test
    public void selectRandomStartTile_blankMap_returnsNull() {
        Map blankMap = new Map(10,10);
        assertEquals(null, blankMap.selectRandomStartTile());
    }

    @Test
    public void getWidth_10x5Map_returns10() {
        assertEquals(10,m.getWidth());
    }

    @Test
    public void getHeight_10x5Map_returns5() {
        assertEquals(5,m.getHeight());
    }

    @Test
    public void isGenerated_blankMap_returnsFalse() {
        Map blankMap = new Map(10,10);
        assertEquals(false, blankMap.isGenerated());
    }

    @Test
    public void isGenerated_generatedMap_returnsTrue() {
        Map generatedMap = new Map(10,10);
        generatedMap.generate();
        assertEquals(true, generatedMap.isGenerated());
    }

}
