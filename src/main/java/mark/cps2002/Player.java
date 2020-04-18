package mark.cps2002;

public class Player {

    private int playerId;                   //Unique Identifier for the Player
    private Position currentPosition;       //The Player's current Position on the board
    private Position startTile;             //The tile that the player starts the game on
    private boolean[][] revealedMap;        //Tile is set to true if the player has revealed that tile


    /**
     * Constructs a player on the board.
     * @param playerID Unique identifier for the player.
     * @param boardWidth The width of the board being used.
     * @param boardHeight The Height of the board being used.
     * @param startPosition The position of the player at the start of the game.
     */
    public Player(int playerID, int boardWidth, int boardHeight, Position startPosition) {
        this.playerId = playerID;

        this.revealedMap = new boolean[boardWidth][boardHeight];
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                //Mark all tiles as NOT revealed
                this.revealedMap[i][j] = false;
            }
        }

        this.startTile = startPosition;
        this.currentPosition = startPosition;
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

        return success;
    }

    public void reset() {

    }

    public void revealTile(Position p) {

    }

    public void revealAllTiles() {

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

    public boolean isTileRevealed() {
        return false;
    }

    public PlayerNotice getNotice() {
        return null;
    }

    public void setNotice(PlayerNotice notice) {

    }

    public boolean hasWon() {
        return false;
    }

}
