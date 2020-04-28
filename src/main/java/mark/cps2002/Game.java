package mark.cps2002;

public class Game {

    final int MIN_PLAYERS = 2;              //The minimum number of players
    final int MAX_PLAYERS = 8;              //The maximum number of players
    final int SMALL_BOARD_PLAYER_LIMIT = 4; //The number of players for which the minimum board size changes
    final int MIN_BOARD_SIZE_SMALL = 5;     //The minimum board size for few players
    final int MIN_BOARD_SIZE_BIG = 8;       //The minimum board size for many players
    final int MAX_BOARD_SIZE = 50;          //The maximum board size for any number of players

    Player[] players;       //The players currently in the game
    Map map;                //The map being played on
    int turn;               //The current turn number
    boolean gameFinished;   //Whether or not the game has been finished yet

    /**
     * Constructor for Game.
     * Sets up a game to be played.
     * @param noOfPlayers The number of players in the game.
     * @param boardWidth The width of the map to be played on.
     * @param boardHeight The height of the board to be played on.
     * @throws IllegalArgumentException If any arguments are invalid or are not within limits.
     */
    public Game(int noOfPlayers, int boardWidth, int boardHeight) throws IllegalArgumentException{

        //Check if all arguments are valid
        //Check noOfPlayers
        if(noOfPlayers < MIN_PLAYERS || noOfPlayers > MAX_PLAYERS) {
            String message = "Invalid number of players. Must be between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ".";
            throw new IllegalArgumentException(message);
        }
        //Check if board sizes are too large
        else if(boardWidth > MAX_BOARD_SIZE) {
            String message = "Board is too large. Maximum size is " + MAX_BOARD_SIZE + "x" + MAX_BOARD_SIZE + ".";
            throw new IllegalArgumentException(message);
        }
        //Check if board sizes are too small
        else {
            //If there are many players
            if(noOfPlayers > SMALL_BOARD_PLAYER_LIMIT) {
                if(boardHeight < MIN_BOARD_SIZE_BIG || boardWidth < MIN_BOARD_SIZE_BIG) {
                    String message = "Board is too small. Minimum size for over " + SMALL_BOARD_PLAYER_LIMIT
                            + " players is " + MIN_BOARD_SIZE_BIG + "x" + MIN_BOARD_SIZE_BIG + ".";
                    throw new IllegalArgumentException(message);
                }
            }
            //If there are few players
            else if (boardHeight < MIN_BOARD_SIZE_SMALL || boardWidth < MIN_BOARD_SIZE_SMALL) {
                String message = "Board is too small. Minimum size is " + MIN_BOARD_SIZE_SMALL + "x"
                        + MIN_BOARD_SIZE_SMALL + ".";
                throw new IllegalArgumentException(message);
            }
        }

        //All arguments are correct, setup the game

        //Create Map to play on
        this.map = new Map(boardWidth, boardHeight);
        this.map.generate();

        //Create players
        this.players = new Player[noOfPlayers];
        for (int i = 0; i < noOfPlayers; i++) {
            Position start = this.map.selectRandomStartTile();
            this.players[i] = new Player(i, boardWidth, boardHeight, start);
        }

        //Initialise other values
        this.turn = 0;
        this.gameFinished = false;
    }

    public void startGame() {


    }

    /**
     * Checks the players input to determine the direction.
     * Also checks if input is valid.
     * @param s The user input string.
     * @param pos The current position of the player. Used to check bounds.
     * @return The direction input by the used. null if invalid.
     */
    public Direction checkPlayerInput (String s, Position pos) {
        return null;
    }

    /**
     * Update the players position/state.
     * @param player The player to be updated.
     * @param dir The direction the player is moving in.
     */
    public void updatePlayer(Player player, Direction dir) {

    }

    /**
     * End the game.
     * Update all players.
     */
    public void finishGame() {

    }

    /**
     * Generates the HTML file with the map for a given player.
     * @param player HTML generated for this player.
     */
    public void generateHTML(Player player) {

        /*
         *
         * TEMPORARY IMPLEMENTATION, PRINTS TO CONSOLE INSTEAD OF HTML
         *
         */

        System.out.println("Player " + (player.getId()+1) + ";");
        System.out.println("Turn " + turn + ";\n");

        //Print column numbers
        System.out.print("  ");
        for (int i = 0; i < map.getWidth(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        //Print Map
        for(int i = 0; i < map.getHeight(); i++) {
            //Print each row
            System.out.print(i + " ");
            for (int j = 0; j < map.getWidth(); j++) {
                //Print each tile in the row
                //Check if tile is revealed
                char type = '?';
                Position tile = new Position(j, i);

                if (player.isTileRevealed(tile)) {
                    //Check tile type
                    switch (map.getTileType(tile)) {
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
                }
                else {
                    //Tile still covered
                    type = '0';
                }

                System.out.print(type + " ");
            }
            System.out.println();
        }
        System.out.println();

        //Show Start Position
        System.out.println("Start : " + player.getStartPosition().toString());

        //Show current Position
        System.out.println("Position : " + player.getPosition().toString());

        //Print any notices
        String msg = "";
        switch (player.getNotice()) {
            case WATER:
                msg = msg.concat("SPLASH! You hit a water tile, back to the start;");
                break;

            case WIN:
                msg = msg.concat("YOU WIN!!!");
                break;

            case LOSE:
                msg = msg.concat("YOU LOSE :(");
                break;
        }
        System.out.println("Notice: " + msg);
        System.out.println("\n\n\n");
    }

    /**
     * Get the number of players in the game.
     * @return The number of players.
     */
    public int getNumberOfPlayers() {
        return players.length;
    }

    /**
     * Get the current turn number.
     * @return The current turn number.
     */
    public int getTurnNumber() {
        return turn;
    }

    /**
     * Check if the game has finished yet.
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

}
