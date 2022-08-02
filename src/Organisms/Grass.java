package Organisms;

import Logic.Counter;
import Logic.Execute;
import Logic.Pos;
import Logic.World;

public class Grass extends Plant {
    public Grass(Pos pos, World world, Execute exec) {
        super(0, 0, 4, 1, 'G', pos, world, exec);
    }

    @Override
    public Grass clone() {
        return new Grass(this.getPos(), this.getWorld(), this.getExec());
    }

    @Override
    public String getType() {
        return "Trawa";
    }

    @Override
    public void accept(Counter counter) {
        counter.update(this);
    }
}