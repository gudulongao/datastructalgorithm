package demo.tree.bean;

import demo.tree.itf.ITree;
import demo.tree.itf.NodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * 树基类
 */
public abstract class Tree<T extends Comparable<T>> implements ITree {
    protected Node<T> root;
    protected int size = 0;

    public int size() {
        return size;
    }

    /**
     * 获取根节点
     */
    protected Node<T> getRoot() {
        return root;
    }

    /**
     * 获取宽度
     */
    public int width() {
        if (root == null) {
            return 0;
        }
        List<Node<T>> list = new ArrayList<Node<T>>();
        list.add(root);
        return width(list, 1);
    }

    private int width(List<Node<T>> list, int maxWidth) {
        List<Node<T>> childList = new ArrayList<Node<T>>();
        for (Node<T> node : list) {
            if (node.getLeftChild() != null) {
                childList.add(node.getLeftChild());
            }
            if (node.getRigthChild() != null) {
                childList.add(node.getRigthChild());
            }
        }
        if (childList.isEmpty()) {
            return maxWidth;
        }
        return width(childList, childList.size() > maxWidth ? childList.size() : maxWidth);
    }

    /**
     * 获取指定关键词的深度
     *
     * @param key 指定关键词
     */
    public int deep(T key) throws Exception {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        validateEmptyTree();
        Node<T> curr = root, target = null;
        int compareResult, deep = 0;
        while (curr != null) {
            compareResult = key.compareTo(curr.getKey());
            if (compareResult == 0) {
                target = curr;
                break;
            } else {
                deep++;
                if (compareResult < 0) {
                    curr = curr.getLeftChild();
                } else {
                    curr = curr.getRigthChild();
                }
            }
        }
        if (target == null) {
            throw new Exception("could not find " + key);
        }
        return deep;
    }

    /**
     * 获取指定关键词的高度
     *
     * @param key 指定关键词
     */
    public int height(T key) throws Exception {
        validateEmptyTree();
        Node<T> targetNode = findTargetNode(key);
        return getHeight(targetNode);
    }

    /**
     * 查找目标节点
     *
     * @param key 指定关键词
     */
    private Node<T> findTargetNode(T key) throws Exception {
        Node<T> curr = root, target = null;
        int compareResult;
        while (curr != null) {
            compareResult = key.compareTo(curr.getKey());
            if (compareResult == 0) {
                target = curr;
                break;
            } else if (compareResult < 0) {
                curr = curr.getLeftChild();
            } else {
                curr = curr.getRigthChild();
            }
        }
        if (target == null) {
            throw new Exception("could not find " + key);
        }
        return target;
    }

    /**
     * 获取指定节点的高度
     *
     * @param node 指定节点
     */
    private int getHeight(Node<T> node) throws Exception {
        NodeType childType = node.childType();
        if (NodeType.NONE == childType) {
            return 0;
        } else {
            int leftH = 0, rightH = 0;
            if (node.getLeftChild() != null) {
                leftH = getHeight(node.getLeftChild());
            }
            if (node.getRigthChild() != null) {
                rightH = getHeight(node.getRigthChild());
            }
            return leftH > rightH ? (leftH + 1) : (rightH + 1);
        }
    }

    /**
     * 插入
     *
     * @param newKey 新关键词
     */
    public abstract void insert(T newKey) throws Exception;

    /**
     * 删除节点
     *
     * @param key 目标关键词
     * @return 删除结果
     */
    public abstract boolean delete(T key) throws Exception;

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
