package NeetCode150;

public class LinkedList_MergeTwoSortedLinkedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) tail.next = list1;
        if (list2 != null) tail.next = list2;
        return dummy.next;
    }

    // Helper method to create a linked list from array
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

    // Helper method to print linked list
    public void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList_MergeTwoSortedLinkedList solution = new LinkedList_MergeTwoSortedLinkedList();

        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};

        ListNode list1 = solution.createList(arr1);
        ListNode list2 = solution.createList(arr2);

        System.out.print("List1: ");
        solution.printList(list1);
        System.out.print("List2: ");
        solution.printList(list2);

        ListNode mergedHead = solution.mergeTwoLists(list1, list2);
        System.out.print("Merged List: ");
        solution.printList(mergedHead);
    }
}
