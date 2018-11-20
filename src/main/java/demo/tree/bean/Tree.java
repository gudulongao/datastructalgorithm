package demo.tree.bean;

import demo.tree.common.ITree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树基类
 */
public abstract class Tree<T extends Comparable<T>> implements ITree {
    protected Node<T> root;

    /**
     * 插入
     *
     * @param newNode 新节点
     */
    public abstract void insert(Node<T> newNode) throws Exception;

    /**
     * 删除节点
     *
     * @param node 目标节点
     * @return 删除结果
     */
    public abstract boolean delete(Node<T> node) throws Exception;

    /**
     * 校验是否是空树
     */
    protected void validateEmptyTree() throws Exception {
        if (root == null) {
            throw new Exception("empty tree");
        }
    }

    public void preIterator() throws Exception {
        validateEmptyTree();
        System.out.println("Tree.preIterator: ");
        preIterator(root);
    }

    private void preIterator(Node<T> node) throws Exception {
        System.out.print(node.getKey() + " ");
        if (node.getLeftChild() != null) {
            preIterator(node.getLeftChild());
        }
        if (node.getRigthChild() != null) {
            preIterator(node.getRigthChild());
        }
    }

    public void midIterator() throws Exception {
        validateEmptyTree();
        System.out.println("Tree.midIterator: ");
        midIterator(root);
    }

    private void midIterator(Node<T> node) throws Exception {
        if (node.getLeftChild() != null) {
            midIterator(node.getLeftChild());
        }
        System.out.print(node.getKey() + " ");
        if (node.getRigthChild() != null) {
            midIterator(node.getRigthChild());
        }
    }

    public void postIterator() throws Exception {
        validateEmptyTree();
        System.out.println("Tree.postIterator: ");
        postIterator(root);
    }

    private void postIterator(Node<T> node) throws Exception {
        if (node.getLeftChild() != null) {
            postIterator(node.getLeftChild());
        }
        if (node.getRigthChild() != null) {
            postIterator(node.getRigthChild());
        }
        System.out.print(node.getKey() + " ");
    }

    public void levelIterator() throws Exception {
        validateEmptyTree();
        System.out.println("Tree.levelIterator: ");
        List<Node<T>> list = new ArrayList<Node<T>>();
        list.add(root);
        levelIterator(list);
    }

    private void levelIterator(List<Node<T>> list) throws Exception {
        if (list.isEmpty()) {
            return;
        }
        List<Node<T>> childList = new ArrayList<Node<T>>();
        for (Node<T> node : list) {
            System.out.print(node.getKey() + " ");
            if (node.childType() == NodeType.NONE) {
                continue;
            }
            if (node.getLeftChild() != null) {
                childList.add(node.getLeftChild());
            }
            if (node.getRigthChild() != null) {
                childList.add(node.getRigthChild());
            }
        }
        if (childList.isEmpty()) {
            return;
        }
        levelIterator(childList);
    }

    public Tree(Node<T> root) {
        this.root = root;
    }

    public Tree() {
    }
}
