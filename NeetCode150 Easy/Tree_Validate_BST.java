package NeetCode150;

public class Tree_Validate_BST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }

    boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MIN_VALUE);
    }

    boolean dfs(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min && root.val >= max) {
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

    public static void main(String[] args) {
        Tree_Validate_BST s = new Tree_Validate_BST();

        // Tree: [5,1,7,null,null,6,8]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        boolean result = s.isValidBST(root);
        System.out.println("Is valid BST? " + result); // Expected: true
    }
}
