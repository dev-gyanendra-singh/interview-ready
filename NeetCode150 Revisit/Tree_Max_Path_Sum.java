package ReviseAgain;

public class Tree_Max_Path_Sum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        maxSum = Math.max(maxSum, root.val + left + right);
        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        Tree_Max_Path_Sum s = new Tree_Max_Path_Sum();

        /*
            Tree:
                -10
                /  \
               9   20
                   / \
                  15  7
        */

        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int result = s.maxPathSum(root);
        System.out.println("Max path sum: " + result); // Expected: 42
    }

}
