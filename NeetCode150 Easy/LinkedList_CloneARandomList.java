package NeetCode150;

import java.util.*;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class LinkedList_CloneARandomList {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node copy = map.get(cur);
            copy.next = map.get(cur.next);
            copy.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // Helper to print a list (val and random's val or null)
    private static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            int randomVal = temp.random != null ? temp.random.val : -1;
            System.out.println("Node val: " + temp.val + ", Random val: " + randomVal);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        // Create nodes
        Node n1 = new Node(3);
        Node n2 = new Node(7);
        Node n3 = new Node(4);
        Node n4 = new Node(5);

        // Link next pointers
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        // Link random pointers
        n1.random = null;
        n2.random = n4; // index 3
        n3.random = n1; // index 0
        n4.random = n2; // index 1

        LinkedList_CloneARandomList sol = new LinkedList_CloneARandomList();
        Node copiedHead = sol.copyRandomList(n1);

        System.out.println("Original list:");
        printList(n1);

        System.out.println("\nCopied list:");
        printList(copiedHead);
    }
}

