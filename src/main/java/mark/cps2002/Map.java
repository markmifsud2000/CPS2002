package mark.cps2002;

public class Map {

    TileType[][] grid;      //The actual grid of tiles on the map
    int width;              //The width of the map
    int height;             //The height of the map

    /**
     * Constructor for Map.
     * Constructs a map of the given dimensions.
     * @param boardWidth The width of the map.
     * @param boardHeight The height of the map.
     * @throws IllegalArgumentException if board dimensions are negative.
     */
    public Map(int boardWidth, int boardHeight) throws IllegalArgumentException{
        //Check if board values are valid
        if(boardHeight <= 0 || boardWidth <= 0) {
            throw new IllegalArgumentException("Board Sizes must all be greater than 0.");
        }

        this.grid = new TileType[boardWidth][boardHeight];
        this.width = boardWidth;
        this.height = boardHeight;
    }

    public void generate() {

    }

    /**
     * Returns the type of tile at a given position.
     * @param pos The position of the tile.
     * @return The type of the tile.
     */
    public TileType getTileType(Position pos) {
        //Ensure position is on the map
        if(pos.getX() < 0 || pos.getX() >= width ||
                pos.getY() < 0 || pos.getY() >= height) {
            return null;
        }
        //Position is in the map, return the requested tile
        else {
            return grid[pos.getX()][pos.getY()];
        }
    }

    public Position selectRandomStartTile() {
        return null;
    }

    /**
     * Returns the width of the map in number of tiles.
     * @return The width of the map.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the map in number of tiles.
     * @return The height of the map.
     */
    public int getHeight() {
        return this.height;
    }

}
