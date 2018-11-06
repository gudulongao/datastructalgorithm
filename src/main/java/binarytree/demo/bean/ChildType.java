package binarytree.demo.bean;

/**
 * 子树类型
 */
public enum ChildType {
    /**
     * 没有子树
     */
    NONE,
    /**
     * 满树(既有左子树又有右子树)
     */
    BOTH,
    /**
     * 左子树
     */
    LEFT,
    /**
     * 右子树
     */
    RIGHT
}
