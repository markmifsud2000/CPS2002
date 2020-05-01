package mark.cps2002;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        Game myGame = new Game(2, 8, 5);
        myGame.startGame();
    }

}
