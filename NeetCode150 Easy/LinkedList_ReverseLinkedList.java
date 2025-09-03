package NeetCode150;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedList_ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode tempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNode;
        }
        return prev;
    }

    // Helper method to print the list
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    // Helper method to create a linked list from an array
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

    public static void main(String[] args) {
        LinkedList_ReverseLinkedList linkedList = new LinkedList_ReverseLinkedList();

        // Create list: 1 -> 2 -> 3 -> 4 -> 5
        int[] vals = {1, 2, 3, 4, 5};
        ListNode head = linkedList.createList(vals);

        System.out.print("Original list: ");
        linkedList.printList(head);

        // Reverse the list
        ListNode reversedHead = linkedList.reverseList(head);

        System.out.print("Reversed list: ");
        linkedList.printList(reversedHead);
    }
}
