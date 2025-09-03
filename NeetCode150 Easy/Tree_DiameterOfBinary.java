package NeetCode150;

public class Tree_DiameterOfBinary {
    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    int diameter = 0;

    public int diameterOfBinaryTree(Node root) {
        depth(root);
        return diameter;
    }

    int depth(Node root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);
        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        Tree_DiameterOfBinary tree = new Tree_DiameterOfBinary();

        // Creating the tree:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        int result = tree.diameterOfBinaryTree(root);
        System.out.println("Diameter of the binary tree: " + result);
    }
}
