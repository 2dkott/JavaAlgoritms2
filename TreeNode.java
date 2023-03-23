public class TreeNode {
    int data;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    private TreeColor color;

    public int getData() {
        return data;
    }

    public TreeNode getParent() {
        return parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeColor getColor() {
        return color;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setColor(TreeColor color) {
        this.color = color;
    }
}
