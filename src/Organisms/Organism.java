package Organisms;

import Logic.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Organism {
    private int power;
    private int initiative;
    private Pos pos;
    private int lifeTime;
    private int reproductionPower;
    private char Character;
    private World world;
    private final Execute exec;

    public Organism(int power, int initiative, int lifeTime,
                    int reproductionPower, char Character, Pos pos, World world, Execute exec) {
        this.power = power;
        this.initiative = initiative;
        this.pos = pos;
        this.lifeTime = lifeTime;
        this.reproductionPower = reproductionPower;
        this.Character = Character;
        this.world = world;
        this.exec = exec;
    }

    public Execute getExec() {
        return exec;
    }

    public ArrayList<Commands> encounter(Organism org) {
        ArrayList<Commands> actions = new ArrayList<>();
        Random rand = new Random();
        if (power > org.getPower() && !(this instanceof Plant)) {
            actions.add(new Kill(exec, org, this));
            //System.out.println(org.getWorld().getCounter().getCounterBackup().get(org.getType()));
            if (org.getWorld().getCounter().getCounterBackup().get(org.getType()) < 4) {
                ArrayList<Pos> freePos = getWorld().filterFreePos(org.getNextPos(org.getPos()));
                if (freePos.size() > 0)
                    actions.add(new Spawn(exec, org.clone(), freePos.get(rand.nextInt(freePos.size()))));
            }
        }
        else if (!(org instanceof Plant)) {
            actions.add(new Kill(exec, this, org));
            //System.out.println(org.getWorld().getCounter().getCounterBackup().get(this.getType()));
            if (world.getCounter().getCounterBackup().get(this.getType()) < 4) {
                ArrayList<Pos> freePos = getFreePos(pos);
                if (freePos.size() > 0)
                    actions.add(new Spawn(exec, clone(), freePos.get(rand.nextInt(freePos.size()))));
            }
        }
        return actions;
    }

    public ArrayList<Pos> getNextPos(Pos pos) {
        ArrayList<Pos> vacant = new ArrayList<>();
        for (int i = -3; i <= 3; i++)
            for (int j = -3; j <= 3; j++) {
                Pos p = new Pos(pos.getPosx() + j, pos.getPosy() + i);
                if (world.isInside(p) && (i != 0 || j != 0))
                    vacant.add(p);
            }
        return vacant;
    }

    public ArrayList<Pos> getFreePos(Pos pos) {
        return this.getWorld().filterFreePos(getNextPos(pos));
    }

    public boolean canReproduce() {
        return this.power >= this.reproductionPower;
    }

    public abstract Organism clone();

    public abstract ArrayList<Commands> move();

    public abstract ArrayList<Commands> action();

    public ArrayList<Commands> secondaryAction(ArrayList<Commands> c) {
        return null;
    }

    public abstract String getType();

    public abstract void accept(Counter counter);

    public int getInitiative() { return initiative; }

    public int getPower() {
        return power;
    }

    public Pos getPos() {
        return pos;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getReproductionPower() {
        return reproductionPower;
    }

    public char getCharacter() {
        return Character;
    }

    public World getWorld() {
        return world;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void setReproductionPower(int reproductionPower) {
        this.reproductionPower = reproductionPower;
    }

    public void setCharacter(char character) {
        Character = character;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
