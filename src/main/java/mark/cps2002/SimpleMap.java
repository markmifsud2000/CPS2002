/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * SimpleMap.java
 * Last Release: N/A
 *
 * Represents the map that the players play on.
 * A simple map takes a rectangular shape.
 * The map is stored as a grid, with each tile having a specified type.
 * The map is randomly generated.
 */


package mark.cps2002;

public abstract class SimpleMap implements Map{

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
    public SimpleMap(int boardWidth, int boardHeight) throws IllegalArgumentException{

        //Check if board values are valid, ie. are all positive
        if(boardHeight <= 0 || boardWidth <= 0) {
            throw new IllegalArgumentException("Board Sizes must all be greater than 0.");
        }

        //Create a grid of the given dimensions
        this.grid = new TileType[boardWidth][boardHeight];
        this.width = boardWidth;
        this.height = boardHeight;

        //The map has not been generated yet
        this.isGenerated = false;
    }

    /**
     * Generate the map.
     * Populates the map grid with grass, water and 1 treasure tile.
     * To be implemented according to subclass requirements.
     */
    public abstract void generate();

    /**
     * Generate a simple map with a given percentage of water tiles.
     * @param waterTileChance The percentage chance of a tile being a water tile.
     * @throws IllegalArgumentException If waterTileChance over 100% or less than 0%.
     */
    protected void generateSimpleMap(double waterTileChance) throws IllegalArgumentException{

        //Check if waterTileChance is Valid
        if (waterTileChance < 0 || waterTileChance >= 1) {
            throw new IllegalArgumentException("waterTileChance must be in the range 0.0 < chance <= 1.0");
        }


        //Iterate through each tile on the grid.
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                //Randomly decide what type of tile will be generated
                //Tiles have a 20% chance of being water tiles, otherwise they are grass
                double r = Math.random();
                if (r < waterTileChance) { //Water Tile
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

        //Position is in the map, return the requested tile's type
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
            return null;    //Map not generated, nothing to return
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


    //Print Map function can be used during testing to output the map layout to the console

    /*
    public void printMap() {
        /*
         *
         * Temporary, to be used during testing
         * Prints a revealed version of the map to the console.
         *
         */
    /*

        //Print column numbers
        System.out.print("  ");
        for (int i = 0; i < getWidth(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        //Print Map
        for(int i = 0; i < getHeight(); i++) {
            //Print each row
            System.out.print(i + " ");
            for (int j = 0; j < getWidth(); j++) {
                //Print each tile in the row
                //Check if tile is revealed
                char type = '?';
                Position tile = new Position(j, i);

                //Check tile type
                switch (getTileType(tile)) {
                    case GRASS:
                        type = 'G';
                        break;

                    case WATER:
                        type = 'W';
                        break;

                    case TREASURE:
                        type = 'T';
                        break;
                }

                System.out.print(type + " ");
            }
            System.out.println();
        }
        System.out.println();

    }
    */

}
