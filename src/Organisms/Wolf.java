package Organisms;

import Logic.Counter;
import Logic.Execute;
import Logic.Pos;
import Logic.World;

import java.util.ArrayList;

public class Wolf extends Animal {
    public Wolf(Pos pos, World world, Execute exec) {
        super(16, 5, 30, 32, 'W', pos, world, exec);
    }

    @Override
    public ArrayList<Pos> getNextPos(Pos pos) {
        ArrayList<Pos> vacant = new ArrayList<>();
        for (int i = -1; i <= 2; i++)
            for (int j = -1; j <= 2; j++) {
                Pos p = new Pos(pos.getPosx() + j, pos.getPosy() + i);
                if (this.getWorld().isInside(p) && (i != 0 || j != 0)) {
                    Organism organismThere = this.getWorld().getOrgFromPos(p);
                    if (organismThere == null) {
                        vacant.add(p);
                        continue;
                    }
                    if (!(organismThere instanceof Alien) && !(organismThere instanceof Wolf))
                        vacant.add(p);
                }
            }
        return vacant;
    }

    @Override
    public Wolf clone() {
        return new Wolf(this.getPos(), this.getWorld(), this.getExec());
    }

    @Override
    public String getType() {
        return "Wilk";
    }

    @Override
    public void accept(Counter counter) {
        counter.update(this);
    }
}
