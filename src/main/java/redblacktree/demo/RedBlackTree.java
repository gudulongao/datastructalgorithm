package redblacktree.demo;

import redblacktree.demo.bean.RedBlackNode;

/**
 * 红黑树
 */
public class RedBlackTree<T extends Comparable<T>> {
    /**
     * 根节点
     */
    private RedBlackNode<T> root;

    /**
     * 左旋示意图：对节点x进行左旋
     * p                       p
     * /                       /
     * x                       y
     * / \                     / \
     * lx  y      ----->       x  ry
     * / \                 / \
     * ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     *
     * @param currRoot 当前子树的根节点
     */
    private void leftRotate(RedBlackNode<T> currRoot) {
        RedBlackNode<T> parent = currRoot.getParent();
        RedBlackNode<T> rightChild = currRoot.getRightChild();
        //处理当前根与新根节点子树的关系
        if (rightChild.getLeftChild() != null) {
            rightChild.getLeftChild().setParent(currRoot);
            currRoot.setRightChild(rightChild.getLeftChild());
        }
        //处理当前节点为根时，左旋后新的根节点为根
        if (parent == null) {
            root = rightChild;
        } else {
            //处理新根与父级节点的关系
            rightChild.setParent(parent);
            if (parent.getLeftChild() == currRoot) {
                parent.setLeftChild(rightChild);
            } else {
                parent.setRightChild(rightChild);
            }
            //处理新根与当前根的关系
            rightChild.setLeftChild(currRoot);
            currRoot.setParent(rightChild);
        }
    }

    /**
     * 左旋示意图：对节点y进行右旋
     * p                   p
     * /                   /
     * y                   x
     * / \                 / \
     * x  ry   ----->      lx  y
     * / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     *
     * @param currRoot 当前子树的根节点
     */
    private void rigthRotate(RedBlackNode<T> currRoot) {
        RedBlackNode<T> leftChild = currRoot.getLeftChild();
        RedBlackNode<T> parent = currRoot.getParent();
        //处理新根与当前根子节点的关系
        if (leftChild.getRightChild() != null) {
            leftChild.getRightChild().setParent(currRoot);
            currRoot.setLeftChild(leftChild);
        }
        //处理当前根为根节点的情况
        if (parent == null) {
            root = leftChild;
        } else {
            //处理新根与父节点的关系
            leftChild.setParent(parent);
            if (parent.getLeftChild() == currRoot) {
                parent.setLeftChild(leftChild);
            } else {
                parent.setRightChild(leftChild);
            }
            //处理新根与当前根的关系
            leftChild.setRightChild(currRoot);
            currRoot.setParent(leftChild);
        }
    }

    /**
     * 查找可以作为新节点父节点的节点
     *
     * @param newNode 新节点
     * @return 节点
     */
    private RedBlackNode<T> findInsertAfterNode(RedBlackNode<T> newNode) {
        if (root == null) {
            root = newNode;
            return null;
        }
        RedBlackNode<T> curr = root;
        while (curr != null) {
            int compareResult = newNode.getKey().compareTo(curr.getKey());
            if (compareResult > 0) {
                curr = curr.getRightChild();
            } else {
                curr = curr.getLeftChild();
            }
        }
        return curr;
    }

    /**
     * 新增
     *
     * @param newNode 新节点
     */
    public void insert(RedBlackNode<T> newNode) {
        if (newNode == null || newNode.getKey() == null) {
            return;
        }
        //可作为新节点父的节点
        RedBlackNode<T> parent = findInsertAfterNode(newNode);
        //新节点为红色
        newNode.setColor(RedBlackNode.Color.RED);
        //新节点作为父的子节点
        newNode.setParent(parent);
        int compareResult = newNode.getKey().compareTo(parent.getKey());
        if (compareResult > 0) {
            parent.setRightChild(newNode);
        } else {
            parent.setLeftChild(newNode);
        }
        //修正
        fixInsert(newNode);
    }

    /**
     * 新增后修正
     *
     * @param newNode 新节点
     */
    private void fixInsert(RedBlackNode<T> newNode) {

    }
}
