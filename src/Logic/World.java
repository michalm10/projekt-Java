package Logic;

import Organisms.*;

import java.util.*;

public class World {
    public boolean showMap;
    private final int width;
    private final int height;
    private int turn;
    private final ArrayList<Organism> organisms = new ArrayList<>();
    private final ArrayList<Organism> newOrganisms = new ArrayList<>();
    private boolean[][] map;
    private final ArrayList<Organism> subscribers = new ArrayList<>();
    private final Counter counter;

    public World(int width, int height) {
        width = Math.max(width, 0);
        height = Math.max(height, 0);
        this.width = width;
        this.height = height;
        map = new boolean[height][width];
        mapReset();
        counter = new Counter();
    }

    public Counter getCounter() {
        return counter;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addSubscriber (Organism org) {
        subscribers.add(org);
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public void addOrganism(Organism org) {
        if (!isInside(org.getPos())) return;
        organisms.add(org);
        organisms.sort(Comparator.comparingInt(Organism::getInitiative));
        Collections.reverse(organisms);
    }

    public void spawn(Organism org) {
        if (!isInside(org.getPos()))
            return;
        newOrganisms.add(org);
    }

    public void nextTurn() {
        Invoke invoker = new Invoke();
        ArrayList<Commands> actions;
        counter.resetCounter();

        for (Organism org: subscribers) {
            actions = org.secondaryAction(null);
            if (actions != null)
                invoker.execute(actions);
        }
        subscribers.removeIf(org -> isInside(org.getPos()));

        for (Organism org: organisms) {
            if (isInside(org.getPos()) && map[org.getPos().getPosy() - 1][org.getPos().getPosx() - 1]) {
                actions = org.move();
                if (actions != null)
                    invoker.execute(actions);
                if (isInside(org.getPos())) {
                    actions = org.action();
                    if (actions == null) continue;
                    invoker.execute(actions);
                }
            }
        }
        organisms.removeIf(org -> !isInside(org.getPos()));

        for (Organism org: organisms) {
            if (!map[org.getPos().getPosy() - 1][org.getPos().getPosx() - 1])
                continue;
            org.setLifeTime(org.getLifeTime() - 1);
            org.setPower(org.getPower() + 1);
            if (org.getLifeTime() < 1)
                System.out.println(org.getType() + " zmarł ze starości na pozycji " + org.getPos());
        }

        organisms.removeIf(org -> org.getLifeTime() < 1);
        organisms.addAll(newOrganisms);
        organisms.sort(Comparator.comparingInt(Organism::getInitiative));
        Collections.reverse(organisms);
        newOrganisms.clear();
        turn++;
        for (Organism org: subscribers)
            org.accept(counter);
        for (Organism org: organisms)
            org.accept(counter);
    }

    public boolean isInside(Pos pos) {
        return pos.getPosx() <= width && pos.getPosx() > 0 && pos.getPosy() <= height && pos.getPosy() > 0;
    }

    public Organism getOrgFromPos(Pos pos) {
        for (Organism org: organisms)
            if (pos.getPosx() == org.getPos().getPosx() && pos.getPosy() == org.getPos().getPosy())
                return org;
        for (Organism org: newOrganisms)
            if (pos.getPosx() == org.getPos().getPosx() && pos.getPosy() == org.getPos().getPosy())
                return org;
        return null;
    }

    public ArrayList<Pos> filterFreePos(ArrayList<Pos> positions) {
        positions.removeIf(pos -> getOrgFromPos(pos) != null);

        return  positions;
    }

    public void setMap(boolean[][] m) {
        map = m;
    }

    public boolean[][] getMap() {
        return map;
    }

    public void mapReset() {
        for (boolean[] row: map)
            Arrays.fill(row, true);
    }

    @Override
    public String toString() {
        StringBuilder toRet = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Organism org = getOrgFromPos(new Pos(j + 1, i + 1));
                if (org != null)
                    toRet.append(org.getCharacter());
                else
                    toRet.append('-');
            }
            if(showMap) {
                toRet.append("\t");
                for (int j = 0; j < width; j++) {
                    if (map[i][j])
                        toRet.append(1);
                    else
                        toRet.append(0);
                }
            }
            toRet.append('\n');
        }

        Map<String, Integer> c = counter.getCounter();
        Set<Map.Entry<String, Integer>> entries = c.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            toRet.append(entry.getKey()).append(": ").append(entry.getValue()).append(" ");
        }
        toRet.append('\n');

        return toRet.toString();
    }
}
