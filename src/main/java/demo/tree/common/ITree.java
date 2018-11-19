package demo.tree.common;

/**
 * 树的定义
 */
public interface ITree {
    /**
     * 先序遍历
     */
    void preIterator() throws Exception;

    /**
     * 中序遍历
     */
    void midIterator() throws Exception;

    /**
     * 后序遍历
     */
    void postIterator() throws Exception;

    /**
     * 层序遍历
     */
    void levelIterator() throws Exception;
}
