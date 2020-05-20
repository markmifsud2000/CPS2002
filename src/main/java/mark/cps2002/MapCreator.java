package mark.cps2002;

public class MapCreator {

    public Map create(String type, Object[] args) {
        MapCreator typeCreator = selectCreator(type);
        return typeCreator.create(type, args);
    }

    private MapCreator selectCreator(String type) {
        return null;
    }

}
