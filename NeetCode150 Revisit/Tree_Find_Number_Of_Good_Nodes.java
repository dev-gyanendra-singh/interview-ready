package ReviseAgain;

public class Tree_Find_Number_Of_Good_Nodes {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode root, int maxSoFar) {
        if (root == null) return 0;
        int count  = 0;

        if(root.val >= maxSoFar) {
            count = 1;
        }

        maxSoFar = Math.max(maxSoFar, root.val);

        count += dfs(root.left, maxSoFar);
        count += dfs(root.right, maxSoFar);

        return count;

    }

    public static void main(String[] args) {
        Tree_Find_Number_Of_Good_Nodes s = new Tree_Find_Number_Of_Good_Nodes();

        // Tree: [3,1,4,3,null,1,5]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);

        int result = s.goodNodes(root);
        System.out.println("Good Nodes Count: " + result); // Expected: 4
    }
}
