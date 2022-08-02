package Logic;

import Organisms.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AlienTests {
    @Test
    public void AlienPlacementTest() {
        World world = new World(10, 10);
        OrganismFactory factory = new OrganismFactory();

        world.spawn(factory.getOrg("kosmita", 3, 5, world));
        world.spawn(factory.getOrg("kosmita", 7, 9, world));

        world.nextTurn();

        boolean[][] map = {
                {true, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true, true, true},
                {false, false, false, false, false, true, true, true, true, true},
                {false, false, false, false, false, true, true, true, true, true},
                {false, false, true, false, false, true, true, true, true, true},
                {false, false, false, false, false, true, true, true, true, true},
                {false, false, false, false, false, false, false, false, false, true},
                {true, true, true, true, false, false, false, false, false, true},
                {true, true, true, true, false, false, true, false, false, true},
                {true, true, true, true, false, false, false, false, false, true}};

        assertArrayEquals(map, world.getMap());
    }

    @Test
    public void AlienDisappearTest() {
        World world = new World(10, 10);
        OrganismFactory factory = new OrganismFactory();

        Organism alien = factory.getOrg("kosmita", 3, 5, world);
        world.spawn(alien);
        Alien alien1 = (Alien) alien;
        alien1.setReproductionPower(10000);
        while (alien1.getPos().getPosx() > 0 && alien1.getPos().getPosy() > 0) {
            world.nextTurn();
            if (alien1.getLifeTime() < 0 || !world.isInside(alien1.getPos()))
                break;
        }

        boolean[][] map = new boolean[10][10];
        for (boolean[] row: map)
            Arrays.fill(row, true);
        assertArrayEquals(map, world.getMap());
        assertEquals(-3, alien1.getPos().getPosx());
        assertEquals(-3, alien1.getPos().getPosy());

        int i = 1000;
        while (alien1.getState() == Alien.st.invisible && i > 0) {
            world.nextTurn();
            i--;
        }
        assertTrue(alien1.getPos().getPosx() > 0  && alien1.getPos().getPosy() > 0);
    }

    @Test
    public void timeStopTest() {
        World world = new World(10, 10);
        OrganismFactory factory = new OrganismFactory();
        ArrayList<Organism> org0 = new ArrayList<>();
        ArrayList<Organism> org1 = new ArrayList<>();

        Organism sheep = factory.getOrg("owca", 4, 6, world);
        Organism wolf = factory.getOrg("wilk", 3, 7, world);
        Organism dandelion = factory.getOrg("mlecz", 5, 5, world);
        Organism grass = factory.getOrg("trawa", 3, 4, world);
        Organism amanita = factory.getOrg("muchomor", 4, 4, world);
        Organism alien = factory.getOrg("kosmita", 3, 5, world);

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

        org0.add(factory.getOrg("owca", 4, 6, world));
        org0.add(factory.getOrg("wilk", 3, 7, world));
        org0.add(factory.getOrg("mlecz", 5, 5, world));
        org0.add(factory.getOrg("trawa", 3, 4, world));
        org0.add(factory.getOrg("muchomor", 4, 4, world));

        world.nextTurn();
        world.spawn(alien);
        world.nextTurn();

        for (int i = 0; i < 5; i++) {
            if (org0.get(i).getPos().getPosx() <= 5 && org0.get(i).getPos().getPosx() >= 1
            && org0.get(i).getPos().getPosy() <= 7 && org0.get(i).getPos().getPosx() >= 3) {
                assertEquals(org0.get(i).getPower(), org1.get(i).getPower());
                assertEquals(org0.get(i).getLifeTime(), org1.get(i).getLifeTime());
                assertTrue(org0.get(i).getPos().equals(org1.get(i).getPos()));
            }
        }
    }
}
