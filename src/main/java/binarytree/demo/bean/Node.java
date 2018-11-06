package binarytree.demo.bean;

public class Node {
    private int data;
    private Node leftChild;
    private Node rigthChild;

    public Node(int data) {
        this.data = data;
    }

    public Node() {
    }

    /**
     * 判断是否有子树
     * @return 判断结果
     */
    public boolean hasChild() {
        return !(leftChild == null && rigthChild == null);
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRigthChild() {
        return rigthChild;
    }

    public void setRigthChild(Node rigthChild) {
        this.rigthChild = rigthChild;
    }
}
