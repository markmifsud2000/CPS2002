package mark.cps2002;

public class SimpleMapHazard extends SimpleMap{

        final double WATER_TILE_CHANCE = 0.3;

        public SimpleMapHazard(int boardWidth, int boardHeight){
            super(boardWidth, boardHeight);
        }

        public void generate(){
            super.generateSimpleMap(WATER_TILE_CHANCE);
        }


}
