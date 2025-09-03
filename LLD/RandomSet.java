package LLD;

import java.util.*;

public class RandomSet {
    Random rand;
    List<Integer> list;
    Map<Integer, Integer> map;

    public RandomSet() {
        rand = new Random();
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    boolean delete(int key) {
        if (!map.containsKey(key)) {
            return false;
        }

        int indexToRemove = map.get(key);
        list.add(indexToRemove, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        map.remove(key);
        return true;
    }

    boolean insert(int value) {
        if (map.containsKey(value)) {
            return false;
        }
        list.add(value);
        map.put(value, list.size() - 1);
        return true;
    }


    int getRandom() {
        int randomIndex = rand.nextInt(list.size());
        return list.get(randomIndex);
    }
}
