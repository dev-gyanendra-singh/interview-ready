package NeetCode150;

import java.util.*;

public class ArrayAndHashing_GroupAnagrams {
    public static void main(String[] args) {
        String[] arrIndexes = {"cat", "tac", "seek", "deep"};
        System.out.println(new ArrayAndHashing_GroupAnagrams().groupAnagrams(arrIndexes));

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());

    }
}
