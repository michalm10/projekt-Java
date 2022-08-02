package Logic;

import Organisms.Alien;
import Organisms.Organism;

import java.util.ArrayList;
import java.util.Arrays;

public class AlienExec implements Execute{
    private final ArrayList<Organism> alienList;

    public AlienExec() {
        alienList = new ArrayList<>();
    }

    public void updateMap(World world) {
        world.mapReset();
        boolean[][] map = new boolean[world.getHeight()][world.getWidth()];
        for (boolean[] row: map)
            Arrays.fill(row, true);
        for (Organism a: alienList)
            for (int i = -2; i <= 2; i++)
                for (int j = -2; j <= 2; j++) {
                    Pos p = new Pos(a.getPos().getPosx() + j, a.getPos().getPosy() + i);
                    Organism metOrganism = world.getOrgFromPos(p);
                    if (world.isInside(p) && (i != 0 || j != 0) && !(metOrganism instanceof Alien)) {
                        map[p.getPosy() - 1][p.getPosx() - 1] = false;
                    }
                }
        world.setMap(map);
    }

    public void add(Alien a) {
        alienList.add(a);
        updateMap(a.getWorld());
    }

    public void remove(Organism a) {
        alienList.remove(a);
    }

    public void move(Move m) {
        //System.out.println(m);
        m.getOrg().setPos(m.getPos());
        updateMap(m.getOrg().getWorld());
    }

    public void kill(Kill k) {
        System.out.println(k);
        k.getOrg().setPos(new Pos(-1, -1));
        k.getKiller().setPower((int)(k.getKiller().getPower() * 0.5));
        remove(k.getOrg());
        updateMap(k.getOrg().getWorld());
    }

    public void spawn(Spawn s) {
        //System.out.println(s);
        s.getOrg().getWorld().spawn(s.getOrg());
    }

    public void disappearAlien(AlienDisappear a) {
        System.out.println(a);
        Alien alien = a.getAlien();
        if (alien.getState() == Alien.st.visible) {
            alien.setPos(a.getNewPos());
            alien.getWorld().spawn(alien);
        }
        else {
            alien.getWorld().addSubscriber(alien);
            alien.setPos(new Pos(-3, -3));
        }
        updateMap(a.getAlien().getWorld());
    }
}
