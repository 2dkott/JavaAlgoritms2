import org.w3c.dom.Node;

public class RedBlackTree {
    TreeNode root;

    private void rotateRight(TreeNode node) {
        TreeNode parent = node.getParent();
        TreeNode leftChild = node.getLeft();

        node.setLeft(leftChild.getRight());
        if (leftChild.getRight() != null) {
            leftChild.getRight().setParent(node);
        }

        leftChild.setRight(node);
        node.setParent(leftChild);
        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(TreeNode node) {
        TreeNode parent = node.getParent();
        TreeNode rightChild = node.getRight();

        node.setRight(rightChild.getRight());
        if (rightChild.getLeft() != null) {
            rightChild.getLeft().setParent(node);
        }

        rightChild.setLeft(node);
        node.setParent(rightChild);

        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(TreeNode parent, TreeNode oldChild, TreeNode newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.getLeft() == oldChild) {
            parent.setLeft(newChild);
        } else if (parent.getRight() == oldChild) {
            parent.setRight(newChild);
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.setParent(parent);
        }
    }

    public void insertNode(int key) {
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.getData()) {
                node = node.getLeft();
            } else if (key > node.getData()) {
                node = node.getRight();
            } else {
                throw new IllegalArgumentException("BST already contains a node with key " + key);
            }
        }

        TreeNode newNode = new TreeNode();
        newNode.setData(key);
        newNode.setColor(TreeColor.RED);
        if (parent == null) {
            root = newNode;
        } else if (key < parent.getData()) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        newNode.setParent(parent);

        fixRedBlackPropertiesAfterInsert(newNode);
    }

    private void fixRedBlackPropertiesAfterInsert(TreeNode node) {
        TreeNode parent = node.getParent();
        if (parent == null) {
            return;
        }

        if (parent.getColor() == TreeColor.BLACK) {
            return;
        }

        TreeNode grandparent = parent.getParent();
        if (grandparent == null) {
            parent.setColor(TreeColor.BLACK);
            return;
        }

        TreeNode uncle = getUncle(parent);

        if (uncle != null && uncle.getColor() == TreeColor.RED) {
            parent.setColor(TreeColor.BLACK);
            grandparent.setColor(TreeColor.RED);
            uncle.setColor(TreeColor.BLACK);
            fixRedBlackPropertiesAfterInsert(grandparent);
        }

        else if (parent == grandparent.getLeft()) {
            if (node == parent.getRight()) {
                rotateLeft(parent);
                parent = node;
            }

            rotateRight(grandparent);

            parent.setColor(TreeColor.BLACK);
            grandparent.setColor(TreeColor.RED);
        }

        else {
            if (node == parent.getLeft()) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.setColor(TreeColor.BLACK);
            grandparent.setColor(TreeColor.RED);
        }
    }

    private TreeNode getUncle(TreeNode parent) {
        TreeNode grandparent = parent.getParent();
        if (grandparent.getLeft() == parent) {
            return grandparent.getRight();
        } else if (grandparent.getRight() == parent) {
            return grandparent.getLeft();
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

    private void printHelper(TreeNode root, String indent, boolean last) {
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.getColor() == TreeColor.RED?"RED":"BLACK";
            System.out.println(root.getData() + "(" + sColor + ")");
            printHelper(root.getLeft(), indent, false);
            printHelper(root.getRight(), indent, true);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        appendNodeToStringRecursive(root, builder);
        return builder.toString();
    }

    private void appendNodeToStringRecursive(TreeNode node, StringBuilder builder) {
        appendNodeToString(node, builder);
        if (node.getLeft() != null) {
            builder.append(" L{");
            appendNodeToStringRecursive(node.getLeft(), builder);
            builder.append('}');
        }
        if (node.getRight() != null) {
            builder.append(" R{");
            appendNodeToStringRecursive(node.getRight(), builder);
            builder.append('}');
        }
    }

    protected void appendNodeToString(TreeNode node, StringBuilder builder) {
        builder.append(node.getData());
    }
}
