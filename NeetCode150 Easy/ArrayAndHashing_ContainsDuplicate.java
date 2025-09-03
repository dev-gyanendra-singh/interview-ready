package NeetCode150;

import java.util.HashSet;

public class ArrayAndHashing_ContainsDuplicate {
    public static void main(String[] args) {
        int[] noDuplicateArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] duplicateArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9};
        System.out.println("Is duplicate present: " + new ArrayAndHashing_ContainsDuplicate().hasDuplicate(noDuplicateArr));
        System.out.println("Is duplicate present: " + new ArrayAndHashing_ContainsDuplicate().hasDuplicate(duplicateArr));
    }

    public boolean hasDuplicate(int[] nums) {
        var vis = new HashSet<>(); //
        for (int num : nums) {
            if (vis.contains(num)) {
                return true;
            } else {
                vis.add(num);
            }
        }
        return false;
    }
}
