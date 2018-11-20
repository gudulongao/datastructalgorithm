package demo.tree.redblacktree;

import demo.tree.bean.Node;
import demo.tree.bean.Tree;
import demo.tree.itf.NodeType;

/**
 * 红黑树
 *
 * @param <T> 可排序的数据结构
 */
public class RedBlackTree<T extends Comparable<T>> extends Tree<T> {

    /**
     * 左旋
     *
     * @param node 当前节点
     */
    private void leftRotate(RedBlackNode<T> node) throws Exception {
        RedBlackNode<T> parent = node.getParent();
        NodeType nodeType = node.nodeType();
        //后继节点（当前节点的右子节点）
        RedBlackNode<T> replaceNode = node.getRigthChild();
        //处理新老节点的子节点关系（新左=老右）
        node.setRigthChild(replaceNode.getLeftChild());
        if (replaceNode.getLeftChild() != null) {
            replaceNode.getLeftChild().setParent(node);
        }
        //处理后继节点与父级节点的关系（新父=老父）
        replaceNode.setParent(parent);
        if (NodeType.LEFT == nodeType) {
            parent.setLeftChild(replaceNode);
        } else {
            parent.setRigthChild(replaceNode);
        }
        //处理新老节点的关系（老=新左）
        replaceNode.setLeftChild(node);
        node.setParent(replaceNode);
    }

    /**
     * 右旋
     *
     * @param node 当前节点
     */
    private void rightRotate(RedBlackNode<T> node) throws Exception {
        RedBlackNode<T> parent = node.getParent();
        NodeType nodeType = node.nodeType();
        //后继节点（当前节点的左子节点）
        RedBlackNode<T> replaceNode = node.getLeftChild();
        //处理新老节点的子节点关系（新右=老左）
        node.setLeftChild(replaceNode.getRigthChild());
        if (replaceNode.getRigthChild() != null) {
            replaceNode.getRigthChild().setParent(node);
        }
        //处理新节点与父节点的关系（新父=老父）
        replaceNode.setParent(parent);
        if (NodeType.LEFT == nodeType) {
            parent.setLeftChild(replaceNode);
        } else {
            parent.setRigthChild(replaceNode);
        }
        //处理新老节点的关系（新右=老）
        replaceNode.setRigthChild(node);
        node.setParent(replaceNode);
    }

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
