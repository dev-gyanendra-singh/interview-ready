package ReviseAgain;

public class Tree_Serialize_And_Deserialize {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }

        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        return deserializeHelper(nodes);
    }

    int index = 0;

    private TreeNode deserializeHelper(String[] nodes) {
        if (index >= nodes.length || nodes[index].equals("null")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodes[index++]));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);

        return node;

    }

    public static void main(String[] args) {
        Tree_Serialize_And_Deserialize s = new Tree_Serialize_And_Deserialize();

        // Build tree: [1, 2, null, null, 3, 4, null, null, 5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = s.serialize(root);
        System.out.println("Serialized: " + serialized);

        TreeNode deserializedRoot = s.deserialize(serialized);
        System.out.println("Deserialized Root: " + deserializedRoot.val); // should be 1
    }

}
