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

public class Team {

    /**
     * Constructs a team
     * @param id The team's unique ID.
     * @param boardWidth The width of the board that the team will play on.
     * @param boardHeight The height of the board that the team will play on.
     */
    public Team (int id, int boardWidth, int boardHeight) {

    }

    //Iterator methods

    /**
     * Get the first player in the team.
     * Point the iterator at the first element.
     * @return The first player in the team.
     */
    public Player first() {
        return null;
    }

    /**
     * Get the next player in the team.
     * Point the iterator at the next player.
     * @return The next player in the team.
     */
    public Player next() {
        return null;
    }

    /**
     * Check if the team has another player.
     * @return True if there is a next player, false otherwise.
     */
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the current team player being pointed at.
     * @return The current player.
     */
    public Player getCurrent() {
        return null;
    }

    //End Iterator Methods

    //Managing team members

    /**
     * Get the number of members in the Team.
     * @return The size of the team.
     */
    public int size() {
        return 0;
    }

    /**
     * Adds a player to the team.
     * @param player The player to be added.
     * @return True if the player was added successfully, false otherwise.
     */
    public boolean addPlayer(Player player) {
        return true;
    }

    //Map management

    /**
     * Update the maps for all players in the team.
     */
    public void updateTeamMaps() {

    }

    /**
     * Check if a given tile is revealed to the team.
     * @param pos The position of the tile.
     * @return True if the tile is revealed, false otherwise.
     */
    public boolean isTileRevealed(Position pos) {
        return false;
    }

}
