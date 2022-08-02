package Organisms;

import Logic.Counter;
import Logic.Execute;
import Logic.Pos;
import Logic.World;

import java.util.ArrayList;

public class Sheep extends Animal {
    public Sheep(Pos pos, World world, Execute exec) {
        super(9, 3, 12, 10, 'S', pos, world, exec);
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
                    if (!(organismThere instanceof Alien) && !(organismThere instanceof Sheep))
                        vacant.add(p);
                }
            }
        return vacant;
    }

    @Override
    public Sheep clone() {
        return new Sheep(this.getPos(), this.getWorld(), this.getExec());
    }

    @Override
    public String getType() {
        return "Owca";
    }

    @Override
    public void accept(Counter counter) {
        counter.update(this);
    }
}
