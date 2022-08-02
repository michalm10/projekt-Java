package Organisms;

import Logic.Counter;
import Logic.Execute;
import Logic.Pos;
import Logic.World;

public class Dandelion extends Plant {
    public Dandelion(Pos pos, World world, Execute exec) {
        super(0, 0, 6, 4, 'D', pos, world, exec);
    }

    @Override
    public Dandelion clone() {
        return new Dandelion(this.getPos(), this.getWorld(), this.getExec());
    }

    @Override
    public String getType() {
        return "Mlecz";
    }

    @Override
    public void accept(Counter counter) {
        counter.update(this);
    }
}