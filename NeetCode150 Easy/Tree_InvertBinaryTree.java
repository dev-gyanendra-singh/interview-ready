package NeetCode150;

public class Tree_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Construct a simple binary tree:
        //       4
        //     /   \
        //    2     7
        //   / \   / \
        //  1   3 6   9

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        Tree_InvertBinaryTree solution = new Tree_InvertBinaryTree();
        System.out.print("Original tree (in-order): ");
        solution.printInOrder(root);
        System.out.println();

        // Invert the tree
        TreeNode invertedRoot = solution.invertTree(root);

        System.out.print("Inverted tree (in-order): ");
        solution.printInOrder(invertedRoot);
        System.out.println();
    }

    public void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }
}
