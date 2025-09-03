package NeetCode150;

public class Tree_max_height_binary_tree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public int maxDepth(TreeNode root) {

        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        Tree_max_height_binary_tree solution = new Tree_max_height_binary_tree();
        System.out.print("Original tree (in-order): ");
        solution.printInOrder(root);
        System.out.println();

        // Invert the tree
        int maxDepth = solution.maxDepth(root);

        System.out.println("Max depth of tree is " + maxDepth);

    }
}
