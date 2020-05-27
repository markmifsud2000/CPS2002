/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * SimpleMapSafeCreator.java
 * Last Release: v2.0.0 27/05/2020
 *
 * Used to create simple safe maps for the Game.
 */

package mark.cps2002;

public class SimpleMapSafeCreator extends MapCreator{

    /**
     * Create a safe map.
     * @param type Type created will always be safe.
     * @param args An array of two integers {Length, Width}.
     * @return A Safe Simple Map with the given Length and Width.
     */
    public Map create(String type, Object[] args) {

        //Get the Length and width of the map
        int width = (int) args[0];
        int height = (int) args[1];

        //Create the simple safe map
        SimpleMapSafe m = SimpleMapSafe.getInstance();
        m.generate(width, height);

        return m;
    }

}
