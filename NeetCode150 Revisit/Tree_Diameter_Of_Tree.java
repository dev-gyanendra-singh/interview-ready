package ReviseAgain;

public class Tree_Diameter_Of_Tree {

    private int diameter;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    int diameter(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        diameter = Math.max(left + right, diameter);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        // Create nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Create solution object and call the method
        Tree_Diameter_Of_Tree sol = new Tree_Diameter_Of_Tree();
        int dia = sol.diameter(root);

        System.out.println("Diameter: " + dia); // Expected: 3
    }

}
