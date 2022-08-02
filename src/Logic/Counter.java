package Logic;

import Organisms.*;

import java.util.Map;
import java.util.TreeMap;

public class Counter {
    Map<String, Integer> counter;
    Map<String, Integer> counterBackup;

    public Counter() {
        counter = new TreeMap<>();
        counterBackup = new TreeMap<>();
    }

    public Map<String, Integer> getCounter() {
        return counter;
    }

    public Map<String, Integer> getCounterBackup() {
        return counterBackup;
    }

    public void resetCounter() {
        counterBackup.clear();
        counterBackup.putAll(counter);
        counter.clear();
    }

    public void update(Alien a) {
        if (counter.get(a.getType()) != null)
            counter.replace(a.getType(), counter.get(a.getType()) + 1);
        else
            counter.put(a.getType(), 1);
    }

    public void update(Amanita a) {
        if (counter.get(a.getType()) != null)
            counter.replace(a.getType(), counter.get(a.getType()) + 1);
        else
            counter.put(a.getType(), 1);
    }

    public void update(Dandelion a) {
        if (counter.get(a.getType()) != null)
            counter.replace(a.getType(), counter.get(a.getType()) + 1);
        else
            counter.put(a.getType(), 1);
    }

    public void update(Grass a) {
        if (counter.get(a.getType()) != null)
            counter.replace(a.getType(), counter.get(a.getType()) + 1);
        else
            counter.put(a.getType(), 1);
    }

    public void update(Sheep a) {
        if (counter.get(a.getType()) != null)
            counter.replace(a.getType(), counter.get(a.getType()) + 1);
        else
            counter.put(a.getType(), 1);
    }

    public void update(Wolf a) {
        if (counter.get(a.getType()) != null)
            counter.replace(a.getType(), counter.get(a.getType()) + 1);
        else
            counter.put(a.getType(), 1);
    }
}
