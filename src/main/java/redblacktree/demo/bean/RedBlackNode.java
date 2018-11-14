package redblacktree.demo.bean;

import binarytree.demo.bean.ChildType;

/**
 * 红黑树节点
 */
public class RedBlackNode<T extends Comparable<T>> {
    /**
     * 关键值
     */
    private T key;
    /**
     * 左子节点
     */
    private RedBlackNode<T> leftChild;
    /**
     * 右子节点
     */
    private RedBlackNode<T> rightChild;
    /**
     * 父节点
     */
    private RedBlackNode<T> parent;
    /**
     * 颜色（默认是黑色）
     */
    private Color color = Color.RED;

    public RedBlackNode(T key, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild, RedBlackNode<T> parent, Color color) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        this.color = color;
    }

    public RedBlackNode() {
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public RedBlackNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RedBlackNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public RedBlackNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(RedBlackNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public RedBlackNode<T> getParent() {
        return parent;
    }

    public void setParent(RedBlackNode<T> parent) {
        this.parent = parent;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ChildType getNodeType() {
        if (parent == null) {
            return ChildType.NONE;
        }
        return parent.getLeftChild() == this ? ChildType.LEFT : ChildType.RIGHT;
    }

    @Override
    public String toString() {
        return "{key=" + key + ",color=" + color.getValue() + "}";
    }
}
