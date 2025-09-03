package NeetCode150;

public class Tree_SubtreeCheck {
    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }

    public boolean checkSubtree(Node root, Node subtree) {
       if( root == null ) return false;
       if(sameTree(root, subtree)) { return true;}
      return checkSubtree(root.left, subtree) || checkSubtree(root.right, subtree);
    }

    private boolean sameTree(Node root, Node subtree) {
        if( root == null && subtree == null ) return true;
        if( root == null || subtree == null ) return false;
        if(root.data != subtree.data) return false;
        return sameTree(root.left, subtree.left) && sameTree(root.right, subtree.right);
    }

    public static void main(String[] args) {
        Tree_SubtreeCheck solution = new Tree_SubtreeCheck();

        // Root tree
        Node root = new Node(3);
        root.left = new Node(4);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(2);

        // Subtree to check
        Node subRoot = new Node(4);
        subRoot.left = new Node(1);
        subRoot.right = new Node(2);

        System.out.println("Is subRoot a subtree of root? " + solution.checkSubtree(root, subRoot));
    }
}
