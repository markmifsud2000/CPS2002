/**
 * CPS2002 Software Engineering
 * Assignment 2020
 * Mark Mifsud (0382200L)
 * B.Sc. Mathematics and Computer Science Yr2
 *
 * Team.java
 * Last Released:
 *
 * Represents a team in the game.
 * A team consists of multiple players who explore a map together.
 * All players in team will have the same tiles on the map revealed to them.
 */

package mark.cps2002;

import java.util.ArrayList;

public class Team {

    private int id;                     //The unique Identifier for team
    private boolean[][] revealedMap;    //Tile is set to true if the team has revealed that tile
    private boolean hasWon;             //True if the team has won, false otherwise

    private ArrayList<Player> players;  //The list of players in the team

    private int size;                   //The number of players in the team
    int iter;                           //The index of the current player in the team



    /**
     * Constructs a team
     * @param id The team's unique ID.
     * @param boardWidth The width of the board that the team will play on.
     * @param boardHeight The height of the board that the team will play on.
     * @throws IllegalArgumentException if any argument is negative
     */
    public Team (int id, int boardWidth, int boardHeight) throws IllegalArgumentException{

        //Check if all arguments are valid
        if (id < 0) {
            throw new IllegalArgumentException("Team id must be greater or equal to 0.");
        }
        else if (boardWidth <= 0 || boardHeight <= 0) {
            throw new IllegalArgumentException("Board dimensions must be greater than 0.");
        }


        //Initialise values
        this.id = id;
        this.revealedMap = new boolean[boardWidth][boardHeight];
        this.hasWon = false;

        this.players = new ArrayList<>();
        this.size = 0;
        this.iter = -1;
    }

    //Iterator methods

    /**
     * Get the first player in the team.
     * Point the iterator at the first element.
     * @return The first player in the team.
     */
    public Player first() {
        //Point to the first player in the team
        if (size > 0) {
            iter = -1;  //Calling next will return 0
            return players.get(0);
        }
        else {
            //Team is empty
            return null;
        }
    }

    /**
     * Get the next player in the team.
     * Point the iterator at the next player.
     * @return The next player in the team.
     */
    public Player next() {
        //Increase the pointer to the next player
        iter++;

        //Check if there is a next player
        if (iter < size) {
            //Return the current player
            return players.get(iter);
        }
        else {
            //There is no next player
            return null;
        }
    }

    /**
     * Check if the team has another player.
     * @return True if there is a next player, false otherwise.
     */
    public boolean hasNext() {
        //Check if there we can still increase the pointer
        return iter < size-1;
    }

    /**
     * Returns the current team player being pointed at.
     * @return The current player.
     */
    public Player getCurrent() {
        //Check if iter points at a player
        if (iter < size && size > 0 && iter >= 0) {
            //Do not increase pointer
            return players.get(iter);
        }
        else if (iter < 0){
            //Next has not been called yet
            //Try return first player (returns null if team empty)
            return first();
        }
        else {
            //Pointer is out of the list
            return null;
        }

    }

    //End Iterator Methods

    //Managing team

    /**
     * Returns the team's unique ID.
     * @return The team's id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the number of members in the Team.
     * @return The size of the team.
     */
    public int size() {
        return this.size;
    }

    /**
     * Adds a player to the team.
     * @param player The player to be added.
     * @return True if the player was added successfully, false otherwise.
     */
    public boolean addPlayer(Player player) {

        //Check that the player is not null
        if (player == null) {
            return false;
        }

        //Add the player
        size++;
        players.add(player);

        return true;
    }

    /**
     * Mark whether the player has won the game.
     * @param win True if the team has won, false otherwise.
     */
    public void setWin(boolean win){
        this.hasWon = win;
    }

    /**
     * Check if the team has won the game.
     * @return true if the team has won, false otherwise.
     */
    public boolean hasWon() {
        return this.hasWon;
    }

    //Map management

    /**
     * Update the maps for all players in the team.
     */
    public void updateTeamMaps() {

        //For each player
        for (Player p: players) {

            //Update each tile in the grid
            for (int i = 0; i < revealedMap.length; i++) {
                for (int j = 0; j < revealedMap.length; j++) {

                    //if the player has revealed the tile, update it
                    Position tile = new Position(i, j);
                    revealedMap[i][j] = revealedMap[i][j] || p.isTileRevealed(tile);

                }
            }

        }

    }

    /**
     * Check if a given tile is revealed to the team.
     * @param pos The position of the tile.
     * @return True if the tile is revealed, false otherwise.
     */
    public boolean isTileRevealed(Position pos) {
        int x = pos.getX();
        int y = pos.getY();

        return this.revealedMap[x][y];
    }

}
