package NeetCode150;

public class LinkedList_Remove_N_th_From_the_End {
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

    // Helper method to create a linked list from array
    public ListNode createList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    // Helper method to print the linked list
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList_Remove_N_th_From_the_End solution = new LinkedList_Remove_N_th_From_the_End();

        int[] values = {1, 2, 3, 4, 5};
        int n = 2;

        ListNode head = solution.createList(values);
        System.out.print("Original List: ");
        solution.printList(head);

        head = solution.removeNthFromEnd(head, n);
        System.out.print("After Removing " + n + "th Node From End: ");
        solution.printList(head);
    }
}
