package redblacktree.demo;

import binarytree.demo.bean.Node;

/**
 * 红黑树节点
 */
public class RedBlackNode extends Node {
    private boolean isRed = true;

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public RedBlackNode(int data, boolean isRed) {
        super(data);
        this.isRed = isRed;
    }

    public RedBlackNode() {
    }
}
