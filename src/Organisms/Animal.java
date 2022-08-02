package Organisms;

import Logic.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal extends Organism {
    private Pos prevPos;

    public Animal(int power, int initiative, int lifeTime, int reproductionPower, char Character, Pos pos, World world, Execute exec) {
        super(power, initiative, lifeTime, reproductionPower, Character, pos, world, exec);
    }

    public Pos getPrevPos() {
        return prevPos;
    }

    public void setPrevPos(Pos prevPos) {
        this.prevPos = prevPos;
    }

    @Override
    public ArrayList<Commands> move() {
        prevPos = this.getPos();
        ArrayList<Pos> freePos = getNextPos(this.getPos());
        ArrayList<Commands> actions = new ArrayList<>();
        Random rand = new Random();
        if (freePos.size() < 1)
            return actions;
        Pos newPos = freePos.get(rand.nextInt(freePos.size()));
        actions.add(new Move(getExec(),this, newPos));
        Organism metOrganism = this.getWorld().getOrgFromPos(newPos);
        if (metOrganism != null)
            actions.addAll(metOrganism.encounter(this));
        else
            if (rand.nextInt(3) == 1)
                this.setPower(getPower() - 1);

        return actions;
    }

    @Override
    public ArrayList<Commands> action() {
        ArrayList<Pos> freeBirthPos = getFreePos(this.getPos());
        ArrayList<Commands> actions = new ArrayList<>();
        Random rand = new Random();
        int divisor;
        if (this.getWorld().getCounter().getCounterBackup().get(this.getType()) != null)
            divisor = this.getWorld().getCounter().getCounterBackup().get(this.getType());
        else
            divisor = 1;

        if (this.canReproduce() && freeBirthPos.size() > 0 && rand.nextInt(divisor) < 5) {
            Pos newPos = freeBirthPos.get(rand.nextInt(freeBirthPos.size()));
            Animal newAnimal = this.clone();
            newAnimal.setPos(newPos);
            this.setPower((int)(this.getPower() * 0.8));
            actions.add(new Spawn(getExec(), newAnimal, newPos));
        }
        return secondaryAction(actions);
    }

    @Override
    public ArrayList<Commands> secondaryAction(ArrayList<Commands> actions) {
        return actions;
    }

    @Override
    public abstract Animal clone();
}
