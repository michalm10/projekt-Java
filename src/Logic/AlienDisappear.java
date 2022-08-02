package Logic;

import Organisms.Alien;

public class AlienDisappear implements Commands{
    private final Alien alien;
    private final Pos newPos;
    private Execute exec;

    public AlienDisappear(Execute exec, Alien alien, Pos pos) {
        this.newPos = pos;
        this.alien = alien;
        this.exec = exec;
    }

    public Alien getAlien() {
        return alien;
    }

    public Pos getNewPos() {
        return newPos;
    }

    @Override
    public void execute() {
        exec.disappearAlien(this);
    }

    @Override
    public String toString() {
        if (alien.getState() == Alien.st.visible)
            return alien.getType() + " pojawił się na pozycji " + newPos;
        else
            return alien.getType() + " zniknął na pozycji " + alien.getPos();
    }
}
