package Logic;

public class Pos {
    private int posx, posy;

    public Pos(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public boolean equals(Pos pos) {
        return this.posx == pos.posx && this.posy == pos.posy;
    }

    @Override
    public String toString() {
        return "[" + posy + "," + posx + "]";
    }
}
