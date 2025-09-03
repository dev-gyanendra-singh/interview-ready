package NeetCode150;

public class LinkedList_CycleDetection {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // cycle detected
            }
        }

        return false; // no cycle
    }

    // Helper method to create a list from array (no cycle)
    public ListNode createList(int[] vals) {
        if (vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode current = head;
        for (int i = 1; i < vals.length; i++) {
            current.next = new ListNode(vals[i]);
            current = current.next;
        }
        return head;
    }

    // Helper method to create a cycle at given index
    public void createCycle(ListNode head, int index) {
        if (index < 0) return;

        ListNode tail = head;
        ListNode cycleNode = null;
        int pos = 0;
        while (tail.next != null) {
            if (pos == index) {
                cycleNode = tail;
            }
            tail = tail.next;
            pos++;
        }
        // Set tail.next to cycleNode to form a cycle
        tail.next = cycleNode;
    }

    public static void main(String[] args) {
        LinkedList_CycleDetection solution = new LinkedList_CycleDetection();

        // Test 1: List with no cycle
        int[] vals1 = {3, 2, 0, -4};
        ListNode list1 = solution.createList(vals1);
        System.out.println("Test 1 (No Cycle): " + solution.hasCycle(list1)); // false

        // Test 2: List with a cycle at index 1 (value 2)
        int[] vals2 = {3, 2, 0, -4};
        ListNode list2 = solution.createList(vals2);
        solution.createCycle(list2, 1); // Create cycle
        System.out.println("Test 2 (Cycle at index 1): " + solution.hasCycle(list2)); // true

        // Test 3: Single node with no cycle
        ListNode single = new ListNode(1);
        System.out.println("Test 3 (Single Node, No Cycle): " + solution.hasCycle(single)); // false

        // Test 4: Single node with cycle to itself
        ListNode selfCycle = new ListNode(1);
        selfCycle.next = selfCycle;
        System.out.println("Test 4 (Single Node, Self Cycle): " + solution.hasCycle(selfCycle)); // true
    }
}

