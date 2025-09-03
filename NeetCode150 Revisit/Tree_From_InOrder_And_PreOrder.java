package ReviseAgain;

import java.util.HashMap;
import java.util.Map;

public class Tree_From_InOrder_And_PreOrder {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    private Map<Integer, Integer> inorderMap;
    private int preorderIndex = 0;

    TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }

    TreeNode build(int[] preorder, int start, int end) {
        if (start > end) return null;
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        int index = inorderMap.get(rootVal);

        root.left = build(preorder, start, index - 1);
        root.right = build(preorder, index + 1, end);
        return root;


    }


    // For testing: print inorder traversal of tree
    private void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Tree_From_InOrder_And_PreOrder s = new Tree_From_InOrder_And_PreOrder();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder =  {9, 3, 15, 20, 7};

        TreeNode root = s.buildTree(preorder, inorder);

        System.out.print("Inorder of built tree: ");
        s.printInorder(root);  // Should match input inorder array
    }
}
