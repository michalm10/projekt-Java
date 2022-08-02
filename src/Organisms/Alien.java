package Organisms;

import Logic.*;

import java.util.ArrayList;
import java.util.Random;

public class Alien extends Animal {
    public enum st {visible, invisible}
    private st state;

    public Alien(Pos pos, World world, Execute exec) {
        super(100, 10, 1000, 108, 'A', pos, world, exec);
        state = st.visible;
        this.getExec().add(this);
    }

    public st getState() {
        return state;
    }

    @Override
    public ArrayList<Commands> secondaryAction(ArrayList<Commands> actions) {
        if (actions == null)
            actions = new ArrayList<>();
        Random rand = new Random();
        int changeStateChance = rand.nextInt(1000);
        if (changeStateChance < 100) {
            if (state == st.visible) {
                state = st.invisible;
                actions.add(new AlienDisappear(this.getExec(), this, this.getPos()));
            }
            else {
                state = st.visible;
                ArrayList<Pos> freePos = getNextPos(this.getPrevPos());
                if (freePos != null) {
                    Pos newPos = freePos.get(rand.nextInt(freePos.size()));
                    actions.add(new AlienDisappear(this.getExec(),this, newPos));
                    Organism metOrganism = this.getWorld().getOrgFromPos(newPos);
                    if (metOrganism != null)
                        actions.addAll(metOrganism.encounter(this));
                }
            }
        }

        return actions;
    }

    @Override
    public ArrayList<Pos> getNextPos(Pos pos) {
        ArrayList<Pos> vacant = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                Pos p = new Pos(pos.getPosx() + j, pos.getPosy() + i);
                if (this.getWorld().isInside(p) && (i != 0 || j != 0)) {
                    Organism organismThere = this.getWorld().getOrgFromPos(p);
                    if (organismThere == null) {
                        vacant.add(p);
                        continue;
                    }
                    if (!(organismThere instanceof Alien))
                        vacant.add(p);
                }
            }
        return vacant;
    }

    @Override
    public Alien clone() {
        return new Alien(this.getPos(), this.getWorld(), this.getExec());
    }

    @Override
    public String getType() {
        return "Kosmita";
    }

    @Override
    public void accept(Counter counter) {
        counter.update(this);
    }
}
