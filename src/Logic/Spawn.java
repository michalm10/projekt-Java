package Logic;

import Organisms.Organism;

public class Spawn implements Commands {
    private final Organism org;
    private final Pos pos;
    private final Execute exec;

    public Spawn(Execute exec, Organism org, Pos pos) {
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
        exec.spawn(this);
    }

    @Override
    public String toString() {
        return org.getType() + " pojawił się na pozycji " + pos;
    }
}
