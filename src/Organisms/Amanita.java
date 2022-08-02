package Organisms;

import Logic.*;

import java.util.ArrayList;
import java.util.Random;

public class Amanita extends Plant {
    public Amanita(Pos pos, World world, Execute exec) {
        super(0, 0, 14, 12, 'T', pos, world, exec);
    }

    @Override
    public ArrayList<Commands> encounter(Organism org) {
        ArrayList<Commands> actions = new ArrayList<>();
        Random rand = new Random();
        if (this.getPower() > org.getPower()) {
            actions.add(new Kill(this.getExec(), org, this));
            if (org.getWorld().getCounter().getCounterBackup().get(org.getType()) < 4) {
                ArrayList<Pos> freePos = getFreePos(org.getPos());
                if (freePos.size() > 0)
                    actions.add(new Spawn(this.getExec(), org.clone(), freePos.get(rand.nextInt(freePos.size()))));
            }
        }
        else {
            actions.add(new Kill(this.getExec(), this, org));
            if (this.getWorld().getCounter().getCounterBackup().get(getType()) < 4) {
                ArrayList<Pos> freePos = getFreePos(this.getPos());
                if (freePos.size() > 0)
                    actions.add(new Spawn(this.getExec(), clone(), freePos.get(rand.nextInt(freePos.size()))));
            }
            org.setPower((int)(org.getPower() * 0.5));
        }
        return actions;
    }
    @Override
    public Amanita clone() {
        return new Amanita(this.getPos(), this.getWorld(), this.getExec());
    }

    @Override
    public String getType() {
        return "Muchomor";
    }

    @Override
    public void accept(Counter counter) {
        counter.update(this);
    }
}