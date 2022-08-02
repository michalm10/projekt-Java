package Logic;

import Organisms.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrganismsTests {
    @Test
    public void moveTest() {
        World world = new World(5, 5);
        OrganismFactory factory = new OrganismFactory();

        Organism sheep = factory.getOrg("owca", 2, 2, world);
        Organism dandelion = factory.getOrg("mlecz", 5, 5, world);
        world.spawn(sheep);
        world.spawn(dandelion);
        world.nextTurn();
        world.nextTurn();

        assertTrue(sheep.getPos().getPosx() >= 1 && sheep.getPos().getPosx() <= 4);
        assertTrue(sheep.getPos().getPosy() >= 1 && sheep.getPos().getPosy() <= 4);
        assertTrue(sheep.getPos().getPosx() != 2 || sheep.getPos().getPosy() != 2);
        assertEquals(5, dandelion.getPos().getPosx());
        assertEquals(5, dandelion.getPos().getPosy());
    }

    @Test
    public void reproduceTest() {
        World world = new World(5, 5);
        OrganismFactory factory = new OrganismFactory();

        Organism sheep = factory.getOrg("owca", 2, 2, world);
        Organism dandelion = factory.getOrg("mlecz", 5, 5, world);
        sheep.setPower(100);
        dandelion.setPower(100);
        world.spawn(sheep);
        world.spawn(dandelion);
        world.nextTurn();
        world.nextTurn();

        int sc = 0, dc = 0, all = 0;
        for (int i = 1; i <= 5; i++)
            for (int j = 1; j <= 5; j++)
                if (world.getOrgFromPos(new Pos(j, i)) != null) {
                    all++;
                    if (world.getOrgFromPos(new Pos(j, i)) instanceof Sheep) sc++;
                    if (world.getOrgFromPos(new Pos(j, i)) instanceof Dandelion) dc++;
                }

        assertEquals(4, all);
        assertEquals(2, sc);
        assertEquals(2, dc);
    }

    @Test
    public void negativeReproduceTest() {
        World world = new World(5, 5);
        OrganismFactory factory = new OrganismFactory();

        Organism sheep = factory.getOrg("owca", 2, 2, world);
        Organism dandelion = factory.getOrg("mlecz", 5, 5, world);
        world.spawn(sheep);
        world.spawn(dandelion);
        world.nextTurn();
        world.nextTurn();

        int sc = 0, dc = 0, all = 0;
        for (int i = 1; i <= 5; i++)
            for (int j = 1; j <= 5; j++)
                if (world.getOrgFromPos(new Pos(j, i)) != null) {
                    all++;
                    if (world.getOrgFromPos(new Pos(j, i)) instanceof Sheep) sc++;
                    if (world.getOrgFromPos(new Pos(j, i)) instanceof Dandelion) dc++;
                }

        assertEquals(2, all);
        assertEquals(1, sc);
        assertEquals(1, dc);
    }

    @Test
    public void killTest() {
        World world = new World(5, 5);
        OrganismFactory factory = new OrganismFactory();
        ArrayList<Organism> sheeps = new ArrayList<>();

        Organism sheep1 = factory.getOrg("owca", 5, 4, world);
        Organism sheep2 = factory.getOrg("owca", 4, 4, world);
        Organism sheep3 = factory.getOrg("owca", 4, 4, world);
        Organism sheep4 = factory.getOrg("owca", 3, 4, world);
        Organism sheep5 = factory.getOrg("owca", 3, 3, world);
        Organism sheep6 = factory.getOrg("owca", 3, 5, world);
        Organism sheep7 = factory.getOrg("owca", 4, 3, world);
        Organism sheep8 = factory.getOrg("owca", 5, 3, world);
        Organism wolf = factory.getOrg("wilk", 5, 5, world);
        world.spawn(sheep1);
        world.spawn(sheep2);
        world.spawn(sheep3);
        world.spawn(sheep4);
        world.spawn(sheep5);
        world.spawn(sheep6);
        world.spawn(sheep7);
        world.spawn(sheep8);
        world.spawn(wolf);

        sheeps.add(sheep1);
        sheeps.add(sheep2);
        sheeps.add(sheep3);
        sheeps.add(sheep4);
        sheeps.add(sheep5);
        sheeps.add(sheep6);
        sheeps.add(sheep7);
        sheeps.add(sheep8);

        world.nextTurn();
        world.nextTurn();

        int wc = 0;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++)
                if (world.getOrgFromPos(new Pos(j, i)) != null) {
                    if (world.getOrgFromPos(new Pos(j, i)) instanceof Wolf) wc++;
                }
        }

        assertEquals(1, wc);
        int killed = 0, survived = 0;
        for (Organism sheep: sheeps) {
            if (sheep.getPos().getPosx() == -1 && sheep.getPos().getPosy() == -1) killed++;
            if (sheep.getPos().getPosx() > 0 && sheep.getPos().getPosy() > 0) survived++;
        }
        assertTrue(1 <= killed);
        assertTrue(7 >= survived);
    }

    @Test
    public void parametersTest() {
        World world = new World(10, 10);
        OrganismFactory factory = new OrganismFactory();
        ArrayList<Organism> org0 = new ArrayList<>();
        ArrayList<Organism> org1 = new ArrayList<>();

        Organism sheep = factory.getOrg("owca", 1, 1, world);
        Organism wolf = factory.getOrg("wilk", 10, 10, world);
        Organism dandelion = factory.getOrg("mlecz", 5, 5, world);
        Organism grass = factory.getOrg("trawa", 10, 1, world);
        Organism amanita = factory.getOrg("muchomor", 1, 10, world);

        world.spawn(sheep);
        world.spawn(wolf);
        world.spawn(dandelion);
        world.spawn(grass);
        world.spawn(amanita);

        org1.add(sheep);
        org1.add(wolf);
        org1.add(dandelion);
        org1.add(grass);
        org1.add(amanita);

        org0.add(factory.getOrg("owca", 1, 1, world));
        org0.add(factory.getOrg("wilk", 10, 10, world));
        org0.add(factory.getOrg("mlecz", 5, 5, world));
        org0.add(factory.getOrg("trawa", 10, 1, world));
        org0.add(factory.getOrg("muchomor", 1, 10, world));

        world.nextTurn();
        world.nextTurn();

        for (int i = 0; i < 5; i++) {
            assertEquals(org0.get(i).getLifeTime() - 1, org1.get(i).getLifeTime());
        }

        ArrayList<Organism> org = world.getOrganisms();
        for (int i = 1; i < org.size(); i++) {
            assertTrue(org.get(i).getInitiative() <= org.get(i - 1).getInitiative());
        }

    }
}
