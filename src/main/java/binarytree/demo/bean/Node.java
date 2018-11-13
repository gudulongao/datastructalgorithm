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
    public ChildType getChildType() {
        if (leftChild != null && rigthChild != null) {
            return ChildType.BOTH;
        } else if (leftChild != null && rigthChild == null) {
            return ChildType.LEFT;
        } else if (leftChild == null && rigthChild != null) {
            return ChildType.RIGHT;
        } else {
            return ChildType.NONE;
        }
    }

    /**
     * 子节点个数
     *
     * @return
     */
    public int directChildSize() {
        ChildType type = getChildType();
        if (ChildType.NONE == type) {
            return 0;
        } else if (ChildType.BOTH == type) {
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
