package demo.tree.redblacktree;

import demo.tree.bean.Node;
import demo.tree.bean.Tree;

/**
 * 红黑树
 *
 * @param <T> 可排序的数据结构
 */
public class RedBlackTree<T extends Comparable<T>> extends Tree<T> {
    @Override
    public void insert(Node<T> newNode) throws Exception {
    }

    public void insert(T key) throws Exception {
        insert(new RedBlackNode<T>(key, null));
    }

    @Override
    public boolean delete(Node<T> node) throws Exception {
        return false;
    }

    public boolean delete(T key) throws Exception {
        return delete(new RedBlackNode<T>(key, null));
    }

    public RedBlackTree(T key) {
        super(new RedBlackNode<T>(key, null));
    }

    public RedBlackTree() {
        super();
    }
}
