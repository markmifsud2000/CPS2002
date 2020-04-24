package mark.cps2002;

public class Launcher {

    public static void main(String[] args) {

        Map m = new Map(10, 10);
        m.generate();


        for (int i = 0; i < m.getWidth(); i++) {
            for (int j = 0; j < m.getHeight(); j++) {
                Position pos = new Position(i, j);
                String s;

                switch (m.getTileType(pos)) {
                    case GRASS:
                        s = "G";
                        break;
                    case WATER:
                        s = "W";
                        break;
                    case TREASURE:
                        s = "T";
                        break;
                    default:
                        s = "?";
                        break;
                }

                System.out.print(s + " ");
            }
            System.out.println();
        }

    }

}
