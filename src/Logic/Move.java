package Logic;

import Organisms.Organism;

public class Move implements Commands {
    private final Organism org;
    private final Pos pos;
    private final Execute exec;

    public Move(Execute exec, Organism org, Pos pos) {
        this.org = org;
        this.pos = pos;
        this.exec = exec;
    }

    public Organism getOrg() {
        return org;
    }

    public Pos getPos() {
        return pos;
    }

    @Override
    public void execute() {
        exec.move(this);
    }

    @Override
    public String toString() {
        return org.getType() + " przesunął się z " + org.getPos() + " na " + pos;
    }
}
