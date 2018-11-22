package demo.tree.redblacktree;

import demo.tree.bean.Tree;
import demo.tree.itf.NodeType;

/**
 * 红黑树
 *
 * @param <T> 可排序的数据结构
 */
public class RedBlackTree<T extends Comparable<T>> extends Tree<T> {

    @Override
    protected RedBlackNode<T> getRoot() {
        return root == null ? null : (RedBlackNode<T>) root;
    }

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
    public void insert(T newKey) throws Exception {
        if (newKey == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = new RedBlackNode<T>(newKey, null, ColourType.BLACK);
            return;
        }
        RedBlackNode<T> newNode = addNewNode(newKey);
        insertFix(newNode);
    }

    /**
     * 添加新节点
     *
     * @param newKey 新关键词
     */
    private RedBlackNode<T> addNewNode(T newKey) {
        RedBlackNode<T> newNode = new RedBlackNode<T>(newKey, null, ColourType.RED);
        RedBlackNode<T> curr = getRoot();
        int compareFlag;
        NodeType nodeType = null;
        //遍历树，找到可以存储新关键词的位置
        while (curr != null) {
            compareFlag = newKey.compareTo(curr.getKey());
            if (compareFlag < 0) {
                curr = curr.getLeftChild();
                nodeType = NodeType.LEFT;
            } else {
                curr = curr.getRigthChild();
                nodeType = NodeType.RIGHT;
            }
        }
        RedBlackNode<T> parent = curr.getParent();
        //将新的节点添加到树中
        newNode.setParent(parent);
        if (NodeType.LEFT == nodeType) {
            parent.setLeftChild(newNode);
        } else {
            parent.setRigthChild(newNode);
        }
        return newNode;
    }

    /**
     * 获取当前节点的叔叔节点
     *
     * @param node 当前节点
     */
    private RedBlackNode<T> getUncaleNode(RedBlackNode<T> node) {
        RedBlackNode<T> parent = node.getParent();
        if (parent == null) {
            return null;
        }
        RedBlackNode<T> grandPa = parent.getParent();
        if (grandPa == null) {
            return null;
        }
        return NodeType.LEFT == parent.nodeType() ? grandPa.getRigthChild() : grandPa.getLeftChild();
    }

    /**
     * 新增后修正
     *
     * @param newNode 新节点
     */
    private void insertFix(RedBlackNode<T> newNode) throws Exception {
        NodeType newNodeType = newNode.nodeType();
        RedBlackNode<T> parent = newNode.getParent();
        NodeType parentNodeType = parent.nodeType();
        ColourType parentColour = parent.getColour();
        RedBlackNode<T> grandPa = parent.getParent();
        RedBlackNode<T> uncale = getUncaleNode(newNode);
        //父节点是黑色
        if (ColourType.BLACK == parentColour) {

        } else {
            if (uncale == null) {
                return;
            }
            ColourType uncaleColour = uncale.getColour();
            //父节点是红色，叔叔节点是红色
            if (ColourType.RED == uncaleColour) {
                parent.setColour(ColourType.BLACK);
                uncale.setColour(ColourType.BLACK);
                insertFix(grandPa);
            }
            //父节点是红色，叔叔节点是黑色
            else {
                if (NodeType.LEFT == parentNodeType && NodeType.LEFT == newNodeType) {
                    rightRotate(parent);
                } else if (NodeType.RIGHT == parentNodeType && NodeType.RIGHT == newNodeType) {
                    leftRotate(parent);
                } else if (NodeType.LEFT == parentNodeType && NodeType.RIGHT == newNodeType) {
                    leftRotate(newNode);
                    rightRotate(newNode);
                } else if (NodeType.RIGHT == parentNodeType && NodeType.LEFT == newNodeType) {
                    rightRotate(newNode);
                    leftRotate(newNode);
                }
            }
        }

    }

    @Override
    public boolean delete(T key) throws Exception {
        return false;
    }

    public RedBlackTree(T key) {
        super(new RedBlackNode<T>(key, null));
    }

    public RedBlackTree() {
        super();
    }
}
