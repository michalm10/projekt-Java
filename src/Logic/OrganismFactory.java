package Logic;

import Organisms.*;

public class OrganismFactory {
    private final RegularExec exec0;
    private final AlienExec exec1;

    public OrganismFactory() {
        exec0 = new RegularExec();
        exec1 = new AlienExec();
    }

    public Organism getOrg(String name, int x, int y, World world) {
        if (name == null) return null;
        if (name.equalsIgnoreCase("Kosmita")) return new Alien(new Pos(x, y), world, exec1);
        if (name.equalsIgnoreCase("Muchomor")) return new Amanita(new Pos(x, y), world, exec0);
        if (name.equalsIgnoreCase("Mlecz")) return new Dandelion(new Pos(x, y), world, exec0);
        if (name.equalsIgnoreCase("Trawa")) return new Grass(new Pos(x, y), world, exec0);
        if (name.equalsIgnoreCase("Owca")) return new Sheep(new Pos(x, y), world, exec0);
        if (name.equalsIgnoreCase("Wilk")) return new Wolf(new Pos(x, y), world, exec0);
        return null;
    }
}
