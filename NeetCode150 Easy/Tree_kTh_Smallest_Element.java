package NeetCode150;

public class Tree_kTh_Smallest_Element {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }
    private int count = 0;
    int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    public void inorder(TreeNode root, int k) {
        if (root == null) return;
        inorder(root.left, k);

        count++;
        if (count == k) {
            result = root.val;
            return; // Optional: stop early
        }

        inorder(root.right, k);
    }

    public static void main(String[] args) {
        Tree_kTh_Smallest_Element s = new Tree_kTh_Smallest_Element();

        /*
            Tree:
                 5
                / \
               3   6
              / \
             2   4
            /
           1
        */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        int k = 3;
        int res = s.kthSmallest(root, k);
        System.out.println("Kth smallest element: " + res); // Expected: 3
    }


}
