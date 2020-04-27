package mark.cps2002;

public class Game {

    final int MIN_PLAYERS = 2;              //The minimum number of players
    final int MAX_PLAYERS = 8;              //The maximum number of players
    final int SMALL_BOARD_PLAYER_LIMIT = 4; //The number of players for which the minimum board size changes
    final int MIN_BOARD_SIZE_SMALL = 5;     //The minimum board size for few players
    final int MIN_BOARD_SIZE_BIG = 8;       //The minimum board size for many players

    Player[] players;       //The players currently in the game
    Map map;                //The map being played on
    int turn;               //The current turn number
    boolean gameFinished;   //Whether or not the game has been finished yet

    public Game(int noOfPlayers, int boardWidth, int boardHeight) {
        this.players = new Player[noOfPlayers];
        this.turn = 0;
        this.gameFinished = false;
    }

    public void startGame() {

    }

    public void generateHTML(Player player) {

    }

    public int getNumberOfPlayers() {
        return players.length;
    }

    public int getTurnNumber() {
        return turn;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

}
