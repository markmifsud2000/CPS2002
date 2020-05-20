package mark.cps2002;

public class SimpleMapHazardCreator extends MapCreator {

    public Map create(String type, Object[] args) {

        int width = (int) args[0];
        int height = (int) args[1];

        SimpleMapHazard m = new SimpleMapHazard(width, height);
        m.generate();

        return m;

    }

}
