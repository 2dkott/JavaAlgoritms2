public class LinkedListNode {
    LinkedListNode parent;
    LinkedListNode child;
    Object value;

    public LinkedListNode(LinkedListNode parent, LinkedListNode chiled, Object value) {
        this.parent = parent;
        this.child = chiled;
        this.value = value;
    }

    public LinkedListNode getParent() {
        return parent;
    }

    public LinkedListNode getChild() {
        return child;
    }

    public Object getValue() {
        return value;
    }

    public void setChild(LinkedListNode child) {
        this.child = child;
    }

    public void setParent(LinkedListNode parent) {
        this.parent = parent;
    }
}
