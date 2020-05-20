package mark.cps2002;

public class MapCreator {

    public Map create(String type, Object[] args) {
        MapCreator typeCreator = selectCreator(type);

        if (typeCreator == null) {
            return null;
        }
        else {
            return typeCreator.create(type, args);
        }
    }

    private MapCreator selectCreator(String type) {

        MapCreator mc;

        if (type.equalsIgnoreCase("Safe")) {
            mc = new SimpleMapSafeCreator();
        }
        else if (type.equalsIgnoreCase("Hazard")) {
            mc = new SimpleMapHazardCreator();
        }
        else {
            mc = null;
        }


        return mc;
    }

}
