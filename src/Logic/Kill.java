package Logic;

import Organisms.Organism;

public class Kill implements  Commands{
    private final Organism org, killer;
    private final Execute exec;

    public Kill(Execute exec, Organism org, Organism killer) {
        this.org = org;
        this.killer = killer;
        this.exec = exec;
    }

    public Organism getOrg() {
        return org;
    }

    public Organism getKiller() {
        return org;
    }

    @Override
    public void execute() {
        exec.kill(this);
    }

    @Override
    public String toString() {
        return org.getType() + " na pozycji " + org.getPos() + " został zabity przez " + killer.getType();
    }
}
