package NeetCode150;

public class Tree_Is_Same_Tree {
    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    boolean isSameTree(Node root1, Node root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

    public static void main(String[] args) {

        Tree_Is_Same_Tree solution = new Tree_Is_Same_Tree();

        Node p = new Node(1);
        p.left = new Node(2);
        p.right = new Node(3);

        // Tree 2 (same as Tree 1)
        Node q = new Node(1);
        q.left = new Node(2);
        q.right = new Node(3);

        System.out.println("Are trees the same? " + solution.isSameTree(p, q)); // true

        // Tree 3 (different structure)
        Node r = new Node(1);
        r.left = new Node(2);

        Node s = new Node(1);
        s.right = new Node(2);

        System.out.println("Are trees the same? " + solution.isSameTree(r, s));
    }
}
