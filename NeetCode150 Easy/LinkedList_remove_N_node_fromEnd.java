package NeetCode150;

public class LinkedList_remove_N_node_fromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }

// Utility method to print the list
public static void printList(ListNode head) {
    ListNode curr = head;
    while (curr != null) {
        System.out.print(curr.val + " ");
        curr = curr.next;
    }
    System.out.println();
}

public static void main(String[] args) {
    // Create a list: 1 -> 2 -> 3 -> 4 -> 5
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    System.out.println("Original list:");
    printList(head);

    int n = 2; // Remove the 2nd node from the end (should remove '4')
    LinkedList_remove_N_node_fromEnd solution = new LinkedList_remove_N_node_fromEnd();
    head = solution.removeNthFromEnd(head, n);

    System.out.println("Modified list after removing " + n + "th node from end:");
    printList(head);
}
}
