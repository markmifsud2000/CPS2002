package mark.cps2002;

public interface Map {

    void generate();

    TileType getTileType(Position pos);

    Position selectRandomStartTile();

    int getWidth();

    int getHeight();

    boolean isGenerated();

}
