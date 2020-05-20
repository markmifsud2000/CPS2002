package mark.cps2002;

public class SimpleMapSafe extends SimpleMap{

    final double WATER_TILE_CHANCE = 0.1;

    public SimpleMapSafe(int boardWidth, int boardHeight){
        super(boardWidth, boardHeight);
    }

    public void generate(){
        super.generateSimpleMap(WATER_TILE_CHANCE);
    }

}
