package mark.cps2002;

public class Player {

    private int playerId;                   //Unique Identifier for the Player
    private Position currentPosition;       //The Player's current Position on the board
    private Position startTile;             //The tile that the player starts the game on
    private boolean[][] revealedMap;        //Tile is set to true if the player has revealed that tile
    private PlayerNotice notice;            //Any notices that need to be passed to the player


    /**
     * Constructs a player on the board.
     * @param playerID Unique identifier for the player.
     * @param boardWidth The width of the board being used.
     * @param boardHeight The Height of the board being used.
     * @param startPosition The position of the player at the start of the game.
     * @throws IllegalArgumentException If board parameters are negative or if starting position is not on the board.
     */
    public Player(int playerID, int boardWidth, int boardHeight, Position startPosition) throws IllegalArgumentException{
        this.playerId = playerID;

        //Check if board values are valid
        if(boardHeight <= 0 || boardWidth <= 0) {
            throw new IllegalArgumentException("Board Sizes must all be greater than 0.");
        }

        //Board values are good, create map
        this.revealedMap = new boolean[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                //Mark all tiles as NOT revealed
                this.revealedMap[i][j] = false;
            }
        }

        //Check if start position is actually on the map
        if (startPosition.getX() < 0 || startPosition.getX() >= boardWidth ||
                startPosition.getY() < 0 || startPosition.getY() >= boardHeight) {
            throw new IllegalArgumentException("Starting position must be a tile within the board.");
        }

        //Start position is good, place the player at the start position
        this.startTile = startPosition;
        this.currentPosition = startPosition;

        //Reveal start tile
        revealTile(startPosition);

        this.notice = PlayerNotice.NONE;
    }

    /**
     * The player moves one tile in the given direction.
     * @param direction The direction to move.
     * @return True if moved successfully, false otherwise.
     */
    public boolean move(Direction direction) {

        boolean success = true;

        //Move player in the given direction
        switch (direction) {
            case UP:
                //Check if the player is in the top row
                if (currentPosition.getY() > 0) {
                    //There is space to move, update position
                    currentPosition = new Position(currentPosition.getX(), currentPosition.getY() - 1);
                }
                else {
                    //Player is in the top row and cannot move further up
                    success = false;
                }
                break;

            case DOWN:
                //Check if the player is in the bottom row
                if (currentPosition.getY() < revealedMap.length-1) {
                    //There is space to move, update position
                    currentPosition = new Position(currentPosition.getX(), currentPosition.getY() + 1);
                }
                else {
                    //Player is in the bottom row and cannot move further down
                    success = false;
                }
                break;

            case LEFT:
                //Check if the player is in the first column
                if (currentPosition.getX() > 0) {
                    //There is space to move, update position
                    currentPosition = new Position(currentPosition.getX() - 1, currentPosition.getY());
                }
                else {
                    //Player is in the first column and cannot move further left
                    success = false;
                }
                break;

            case RIGHT:
                //Check if the player is in the last column
                if (currentPosition.getX() < revealedMap[0].length-1) {
                    //There is space to move, update position
                    currentPosition = new Position(currentPosition.getX() + 1, currentPosition.getY());
                }
                else {
                    //Player is in the first column and cannot move further right
                    success = false;
                }
                break;

            default:
                //Invalid Direction
                success = false;

        }

        revealTile(currentPosition);

        return success;
    }

    /**
     * Resets the player to the start of the game.
     * Player returns to starting position and Map becomes covered again.
     */
    public void reset() {
        //Reset Player Position
        currentPosition = startTile;

        //Cover board again
        for(int i = 0; i < revealedMap.length; i++) {
            for (int j = 0; j < revealedMap[0].length; j++) {
                revealedMap[i][j] = false;
            }
        }

        //Reveal start tile
        revealTile(startTile);
    }

    /**
     * Marks a given tile as revealed to the player.
     * @param p The position of the tile to be revealed.
     * @return True if the tile is successfully revealed, false otherwise.
     */
    public boolean revealTile(Position p) {
        //Check if position is part of the board
        if (p.getX() >= 0 && p.getX() < revealedMap.length) { // Check x value
            if (p.getY() >= 0 && p.getY() < revealedMap[0].length) { // Check y value
                //Position is valid, mark it as revealed
                revealedMap[p.getX()][p.getY()] = true;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Mark the entire map as revealed.
     */
    public void revealAllTiles() {
        for(int i = 0; i < revealedMap.length; i++) {
            for (int j = 0; j < revealedMap[0].length; j++) {
                revealedMap[i][j] = true;
            }
        }
    }

    /**
     * Get the player's unique Identifier.
     * @return Integer value of the player's ID.
     */
    public int getId() {
        return this.playerId;
    }

    /**
     * Get the player's current position.
     * @return The player's current position.
     */
    public Position getPosition() {
        return this.currentPosition;
    }

    /**
     * Get the player's start position.
     * @return The player's start position.
     */
    public Position getStartPosition() {
        return this.startTile;
    }

    /**
     * Check if a given tile has been revealed by the player.
     * @param p The position of the tile.
     * @return True if the tile has been revealed, false otherwise.
     */
    public boolean isTileRevealed(Position p) {
        return revealedMap[p.getX()][p.getY()];
    }

    /**
     * Returns any notices that have been given to the player.
     * @return The notice.
     */
    public PlayerNotice getNotice() {
        return this.notice;
    }

    /**
     * Sets the players notice.
     * @param notice The notice to be set.
     */
    public void setNotice(PlayerNotice notice) {
        this.notice = notice;
    }

    /**
     * Checks if the player has won the game.
     * @return True if the player has won, false otherwise.
     */
    public boolean hasWon() {
        return (getNotice() == PlayerNotice.WIN);
    }

}
