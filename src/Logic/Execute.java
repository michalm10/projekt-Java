package Logic;

import Organisms.Alien;

public interface Execute {
    void move(Move m);
    void kill(Kill k);
    void spawn(Spawn s);
    void disappearAlien(AlienDisappear a);
    void add(Alien a);
}
