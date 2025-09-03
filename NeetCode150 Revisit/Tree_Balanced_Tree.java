package ReviseAgain;

public class Tree_Balanced_Tree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return height(root) != -1;
    }

    int height(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = height(root.right);
        if (rightHeight == -1) return -1;
        if(Math.abs(leftHeight - rightHeight) > 1) return -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }
    public static void main(String[] args) {
        // Create nodes
        Tree_Diameter_Of_Tree.TreeNode root = new Tree_Diameter_Of_Tree.TreeNode(1);
        root.left = new Tree_Diameter_Of_Tree.TreeNode(2);
        root.right = new Tree_Diameter_Of_Tree.TreeNode(3);
        root.left.left = new Tree_Diameter_Of_Tree.TreeNode(4);
        root.left.right = new Tree_Diameter_Of_Tree.TreeNode(5);

        // Create solution object and call the method
        Tree_Diameter_Of_Tree sol = new Tree_Diameter_Of_Tree();
        int dia = sol.diameter(root);

        System.out.println("Diameter: " + dia); // Expected: 3
    }


}
