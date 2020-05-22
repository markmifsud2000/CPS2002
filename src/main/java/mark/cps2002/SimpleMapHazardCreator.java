/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * SimpleMapHazardCreator.java
 * Last Released: N/A
 *
 * Used to create simple hazard maps for the Game.
 */

package mark.cps2002;

public class SimpleMapHazardCreator extends MapCreator {

    /**
     * Create a hazard map.
     * @param type Type created will always be hazard.
     * @param args An array of two integers {Length, Width}.
     * @return A Hazard Simple Map with the given Length and Width.
     */
    public Map create(String type, Object[] args) {

        //Get the length and width of the map
        int width = (int) args[0];
        int height = (int) args[1];

        //Create a simple hazard map
        SimpleMapHazard m = SimpleMapHazard.getInstance();
        m.generate(width, height);

        return m;

    }

}
