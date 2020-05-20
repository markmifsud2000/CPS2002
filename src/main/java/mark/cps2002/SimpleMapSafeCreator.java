package mark.cps2002;

public class SimpleMapSafeCreator extends MapCreator{

    public Map create(String type, Object[] args) {

        int width = (int) args[0];
        int height = (int) args[1];

        SimpleMapSafe m = new SimpleMapSafe(width, height);
        m.generate();

        return m;
    }

}
