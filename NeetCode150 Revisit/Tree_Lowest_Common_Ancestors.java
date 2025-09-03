package ReviseAgain;

public class Tree_Lowest_Common_Ancestors {
    static class Node {
        int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {

        // koi bhi node root node h to
        if (root == null || root == p || root == q) {
            return root; // base case: found one of the nodes or null
        }

        // Recurse left and right
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root; // both nodes found below → current node is LCA... maybe dusra node upar kisi root k right me hoga
        }

        return (left != null) ? left : right; // return the non-null one (if any)
    }

    public static void main (String[] args) {
        Tree_Lowest_Common_Ancestors solution = new Tree_Lowest_Common_Ancestors();

        // Constructing the tree:
        //         6
        //        / \
        //       2   8
        //      / \ / \
        //     0  4 7  9
        //       / \
        //      3   5

        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        root.left.left = new Node(0);
        root.left.right = new Node(4);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        Node p = root.left;           // Node 2
        Node q = root.right;          // Node 8

        Node lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + " is: " + lca.val); // Output: 6

        Node p2 = root.left;           // Node 2
        Node q2 = root.left.right;     // Node 4

        Node lca2 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("LCA of " + p2.val + " and " + q2.val + " is: " + lca2.val); // Output: 2
    }
}
