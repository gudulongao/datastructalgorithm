package demo.tree.bean;


/**
 * 节点
 *
 * @param <T> 可排序的数据结构
 */
public class Node<T extends Comparable<T>> {
    private T key;
    private Node<T> leftChild;
    private Node<T> rigthChild;

    public Node(T key) {
        this.key = key;
    }

    public Node() {
    }

    /**
     * 子节点的类型
     */
    public NodeType childType() {
        if (leftChild == null && rigthChild == null) {
            return NodeType.NONE;
        } else if (leftChild != null && rigthChild != null) {
            return NodeType.BOTH;
        } else if (leftChild != null) {
            return NodeType.LEFT;
        } else {
            return NodeType.RIGHT;
        }
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRigthChild() {
        return rigthChild;
    }

    public void setRigthChild(Node<T> rigthChild) {
        this.rigthChild = rigthChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                '}';
    }

}
