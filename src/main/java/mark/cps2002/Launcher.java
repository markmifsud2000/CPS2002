package mark.cps2002;

public class Launcher {

    public static void main(String[] args) {
        //Create a game with 3 players on a 10x14 board
        Game myGame = new Game(3, 10, 14);
        myGame.startGame();
    }

}
