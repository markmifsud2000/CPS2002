package mark.cps2002;

public class Map {

    private TileType[][] grid;      //The actual grid of tiles on the map
    private int width;              //The width of the map
    private int height;             //The height of the map
    private boolean isGenerated;    //True if the map has been generated, false otherwise

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

        this.isGenerated = false;
    }

    /**
     * Generate the map.
     * Populates the map grid with grass, water and 1 treasure tile.
     */
    public void generate() {

        //Iterate through each tile on the grid.
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //Randomly decide what type of tile will be generated
                //Tiles have a 20% chance of being water tiles, otherwise they are grass
                double r = Math.random();
                if (r < 0.2) { //Water Tile
                    grid[i][j] = TileType.WATER;
                }
                else { //Grass Tile
                    grid[i][j] = TileType.GRASS;
                }
            }
        }


        //Once the map has been generated, we can randomly select a position for the treasure tile
        int treasureX = (int) Math.floor(Math.random()*width);
        int treasureY = (int) Math.floor(Math.random()*height);

        grid[treasureX][treasureY] = TileType.TREASURE;

        //Mark the map as generated
        isGenerated = true;
    }

    /**
     * Returns the type of tile at a given position.
     * @param pos The position of the tile.
     * @return The type of the tile.
     */
    public TileType getTileType(Position pos) {
        //Ensure that the map has been generated and the position is on the map
        if(!isGenerated || pos.getX() < 0 || pos.getX() >= width ||
                pos.getY() < 0 || pos.getY() >= height) {
            return null;
        }
        //Position is in the map, return the requested tile
        else {
            return grid[pos.getX()][pos.getY()];
        }
    }

    /**
     * Randomly picks a grass tile to that is possible to use as a starting tile.
     * @return The start tile selected.
     */
    public Position selectRandomStartTile() {

        //Check if the map has been generated.
        if(!isGenerated) {
            return null;
        }

        boolean isNotGrass = true;    //Flag, true if the selected tile is invalid
        Position startTile;           //The start tile to be returned

        //Loop until a valid tile is selected
        do{
            //Randomly select a tile on the board
            int randX = (int) Math.floor(Math.random()*width);
            int randY = (int) Math.floor(Math.random()*height);
            startTile = new Position(randX, randY);

            //Check if the selected tile is grass
            //If not grass, select a different tile
            isNotGrass = (getTileType(startTile) != TileType.GRASS);

        } while (isNotGrass);

        return startTile;
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

    /**
     * Check if the map has been generated yet.
     * @return True if the map has been generated, false otherwise.
     */
    public boolean isGenerated() {
        return isGenerated;
    }

}
