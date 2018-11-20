package demo.tree.binarytree;

import demo.tree.bean.Node;
import demo.tree.bean.NodeType;
import demo.tree.bean.Tree;

/**
 * 二叉树
 *
 * @param <T> 可排序的数据结构
 */
public class BinaryTree<T extends Comparable<T>> extends Tree<T> {
    /**
     * 新增节点
     *
     * @param newKey 新的关键词
     */
    public void insert(T newKey) throws Exception {
        insert(new Node<T>(newKey));
    }

    @Override
    public void insert(Node<T> newNode) throws Exception {
        if (newNode == null || newNode.getKey() == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> curr = root, parent = root;
        int compareFlag;
        NodeType nodeType = null;
        while (curr != null) {
            compareFlag = newNode.getKey().compareTo(curr.getKey());
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
        if (NodeType.LEFT == nodeType) {
            parent.setLeftChild(newNode);
        } else {
            parent.setRigthChild(newNode);
        }
        size++;
    }

    public boolean delete(T key) throws Exception {
        return delete(new Node<T>(key));
    }

    @Override
    public boolean delete(Node<T> node) throws Exception {
        if (node == null || node.getKey() == null) {
            throw new IllegalArgumentException();
        }
        validateEmptyTree();
        TargetNodeInfo targetNodeInfo = findDeleteTarget(node);
        Node<T> targetNode = targetNodeInfo.getTargetNode();
        NodeType targetChildType = targetNode.childType();
        boolean delFlag;
        if (NodeType.NONE == targetChildType) {
            delFlag = delTargetHasNoChild(targetNodeInfo);
        } else if (NodeType.LEFT == targetChildType || NodeType.RIGHT == targetChildType) {
            delFlag = delTargetHasSingleChild(targetNodeInfo);
        } else if (NodeType.BOTH == targetChildType) {
            delFlag = delTargetHasBothChild(targetNodeInfo);
        } else {
            throw new IllegalStateException();
        }
        if (delFlag) {
            size--;
        }
        return delFlag;
    }

    /**
     * 查找要删除的目标节点信息
     *
     * @param node 要删除的节点
     * @return 目标节点信息
     */
    private TargetNodeInfo findDeleteTarget(Node<T> node) throws Exception {
        Node<T> curr = root, parent = curr, target = null;
        int compareFlag;
        NodeType nodeType = null;
        while (curr != null) {
            compareFlag = node.getKey().compareTo(curr.getKey());
            if (compareFlag == 0) {
                target = curr;
                break;
            } else if (compareFlag < 0) {
                parent = curr;
                curr = curr.getLeftChild();
                nodeType = NodeType.LEFT;
            } else {
                parent = curr;
                curr = curr.getRigthChild();
                nodeType = NodeType.RIGHT;
            }
        }
        if (target == null) {
            throw new Exception("could not find target :" + node.getKey());
        }
        return new TargetNodeInfo(target, parent, nodeType);
    }

    /**
     * 删除没有没有子节点的目标节点
     *
     * @param targetNodeInfo 目标节点信息
     */
    private boolean delTargetHasNoChild(TargetNodeInfo targetNodeInfo) throws Exception {
        NodeType targetNodeType = targetNodeInfo.getNodeType();
        Node<T> parent = targetNodeInfo.getParent();
        if (NodeType.LEFT == targetNodeType) {
            parent.setLeftChild(null);
        } else if (NodeType.RIGHT == targetNodeType) {
            parent.setRigthChild(null);
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    /**
     * 删除有一个子节点的目标节点
     *
     * @param targetNodeInfo 目标节点信息
     */
    private boolean delTargetHasSingleChild(TargetNodeInfo targetNodeInfo) throws Exception {
        Node<T> parent = targetNodeInfo.getParent();
        Node<T> targetNode = targetNodeInfo.getTargetNode();
        NodeType targetNodeType = targetNodeInfo.getNodeType();
        NodeType targetChildType = targetNode.childType();
        if (NodeType.LEFT == targetNodeType) {
            if (NodeType.LEFT == targetChildType) {
                parent.setLeftChild(targetNode.getLeftChild());
            } else if (NodeType.RIGHT == targetChildType) {
                parent.setLeftChild(targetNode.getRigthChild());
            } else {
                throw new IllegalStateException();
            }
        } else if (NodeType.RIGHT == targetNodeType) {
            if (NodeType.LEFT == targetChildType) {
                parent.setRigthChild(targetNode.getLeftChild());
            } else if (NodeType.RIGHT == targetChildType) {
                parent.setRigthChild(targetNode.getRigthChild());
            } else {
                throw new IllegalStateException();
            }
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    /**
     * 删除拥有两个子节点的目标节点
     *
     * @param targetNodeInfo 目标节点信息
     */
    private boolean delTargetHasBothChild(TargetNodeInfo targetNodeInfo) throws Exception {
        Node<T> parent = targetNodeInfo.getParent();
        Node<T> targetNode = targetNodeInfo.getTargetNode();
        NodeType targetNodeType = targetNodeInfo.getNodeType();
        Node<T> targetRCNode = targetNode.getRigthChild();
        if (targetRCNode.getLeftChild() == null) {
            if (NodeType.LEFT == targetNodeType) {
                parent.setLeftChild(targetRCNode);
            } else if (NodeType.RIGHT == targetNodeType) {
                parent.setRigthChild(targetRCNode);
            } else {
                throw new IllegalStateException();
            }
            targetRCNode.setLeftChild(targetNode.getLeftChild());
        } else {
            TargetNodeInfo replaceNodeInfo = findDelReplaceNode(targetNode);
            Node<T> replaceParent = replaceNodeInfo.getParent();
            Node<T> replaceNode = replaceNodeInfo.getTargetNode();
            replaceParent.setLeftChild(replaceNode.getRigthChild());
            replaceNode.setRigthChild(targetRCNode);
            replaceNode.setLeftChild(targetNode.getLeftChild());
            if (NodeType.LEFT == targetNodeType) {
                parent.setLeftChild(replaceNode);
            } else if (NodeType.RIGHT == targetNodeType) {
                parent.setRigthChild(replaceNode);
            }
        }
        return true;
    }

    /**
     * 查找删除节点的后继节点信息
     *
     * @param targetNode 要删除的目标节点
     * @return 后继节点信息
     */
    private TargetNodeInfo findDelReplaceNode(Node<T> targetNode) throws Exception {
        Node<T> rigthChild = targetNode.getRigthChild();
        Node<T> curr = rigthChild.getLeftChild(), parent = rigthChild;
        while (curr.getLeftChild() != null) {
            parent = curr;
            curr = curr.getLeftChild();
        }
        return new TargetNodeInfo(curr, parent, NodeType.LEFT);
    }

    /**
     * 目标节点信息
     */
    class TargetNodeInfo {
        private Node<T> targetNode;
        private Node<T> parent;
        private NodeType nodeType;

        public TargetNodeInfo(Node<T> targetNode, Node<T> parent, NodeType nodeType) {
            this.targetNode = targetNode;
            this.parent = parent;
            this.nodeType = nodeType;
        }

        public Node<T> getTargetNode() {
            return targetNode;
        }

        public void setTargetNode(Node<T> targetNode) {
            this.targetNode = targetNode;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public NodeType getNodeType() {
            return nodeType;
        }

        public void setNodeType(NodeType nodeType) {
            this.nodeType = nodeType;
        }
    }

    public static void main(String[] args) {

    }
}
