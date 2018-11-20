package demo.tree.redblacktree;

import demo.tree.bean.Node;

/**
 * 红黑树节点
 *
 * @param <T> 可排序的数据结构
 */
public class RedBlackNode<T extends Comparable<T>> extends Node<T> {
    private RedBlackNode<T> parent;
    private ColourType colour;

    public RedBlackNode(T key, RedBlackNode<T> parent) {
        super(key);
        this.parent = parent;
    }

    public RedBlackNode() {
    }

    @Override
    public RedBlackNode<T> getLeftChild() {
        return (RedBlackNode<T>) super.getLeftChild();
    }

    public void setLeftChild(RedBlackNode<T> leftChild) {
        super.setLeftChild(leftChild);
    }

    @Override
    public RedBlackNode<T> getRigthChild() {
        return (RedBlackNode<T>) super.getRigthChild();
    }

    public void setRigthChild(RedBlackNode<T> rigthChild) {
        super.setRigthChild(rigthChild);
    }

    public RedBlackNode<T> getParent() {
        return parent;
    }

    public void setParent(RedBlackNode<T> parent) {
        this.parent = parent;
    }

    public ColourType getColour() {
        return colour;
    }

    public void setColour(ColourType colour) {
        this.colour = colour;
    }
}
