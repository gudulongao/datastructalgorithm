package redblacktree.demo;

import binarytree.demo.bean.NodeType;
import redblacktree.demo.bean.Color;
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
        newNode.setColor(Color.RED);
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
        if (root == newNode) {
            return;
        }
        //获取新节点的父节点
        RedBlackNode<T> parent = newNode.getParent();
        //父节点为黑色，则无需修正
        if (Color.BLACK == parent.getColor()) {
            return;
        }
        //获取新节点的祖父节点
        RedBlackNode<T> grandpa = parent.getParent();
        if (grandpa == null) {
            return;
        }
        //获取新节点的叔叔节点
        RedBlackNode<T> uncle = parent == grandpa.getLeftChild() ? grandpa.getRightChild() : grandpa.getLeftChild();
        //新节点是父节点的子节点类型
        NodeType newNodeType = newNode.getNodeType();
        //父节点是祖父节点的子节点类型
        NodeType parentType = parent.getNodeType();
        //父节点以及叔叔节点都是红色
        if ((Color.RED == parent.getColor()) && (Color.RED == uncle.getColor())) {
            //父节点以及叔叔节点都变成黑色
            parent.setColor(Color.BLACK);
            uncle.setColor(Color.BLACK);
            //如果祖父节点不是根节点，则将祖父节点变成红色，同时以祖父节点开始修正
            if (root != grandpa) {
                grandpa.setColor(Color.RED);
            }
            fixInsert(grandpa);
        }
        //父节点和新节点都是左子节点类型
        else if (NodeType.LEFT == parentType && NodeType.LEFT == newNodeType) {
            //以父节点进行右旋
            rigthRotate(parent);
            fixInsert(parent);
        }
        //父节点和新节点都是右子节点类型
        else if (NodeType.RIGHT == parentType && NodeType.RIGHT == newNodeType) {
            //以父节点进行左旋
            leftRotate(parent);
            fixInsert(parent);
        }
        //父节点是左子节点，新节点是右子节点
        else if (NodeType.LEFT == parentType && NodeType.RIGHT == newNodeType) {
            //以新节点进行左旋
            leftRotate(newNode);
            //以新节点进行右旋
            rigthRotate(newNode);
            fixInsert(parent);
        }
        //父节点是右子节点，新节点是左子节点
        else if (NodeType.RIGHT == parentType && NodeType.LEFT == newNodeType) {
            //以新节点进行右旋
            rigthRotate(newNode);
            //以新节点进行左旋
            leftRotate(newNode);
            fixInsert(parent);
        }
    }
}
