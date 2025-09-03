package ReviseAgain;

public class Tree_Balanced_Binary_Tree {

    static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(Node root) {

        if (root == null) {
            return 0;
        }

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        Tree_Balanced_Binary_Tree tree = new Tree_Balanced_Binary_Tree();

        // Balanced Tree
        Node root1 = new Node(3);
        root1.left = new Node(9);
        root1.right = new Node(20);
        root1.right.left = new Node(15);
        root1.right.right = new Node(7);

        System.out.println("Is Balanced Tree 1? " + tree.isBalanced(root1)); // true

        // Unbalanced Tree
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.left.left = new Node(3);

        System.out.println("Is Balanced Tree 2? " + tree.isBalanced(root2)); // false
    }
}
