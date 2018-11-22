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
     * @param currNode 当前节点
     */
    private void leftRotate(RedBlackNode<T> currNode) throws Exception {
        RedBlackNode<T> parent = currNode.getParent();
        NodeType nodeType = currNode.nodeType();
        //后继节点（当前节点的右子节点）
        RedBlackNode<T> replaceNode = currNode.getRigthChild();
        //处理新老节点的子节点关系（新左=老右）
        currNode.setRigthChild(replaceNode.getLeftChild());
        if (replaceNode.getLeftChild() != null) {
            replaceNode.getLeftChild().setParent(currNode);
        }
        //处理后继节点与父级节点的关系（新父=老父）如果老父为空，则表明原先的基准节点为root，旋转之后后继节点应为root
        replaceNode.setParent(parent);
        if (parent == null) {
            root = replaceNode;
        } else {
            if (NodeType.LEFT == nodeType) {
                parent.setLeftChild(replaceNode);
            } else {
                parent.setRigthChild(replaceNode);
            }
        }
        //处理新老节点的关系（老=新左）
        replaceNode.setLeftChild(currNode);
        currNode.setParent(replaceNode);
    }

    /**
     * 右旋
     *
     * @param currNode 当前节点
     */
    private void rightRotate(RedBlackNode<T> currNode) throws Exception {
        RedBlackNode<T> parent = currNode.getParent();
        NodeType nodeType = currNode.nodeType();
        //后继节点（当前节点的左子节点）
        RedBlackNode<T> replaceNode = currNode.getLeftChild();
        //处理新老节点的子节点关系（新右=老左）
        currNode.setLeftChild(replaceNode.getRigthChild());
        if (replaceNode.getRigthChild() != null) {
            replaceNode.getRigthChild().setParent(currNode);
        }
        //处理新节点与父节点的关系（新父=老父）如果原先基准节点父为空，则表明原先的基准节点为root，旋转后后继节点应为root
        replaceNode.setParent(parent);
        if (parent == null) {
            root = replaceNode;
        } else {
            if (NodeType.LEFT == nodeType) {
                parent.setLeftChild(replaceNode);
            } else {
                parent.setRigthChild(replaceNode);
            }
        }
        //处理新老节点的关系（新右=老）
        replaceNode.setRigthChild(currNode);
        currNode.setParent(replaceNode);
    }

    @Override
    public void insert(T newKey) throws Exception {
        if (newKey == null) {
            throw new IllegalArgumentException();
        }
        if (getRoot() == null) {
            root = new RedBlackNode<T>(newKey, null, ColourType.BLACK);
            return;
        }
        RedBlackNode<T> newNode = addNewNode(newKey);
        insertFix(newNode);
        getRoot().setColour(ColourType.BLACK);
    }

    /**
     * 添加新节点
     *
     * @param newKey 新关键词
     */
    private RedBlackNode<T> addNewNode(T newKey) {
        RedBlackNode<T> newNode = new RedBlackNode<T>(newKey, null, ColourType.RED);
        RedBlackNode<T> curr = getRoot(), parent = curr;
        int compareFlag;
        NodeType nodeType = null;
        //遍历树，找到可以存储新关键词的位置
        while (curr != null) {
            compareFlag = newKey.compareTo(curr.getKey());
            if (compareFlag < 0) {
                parent = curr;
                curr = curr.getLeftChild();
                nodeType = NodeType.LEFT;
            } else {
                parent = curr;
                curr = curr.getRigthChild();
                nodeType = NodeType.RIGHT;
            }
        }
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
     * @param currNode 当前节点
     */
    private void insertFix(RedBlackNode<T> currNode) throws Exception {
        NodeType currNodeType = currNode.nodeType();
        //获取父级节点
        RedBlackNode<T> parent = currNode.getParent();
        if (parent == null) {
            return;
        }
        //获取祖父节点
        RedBlackNode<T> grandPa = parent.getParent();
        if (grandPa == null) {
            return;
        }
        //父节点的颜色
        ColourType parentColour = parent.getColour();
        //父节点是黑色不做处理
        if (ColourType.BLACK == parentColour) {
            return;
        }
        //获取父级节点的节点类型
        NodeType parentNodeType = parent.nodeType();
        //获取叔叔节点
        RedBlackNode<T> uncale = getUncaleNode(currNode);
        ColourType uncaleColour = uncale == null ? null : uncale.getColour();
        //父节点是红色，叔叔节点是红色,直接变更父节点与叔叔节点的颜色，修改祖父节点的颜色
        if (ColourType.RED == uncaleColour) {
            parent.setColour(ColourType.BLACK);
            uncale.setColour(ColourType.BLACK);
            grandPa.setColour(ColourType.RED);
            //以祖父节点为基准开始修正
            insertFix(grandPa);
        }
        //父节点是红色，叔叔节点是黑色
        else {
            if (NodeType.LEFT == parentNodeType) {
                if (NodeType.RIGHT == currNodeType) {
                    leftRotate(parent);
                    parent = currNode;
                }
                parent.setColour(ColourType.BLACK);
                grandPa.setColour(ColourType.RED);
                rightRotate(grandPa);
            } else {
                if (NodeType.LEFT == currNodeType) {
                    rightRotate(parent);
                    parent = currNode;
                }
                parent.setColour(ColourType.BLACK);
                grandPa.setColour(ColourType.RED);
                leftRotate(grandPa);
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
