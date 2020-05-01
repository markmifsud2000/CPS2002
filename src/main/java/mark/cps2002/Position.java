package mark.cps2002;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean equals(Position p) {
        return (this.getX()==p.getX()) && (this.getY()==p.getY());
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
