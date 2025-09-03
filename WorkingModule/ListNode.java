package WorkingModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }

    static public void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        list.add(3);

        Integer [] arr = list.toArray(new Integer[list.size()]);

        for (Integer i : arr) {
            System.out.println("X " + i);
        }

    }
}
