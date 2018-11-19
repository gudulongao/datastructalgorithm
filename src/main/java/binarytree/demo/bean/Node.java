package binarytree.demo.bean;

public class Node {
    private Integer data = null;
    private Node leftChild = null;
    private Node rigthChild = null;

    public Node(int data) {
        this.data = data;
    }

    public Node() {
    }

    /**
     * 判断是否有子树
     *
     * @return 判断结果
     */
    public boolean hasChild() {
        return !(leftChild == null && rigthChild == null);
    }

    /**
     * 获取子树的类型
     *
     * @return
     */
    public NodeType getChildType() {
        if (leftChild != null && rigthChild != null) {
            return NodeType.BOTH;
        } else if (leftChild != null && rigthChild == null) {
            return NodeType.LEFT;
        } else if (leftChild == null && rigthChild != null) {
            return NodeType.RIGHT;
        } else {
            return NodeType.NONE;
        }
    }

    /**
     * 子节点个数
     *
     * @return
     */
    public int directChildSize() {
        NodeType type = getChildType();
        if (NodeType.NONE == type) {
            return 0;
        } else if (NodeType.BOTH == type) {
            return 2;
        } else {
            return 1;
        }
    }

    public Integer getData() {
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
