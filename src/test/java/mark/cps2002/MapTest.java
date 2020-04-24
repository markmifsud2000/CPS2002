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
    public void getWidth_10x5Map_returns10() {
        assertEquals(10,m.getWidth());
    }

    @Test
    public void getHeight_10x5Map_returns5() {
        assertEquals(5,m.getHeight());
    }

}
