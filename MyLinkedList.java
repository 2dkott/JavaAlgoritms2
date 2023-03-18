import java.util.Objects;

public class MyLinkedList {

    private LinkedListNode parent;
    private LinkedListNode currentNode = null;

    public MyLinkedList(){

    }

    public void add(Object value) {
        if(Objects.isNull(currentNode)) {
            parent = new LinkedListNode(parent, null, value);
            currentNode = parent;
        } else {
            LinkedListNode temp = new LinkedListNode(currentNode, null, value);
            currentNode.setChild(temp);
            currentNode = temp;
        }
    }

    public void reverse() {
        LinkedListNode node = parent;
        while(Objects.nonNull(node)){
            LinkedListNode child = node.getChild();
            if (Objects.isNull(child)){
                node.setChild(node.getParent());
                node.setParent(node);
                parent.setChild(null);
                parent = node;
            } else {
                node.setChild(node.getParent());
                node.setParent(child);
            }
            node = child;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        LinkedListNode node = parent;
        stringBuilder.append("[");

        while (Objects.nonNull(node)){
            stringBuilder.append(node.getValue().toString());
            stringBuilder.append(",");
            node = node.getChild();
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }



}
