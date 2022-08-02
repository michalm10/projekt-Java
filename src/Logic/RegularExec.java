package Logic;

import Organisms.Alien;

public class RegularExec implements Execute{
    public void move(Move m) {
        //System.out.println(m);
        m.getOrg().setPos(m.getPos());
    }

    public void kill(Kill k) {
        System.out.println(k);
        k.getOrg().setPos(new Pos(-1, -1));
        k.getKiller().setPower((int)(k.getKiller().getPower() * 0.5));
    }

    public void spawn(Spawn s) {
        //System.out.println(s);
        s.getOrg().setPos(s.getPos());
        s.getOrg().getWorld().spawn(s.getOrg());
    }

    public void disappearAlien(AlienDisappear a) {}

    public void add(Alien a) {}

}
