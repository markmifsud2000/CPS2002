package mark.cps2002;

public enum TileType {
    GRASS,      //The tile is grass, nothing happens when a player lands on this
    WATER,      //The tile is water, if a player lands on it, they should be sent back to the start
    TREASURE    //The tile is the one treasure tile, if a player lands on it, they have won and the game is over
}
