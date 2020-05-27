/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * MapCreator.java
 * Last Release: v2.0.0 27/05/2020
 *
 * Used to create all different kinds Maps used in the game.
 * Will Call the appropriate creator class for a specific map type.
 */

package mark.cps2002;

public class MapCreator {

    /**
     * Create a Map of a given type.
     * @param type The type of map to be created.
     * @param args Any arguments to be used when constructing the map.
     * @return The Created Map. Null if the map type does not match.
     */
    public Map create(String type, Object[] args) {

        //Select the appropriate map creator class
        MapCreator typeCreator = selectCreator(type);

        // Check if a map creator was found
        if (typeCreator == null) {
            return null;
        }
        else {
            //Create the appropriate map
            return typeCreator.create(type, args);
        }
    }

    /**
     * Select the appropriate MapCreator Class.
     * @param type The type of map to be created.
     * @return The appropriate map creator.
     */
    private MapCreator selectCreator(String type) {

        MapCreator mc;

        //Check the type required
        if (type.equalsIgnoreCase("Safe")) {
            //Return a creator for a safe map
            mc = new SimpleMapSafeCreator();
        }
        else if (type.equalsIgnoreCase("Hazard")) {
            //Return a creator for a hazard map
            mc = new SimpleMapHazardCreator();
        }
        else {
            //No creator can be found
            mc = null;
        }


        return mc;
    }

}
