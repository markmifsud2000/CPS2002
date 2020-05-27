/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * Game.java
 * Last Released: v1.0.1, 01/05/2020
 *
 * Game is responsible for coordinating the movement of all players over the map.
 * The main gameplay loop takes place here.
 * It also handles all input/output to the user through the console, as well as creation of output HTML files.
 */


package mark.cps2002;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Game {


    private final int MIN_PLAYERS = 2;              //The minimum number of players
    private final int MAX_PLAYERS = 8;              //The maximum number of players
    private final int SMALL_BOARD_PLAYER_LIMIT = 4; //The number of players for which the minimum board size changes
    private final int MIN_BOARD_SIZE_SMALL = 5;     //The minimum board size for few players
    private final int MIN_BOARD_SIZE_BIG = 8;       //The minimum board size for many players
    private final int MAX_BOARD_SIZE = 50;          //The maximum board size for any number of players


    private final String HTML_OUTPUT_DIR = "playerHtmlOutput";              //Path to output directory, directory must exist
    private final String CSS_STYLE_PATH = "../htmlResources/style.css";     //Path to html style (relative to output dir)
    private final String HTML_LEGEND_PATH = "htmlResources/mapLegend.html"; //Path to html map legend (relative to working dir)


    private Player[] players;       //The players currently in the game
    private MapCreator mc;          //The creator used to generate all required maps
    private Map map;                //The map being played on
    private int turn;               //The current turn number
    private boolean gameFinished;   //Whether or not the game has been finished yet
    private Team[] teams;           //The teams in the game (if any)


    /**
     * Constructor for Game.
     * Sets up a game to be played.
     * @param noOfPlayers The number of players in the game.
     * @param boardWidth The width of the map to be played on.
     * @param boardHeight The height of the board to be played on.
     * @param teams The number of teams to be used. 0 for no teams.
     * @throws IllegalArgumentException If any arguments are invalid or are not within limits.
     */
    public Game(int noOfPlayers, int boardWidth, int boardHeight, String mapType, int teams) throws IllegalArgumentException{

        //Check if all arguments are valid, throw exception if they are not

        //Check noOfPlayers
        if(noOfPlayers < MIN_PLAYERS || noOfPlayers > MAX_PLAYERS) {
            String message = "Invalid number of players. Must be between " + MIN_PLAYERS + " and " + MAX_PLAYERS + ".";
            throw new IllegalArgumentException(message);
        }

        //Check if board sizes are too large
        else if(boardWidth > MAX_BOARD_SIZE || boardHeight > MAX_BOARD_SIZE) {
            String message = "Board is too large. Maximum size is " + MAX_BOARD_SIZE + "x" + MAX_BOARD_SIZE + ".";
            throw new IllegalArgumentException(message);
        }

        //Check if the number of teams is negative
        else if(teams < 0 || teams > noOfPlayers) {
            String message = "Number of teams must be between 0 and the number of players";
            throw new IllegalArgumentException(message);
        }

        //Check if board sizes are too small
        else {
            //If there are many players, use the larger minimum board size
            if(noOfPlayers > SMALL_BOARD_PLAYER_LIMIT) {
                if(boardHeight < MIN_BOARD_SIZE_BIG || boardWidth < MIN_BOARD_SIZE_BIG) {
                    String message = "Board is too small. Minimum size for over " + SMALL_BOARD_PLAYER_LIMIT
                            + " players is " + MIN_BOARD_SIZE_BIG + "x" + MIN_BOARD_SIZE_BIG + ".";
                    throw new IllegalArgumentException(message);
                }
            }

            //If there are few players, use the smaller minimum board size
            else if (boardHeight < MIN_BOARD_SIZE_SMALL || boardWidth < MIN_BOARD_SIZE_SMALL) {
                String message = "Board is too small. Minimum size is " + MIN_BOARD_SIZE_SMALL + "x"
                        + MIN_BOARD_SIZE_SMALL + ".";
                throw new IllegalArgumentException(message);
            }
        }


        //All arguments are correct, setup the game

        //Setup Map Creator to be used later
        this.mc = new MapCreator();
        Object[] mapArgs = new Object[]{boardWidth, boardHeight};
        this.map = mc.create(mapType, mapArgs);

        //Check if map was created successfully
        if (this.map == null) {
            //Map type was not found, throw an exception
            throw new IllegalArgumentException("Map type must be either \"Safe\" or \"Hazard\"");
        }

        //Create players
        this.players = new Player[noOfPlayers];
        for (int i = 0; i < noOfPlayers; i++) {
            //For each player, select a random start tile from the map
            Position start = this.map.selectRandomStartTile();
            this.players[i] = new Player(i, boardWidth, boardHeight, start);
        }

        //Check if players are in teams
        if (teams > 0) {
            //Setup all the teams
            this.teams = new Team[teams];
            for (int i = 0; i < teams; i++) {
                this.teams[i] = new Team(i, boardWidth, boardHeight);
            }

            assignTeams();
        }

        //Initialise other values
        this.turn = 0;
        this.gameFinished = false;


        //Create the output directory if it doesn't exist
        File f = new File(HTML_OUTPUT_DIR);
        f.mkdir();

        //Clear the output directory of any old files
        clearOutputDirectory();
    }


    /**
     * Assign all players randomly to the teams
     */
    private void assignTeams() {

        //Create an array with the index of each player
        int[] playerIndexes = new int[players.length];
        for (int i = 0; i < playerIndexes.length; i++) {
            playerIndexes[i] = i;
        }

        //The number of players that still need to be assigned to a team
        int remaining = playerIndexes.length;

        //The team being assigned to
        int teamIndex = 0;

        //Assign each player to a team
        while (remaining > 0) {

            //Randomly select a player to assign to the current team
            int rand = (int) Math.floor(Math.random()*remaining);
            teams[teamIndex].addPlayer(players[playerIndexes[rand]]);

            //Remove the player's index and move the last index to close the gap
            remaining--;
            playerIndexes[rand] = playerIndexes[remaining];

            //Select next team
            teamIndex = (teamIndex + 1) % teams.length;
        }

    }


    /**
     * The main game loop.
     * Calling this method begins the game.
     */
    public void startGame() {

        //Check if this is a team game
        if (teams != null) {
            startTeamGame();
            return;
        }


        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        //Loop until the game is over
        do {

            //Start the new turn
            turn++;

            //Generate the HTML map for each player
            for (Player p: players) {
                generateHTML(p);
                p.setNotice(PlayerNotice.NONE); //Player's have been informed, clear any notice
            }


            //Store the movement direction selected by each player
            Direction[] playerMoves = new Direction[players.length];

            //Each player takes their turn
            for (int i = 0; i < players.length; i++) {

                //Loop turn for the same player until the input is valid
                do {
                    //Ask the player to input a direction
                    System.out.println("Player " + (players[i].getId()+1));
                    System.out.println("Please enter a direction: (U)p, (D)own, (L)eft, (R)ight");
                    String input = sc.nextLine();

                    //Convert input string to direction
                    playerMoves[i] = checkPlayerInput(input, players[i].getPosition());

                    //If it was invalid, inform the user
                    if (playerMoves[i] == null) {
                        System.out.println("Please enter a valid direction. Keep inside the bounds of the map.\n");
                    }

                } while (playerMoves[i] == null); //While input not valid

                System.out.println("\n");
            }


            //Each player has made their move, update them
            for (int i = 0; i < players.length; i++) {
                updatePlayer(players[i], playerMoves[i]);
            }


            //Check if any of of the players have won
            if (isGameFinished()) {
                finishGame();
            }


        } while (!isGameFinished());    //If the game is not finished, go to start

    }


    /**
     * The main game loop executed using teams.
     * Calling this method begins the game.
     * Turns are carried out in the order of teams.
     */
    public void startTeamGame() {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        //Loop until the game is over
        do {

            //Start the new turn
            turn++;

            //Store the movement direction selected by each player
            Direction[] playerMoves = new Direction[players.length];

            //Generate HTML
            for (Team t : teams) {
                t.updateTeamMaps();

                //generate HTML for each player
                t.first();
                while (t.hasNext()) {
                    Player p = t.next();
                    generateTeamHTML(t, p);
                    p.setNotice(PlayerNotice.NONE); //Players have been informed, clear any notice
                }

            }

            //Take input for each team
            for (Team t: teams) {

                System.out.println("Team " + (t.getId()+1) + ":\n");

                t.first();
                while(t.hasNext()) {

                    Player p = t.next();    //Get the next player in the team
                    int i = p.getId();      //Index of player in the player list

                    //Loop turn for the same player until the input is valid
                    do {
                        //Ask the player to input a direction
                        System.out.println("Player " + (p.getId()+1));
                        System.out.println("Please enter a direction: (U)p, (D)own, (L)eft, (R)ight");
                        String input = sc.nextLine();

                        //Convert input string to direction
                        playerMoves[i] = checkPlayerInput(input, p.getPosition());

                        //If it was invalid, inform the user
                        if (playerMoves[i] == null) {
                            System.out.println("Please enter a valid direction. Keep inside the bounds of the map.\n");
                        }

                    } while (playerMoves[i] == null); //While input not valid

                    System.out.println();

                }

                System.out.println("\n\n");

            }

            //Each teams have entered their moves, update each player
            for (Team t : teams) {
                //Update each player in the team
                t.first();
                while (t.hasNext()) {
                    Player p = t.next();
                    updatePlayer(p, playerMoves[p.getId()]);
                }
            }

            //Check if any of of the teams have won
            if (isGameFinished()) {
                finishTeamGame();
            }

        } while (!isGameFinished());    //If the game is not finished, go to start

    }


    /**
     * Checks the players input to determine the direction.
     * Also checks if input is valid.
     * @param s The user input string.
     * @param pos The current position of the player. Used to check bounds.
     * @return The direction input by the used. null if invalid.
     */
    public Direction checkPlayerInput (String s, Position pos) {
        Direction dir;

        //Read input string
        if (s.equalsIgnoreCase("U")) {
            //Up
            if (pos.getY() <= 0) {
                //Moving up would put the player out of bounds
                dir = null;
            }
            else {
                dir = Direction.UP;
            }
        }

        else if (s.equalsIgnoreCase("D")) {
            //Down
            if (pos.getY() >= map.getHeight()-1) {
                //Moving down would put the player out of bounds
                dir = null;
            }
            else {
                dir = Direction.DOWN;
            }
        }

        else if (s.equalsIgnoreCase("L")) {
            //Left
            if (pos.getX() <= 0) {
                //Moving left would put the player out of bounds
                dir = null;
            }
            else {
                dir = Direction.LEFT;
            }
        }

        else if (s.equalsIgnoreCase("R")) {
            //Right
            if (pos.getX() >= map.getWidth()-1) {
                //Moving right would put the player out of bounds
                dir = null;
            }
            else {
                dir = Direction.RIGHT;
            }
        }

        else {
            //Unknown input string
            dir = null;
        }

        return dir;
    }


    /**
     * Update the players position/state.
     * @param player The player to be updated.
     * @param dir The direction the player is moving in.
     */
    public void updatePlayer(Player player, Direction dir) {

        //Move the player
        player.move(dir);

        //Get the tile that the player is standing on
        Position pos = player.getPosition();
        TileType tile = map.getTileType(pos);

        //Check the tile type
        switch (tile) {
            case WATER:
                //Water tile, send the player back to the start
                player.setNotice(PlayerNotice.WATER);
                player.returnToStart();
                break;

            case TREASURE:
                //Player has found the treasure, end the game
                player.setNotice(PlayerNotice.WIN);
                gameFinished = true;
                break;

            case GRASS:
                //Grass tile, do nothing
                break;

            default:
                //tile is null, throw an error
                throw new RuntimeException("Position out of bounds or map not yet generated.");
        }
    }


    /**
     * End the game.
     * Update all players.
     */
    public void finishGame() {

        //Check each player to see if they have won
        for (Player p: players) {

            //If the player has not won, then they have lost
            if (!p.hasWon()) {
                p.setNotice(PlayerNotice.LOSE);
            }

            //Reveal the map to the player
            p.revealAllTiles();
            generateHTML(p);
        }

    }


    /**
     * End the game when playing in teams.
     * Update all teams.
     */
    public void finishTeamGame() {

        //Check each team to see if anyone has won
        for (Team t: teams) {
            //Check each player in the team
            t.first();
            while (t.hasNext()) {
                Player p = t.next();

                if (p.hasWon()) {
                    //This team has won
                    t.setWin(true);
                }
                else {
                    //If the player has not won, then they have lost
                    p.setNotice(PlayerNotice.LOSE);
                }

                //reveal all tiles for the players
                p.revealAllTiles();
            }


            //Check if this is the team that won
            if (t.hasWon()) {
                //Mark all players as winners
                t.first();
                while (t.hasNext()) {
                    Player p = t.next();
                    p.setNotice(PlayerNotice.WIN);
                }
            }

        }


        //Generate HTML for all teams
        for (Team t: teams) {
            //generate HTML for each player
            t.updateTeamMaps();
            t.first();
            while (t.hasNext()) {
                Player p = t.next();
                generateTeamHTML(t, p);
            }
        }

    }


    /**
     * Generates the HTML file with the map for a given player.
     * @param player HTML generated for this player.
     */
    public void generateHTML(Player player) {

        //Create output file
        String outputPath = HTML_OUTPUT_DIR + "/map_player_" + (player.getId()+1) + ".html";
        FileWriter outFile;
        PrintWriter html;

        try {
            //Create the HTML file in the output dir
            outFile = new FileWriter(outputPath, false);
            html = new PrintWriter(outFile);
        }
        catch (IOException e) {
            //Output directory cannot be accessed, Inform the user.
            System.out.println("Output directory cannot be accessed, html file for player " + (player.getId()+1) +
                    " cannot be created.");
            System.out.println(e.getMessage());
            return;
        }


        //Add Html head with reference to CSS file
        html.println("<html>\n<head>");
        html.println("<title>Player " + (player.getId()+1) + "</title>");
        html.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + CSS_STYLE_PATH + "\">");
        html.println("</head>\n\n");


        //Display the map
        html.println("<body>");
        html.println("\n\n<!--Display Map-->\n\n");

        //Table header, using appropriate values for width, player id and turn number
        html.println("<table>");
        html.println("<tr>");
        html.println("<th colspan=" +map.getWidth()+ "><H2>Player " +(player.getId()+1)+ "</H2>Turn " + turn + "</th>");
        html.println("</tr>");
        html.println();


        //Print Map
        for(int i = 0; i < map.getHeight(); i++) {
            //Print each row
            html.println("<tr>");
            for (int j = 0; j < map.getWidth(); j++) {
                //Print each tile in the row
                //Check if tile is revealed
                String type = "";
                Position tile = new Position(j, i);

                if (player.isTileRevealed(tile)) {
                    //Check tile type
                    switch (map.getTileType(tile)) {
                        case GRASS:
                            type = "grass";
                            break;

                        case WATER:
                            type = "water";
                            break;

                        case TREASURE:
                            type = "treasure";
                            break;
                    }
                }
                else {
                    //Tile still covered
                    type = "hidden";
                }

                //Check if it is the current tile or start tile
                String tileMarker = "";
                if (tile.equals(player.getPosition())) {
                    //Mark the current tile
                    tileMarker = "X";
                }
                else if (tile.equals(player.getStartPosition())) {
                    //Mark the start tile
                    tileMarker = "O";
                }

                html.println("<td id=\"" + type + "\">" + tileMarker + "</td>");
            }
            html.println("</tr>\n");
        }
        html.println("</table>\n\n");

        html.println("<br>\n");


        //Check if there is a notice to display
        PlayerNotice notice = player.getNotice();
        if (notice != PlayerNotice.NONE) {
            html.println("<br><br>");

            //Get the message to show the user
            String msg = "";
            switch (player.getNotice()) {
                case WATER:
                    msg = "SPLASH!<br>You hit a water tile<br>Back to the start";
                    break;

                case WIN:
                    msg = "GAME OVER<br>YOU WIN!!!";
                    break;

                case LOSE:
                    msg = "GAME OVER<br>YOU LOSE :(";
                    break;
            }

            html.println("<table>\n<tr>");
            html.println("<th width="+ (map.getWidth()*30) + ">" + msg + "</th>");
            html.println("</table>\n</tr>\n<br>");
        }


        //Copy map legend into the file
        String legend = "";
        try {
            legend = new String(Files.readAllBytes(Paths.get(HTML_LEGEND_PATH)));
            html.println(legend);
        }
        catch (IOException e) {
            //If the file cannot be accessed, do not include anything
        }


        html.println("</body>\n</html>");

        html.close();
    }


    /**
     * Generates HTML file with map for a player in a team.
     * @param t The team that the player is in.
     * @param p HTML generated for this player
     */
    public void generateTeamHTML(Team t, Player p) {
        //Create output file
        String outputPath = HTML_OUTPUT_DIR + "/map_player_" + (p.getId()+1) + ".html";
        FileWriter outFile;
        PrintWriter html;

        try {
            //Create the HTML file in the output dir
            outFile = new FileWriter(outputPath, false);
            html = new PrintWriter(outFile);
        }
        catch (IOException e) {
            //Output directory cannot be accessed, Inform the user.
            System.out.println("Output directory cannot be accessed, html file for player " + (p.getId()+1) +
                    " cannot be created.");
            System.out.println(e.getMessage());
            return;
        }


        //Add Html head with reference to CSS file
        html.println("<html>\n<head>");
        html.println("<title>Player " + (p.getId()+1) + ", Team " + (t.getId()+1) + "</title>");
        html.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + CSS_STYLE_PATH + "\">");
        html.println("</head>\n\n");


        //Display the map
        html.println("<body>");
        html.println("\n\n<!--Display Map-->\n\n");

        //Table header, using appropriate values for width, player id and turn number
        html.println("<table>");
        html.println("<tr>");
        html.print("<th colspan=" +map.getWidth()+ "><H2>Player " +(p.getId()+1)+ "</H2>");
        html.println("Team " + (t.getId()+1) + "<br>Turn " + turn + "</th>");
        html.println("</tr>");
        html.println();


        //Print Map
        for(int i = 0; i < map.getHeight(); i++) {
            //Print each row
            html.println("<tr>");
            for (int j = 0; j < map.getWidth(); j++) {
                //Print each tile in the row
                //Check if tile is revealed
                String type = "";
                Position tile = new Position(j, i);

                if (t.isTileRevealed(tile)) {
                    //Check tile type
                    switch (map.getTileType(tile)) {
                        case GRASS:
                            type = "grass";
                            break;

                        case WATER:
                            type = "water";
                            break;

                        case TREASURE:
                            type = "treasure";
                            break;
                    }
                }
                else {
                    //Tile still covered
                    type = "hidden";
                }

                //Check if it is the current tile or start tile
                String tileMarker = "";
                if (tile.equals(p.getPosition())) {
                    //Mark the current tile
                    tileMarker = "X";
                }
                else if (tile.equals(p.getStartPosition())) {
                    //Mark the start tile
                    tileMarker = "O";
                }

                html.println("<td id=\"" + type + "\">" + tileMarker + "</td>");
            }
            html.println("</tr>\n");
        }
        html.println("</table>\n\n");

        html.println("<br>\n");


        //Check if there is a notice to display
        PlayerNotice notice = p.getNotice();
        if (notice != PlayerNotice.NONE) {
            html.println("<br><br>");

            //Get the message to show the user
            String msg = "";
            switch (p.getNotice()) {
                case WATER:
                    msg = "SPLASH!<br>You hit a water tile<br>Back to the start";
                    break;

                case WIN:
                    msg = "GAME OVER<br>YOUR TEAM WINS!!!";
                    break;

                case LOSE:
                    msg = "GAME OVER<br>YOUR TEAM LOSES :(";
                    break;
            }

            html.println("<table>\n<tr>");
            html.println("<th width="+ (map.getWidth()*30) + ">" + msg + "</th>");
            html.println("</table>\n</tr>\n<br>");
        }


        //Copy map legend into the file
        String legend = "";
        try {
            legend = new String(Files.readAllBytes(Paths.get(HTML_LEGEND_PATH)));
            html.println(legend);
        }
        catch (IOException e) {
            //If the file cannot be accessed, do not include anything
        }


        html.println("</body>\n</html>");

        html.close();
    }


    /**
     * Get the number of players in the game.
     * @return The number of players.
     */
    public int getNumberOfPlayers() {
        return players.length;
    }


    /**
     * Get the number of teams in the game.
     * @return the number of teams.
     */
    public int getNumberOfTeams() {
        if (teams == null) {
            //The game has no teams
            return 0;
        }
        else {
            //Return the number of teams
            return teams.length;
        }
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


    /**
     * Clears the output directory of any old files that are already in it.
     */
    public void clearOutputDirectory() {
        File dir = new File(HTML_OUTPUT_DIR);
        File[] files = dir.listFiles(); //Get a list of all the files in the directory

        if(files!=null) { //If the directory is not empty

            for(File f: files) { //Delete all the old files in the directory
                f.delete();
            }

        }

    }

}
