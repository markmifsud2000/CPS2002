package mark.cps2002;

public class Player {

    int playerId;                   //Unique Identifier for the Player
    Position currentPosition;       //The Player's current Position on the board
    Position startTile;             //The tile that the player starts the game on


    /**
     * Constructs a player on the board.
     * @param playerID Unique identifier for the player.
     * @param boardWidth The width of the board being used.
     * @param boardHeight The Height of the board being used.
     * @param startPosition The position of the player at the start of the game.
     */
    public Player(int playerID, int boardWidth, int boardHeight, Position startPosition) {
        this.playerId = playerID;

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

        switch (direction) {
            case UP:
                this.currentPosition = new Position(currentPosition.getX(),currentPosition.getY()+1);
                break;

            case DOWN:
                this.currentPosition = new Position(currentPosition.getX(),currentPosition.getY()-1);
                break;

            case LEFT:
                this.currentPosition = new Position(currentPosition.getX()-1,currentPosition.getY());
                break;

            case RIGHT:
                this.currentPosition = new Position(currentPosition.getX()+1,currentPosition.getY());
                break;

            default:
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
