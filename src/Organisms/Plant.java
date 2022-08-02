package Organisms;

import Logic.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Plant extends Organism{
    public Plant(int power, int initiative, int lifeTime, int reproductionPower, char Character, Pos pos, World world, Execute exec) {
        super(power, initiative, lifeTime, reproductionPower, Character, pos, world, exec);
    }

    @Override
    public ArrayList<Commands> action() {
        ArrayList<Pos> freePos = getFreePos(this.getPos());
        ArrayList<Commands> actions = new ArrayList<>();
        Random rand = new Random();
        int divisor;
        if (this.getWorld().getCounter().getCounterBackup().get(this.getType()) != null)
            divisor = this.getWorld().getCounter().getCounterBackup().get(this.getType());
        else
            divisor = 1;


        if (this.canReproduce() && freePos.size() > 0 && rand.nextInt(divisor) < 5) {
            Pos newPos = freePos.get(rand.nextInt(freePos.size()));
            Plant newPlant = this.clone();
            newPlant.setPos(newPos);
            this.setPower((int)(this.getPower() * 0.8));
            actions.add(new Spawn(getExec(), newPlant, newPos));
        }
        return actions;
    }

    @Override
    public ArrayList<Commands> move() {
        return null;
    }

    @Override
    public abstract Plant clone();
}