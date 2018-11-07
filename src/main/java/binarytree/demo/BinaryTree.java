package binarytree.demo;

import binarytree.demo.bean.ChildType;
import binarytree.demo.bean.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 */
public class BinaryTree {
    /**
     * 根节点
     */
    private Node root;

    /**
     * 插入数据
     *
     * @param data 数据
     * @return 插入结果
     */
    public boolean insert(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return true;
        }
        Node curr = root;
        Node parent;
        while (curr != null) {
            parent = curr;
            if (curr.getData() > data) {
                curr = curr.getLeftChild();
                if (curr == null) {
                    parent.setLeftChild(node);
                    return true;
                }
            } else {
                curr = curr.getRigthChild();
                if (curr == null) {
                    parent.setRigthChild(node);
                }
            }
        }
        return false;
    }

    /**
     * 中序遍历
     */
    public void middleIterator() {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        System.out.print("BinaryTree.middleIterator ");
        middleIterator(root);
    }

    private void middleIterator(Node node) {
        if (node == null) {
            return;
        }
        middleIterator(node.getLeftChild());
        System.out.print(node.getData() + " ");
        middleIterator(node.getRigthChild());
    }

    /**
     * 先序遍历
     */
    public void preIterator() {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        System.out.print("BinaryTree.preIterator ");
        preIterator(root);
    }

    private void preIterator(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + " ");
        preIterator(node.getLeftChild());
        preIterator(node.getRigthChild());
    }

    /**
     * 后序遍历
     */
    public void postIterator() {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        System.out.print("BinaryTree.postIterator ");
        postIterator(root);
    }

    private void postIterator(Node node) {
        if (node == null) {
            return;
        }
        postIterator(node.getLeftChild());
        postIterator(node.getRigthChild());
        System.out.print(node.getData() + " ");
    }

    /**
     * 层序遍历
     */
    public void levelIterator() {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        System.out.print("BinaryTree.levelIterator ");
        Queue<Node> queue = new LinkedList<Node>();
        System.out.print(root.getData() + " ");
        queue.add(root);
        levelIterator(queue);
    }

    private void levelIterator(Queue<Node> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Node node = queue.poll();
        Node leftChild = node.getLeftChild();
        if (leftChild != null) {
            System.out.print(leftChild.getData() + " ");
            queue.offer(leftChild);
        }
        Node rigthChild = node.getRigthChild();
        if (rigthChild != null) {
            System.out.print(rigthChild.getData() + " ");
            queue.offer(rigthChild);
        }
        levelIterator(queue);
    }

    /**
     * 删除
     *
     * @param data 要删除的数据
     * @return 结果
     * @throws Exception
     */
    public boolean delete(int data) throws Exception {
        if (root == null) {
            throw new Exception("empty tree!");
        }
        if (data == root.getData()) {
            root = null;
            return true;
        }
        Node target = null;
        ChildType targetType = null;
        Node curr = root;
        Node parent = curr;
        while (curr != null) {
            if (curr.getData() == data) {
                target = curr;
                break;
            }
            parent = curr;
            if (curr.getData() > data) {
                curr = curr.getLeftChild();
                targetType = ChildType.LEFT;
            } else {
                curr = curr.getRigthChild();
                targetType = ChildType.RIGHT;
            }
        }
        if (target == null) {
            throw new Exception("could not find data :" + data);
        }
        ChildType targetChildType = target.getChildType();
        if (ChildType.NONE == targetChildType) {
            return deleteNoChild(parent, targetType);
        } else if (ChildType.LEFT == targetChildType || ChildType.RIGHT == targetChildType) {
            return deleteSingleChild(parent, target, targetType, targetChildType);
        } else {
            return deleteBothChild(parent, target, targetType);
        }
    }

    /**
     * 删除叶子节点
     *
     * @param parent 父节点
     * @param type   叶子的类型
     * @return 删除结果
     */
    private boolean deleteNoChild(Node parent, ChildType type) {
        if (ChildType.LEFT == type) {
            parent.setLeftChild(null);
            return true;
        } else if (ChildType.RIGHT == type) {
            parent.setRigthChild(null);
            return true;
        }
        return false;
    }

    /**
     * 删除有一个子树的节点
     *
     * @param parent          父节点
     * @param target          目标节点
     * @param targetType      目标节点位于父节点的类型
     * @param targetChildType 目标节点子树的类型
     * @return 结果
     */
    private boolean deleteSingleChild(Node parent, Node target, ChildType targetType, ChildType targetChildType) {
        if (ChildType.LEFT == targetType) {
            if (ChildType.LEFT == targetChildType) {
                parent.setLeftChild(target.getLeftChild());
            } else if (ChildType.RIGHT == targetChildType) {
                parent.setLeftChild(target.getRigthChild());
            }
        } else if (ChildType.RIGHT == targetType) {
            if (ChildType.LEFT == targetChildType) {
                parent.setRigthChild(target.getLeftChild());
            } else if (ChildType.RIGHT == targetChildType) {
                parent.setRigthChild(target.getRigthChild());
            }
        }
        return false;
    }

    /**
     * 删除节点既有左子树又有右子树
     *
     * @param parent     父节点
     * @param target     目标节点
     * @param targetType 目标子树类型
     * @return 结果
     */
    private boolean deleteBothChild(Node parent, Node target, ChildType targetType) throws Exception {
        Node targetLChild = target.getLeftChild();
        Node targetRCild = target.getRigthChild();
        //如果目标节点的右子节点无左子树，即目标的右子节点为后继节点
        if (targetRCild.getLeftChild() == null) {
            //父节点的右子树为后继节点
            parent.setRigthChild(targetRCild);
            //目标节点的左子树为后继节点的左子树
            targetRCild.setLeftChild(targetLChild);
            return true;
        }
        //如果目标即诶单的右子节点有左子树，则需要遍历找到后继节点（目标节点右子树中的最小值--右子树的最左节点）
        else {
            Node curr = targetRCild;
            Node replaceNode = null, repalceParent = target, replaceChild;
            while (curr != null) {
                replaceChild = curr.getLeftChild();
                if (replaceChild == null) {
                    replaceNode = curr;
                    break;
                }
                repalceParent = curr;
                curr = curr.getLeftChild();
            }
            //将后继节点的右子树作为后继节点父节点的左子树
            repalceParent.setLeftChild(replaceNode.getRigthChild());
            //将目标节点的右子树作为后继节点的右子树
            replaceNode.setRigthChild(targetRCild);
            //将目标节点的左子树作为后继节点的左子树
            replaceNode.setLeftChild(targetLChild);
            //将后继节替换目标节点父节点的子树
            if (ChildType.LEFT == targetType) {
                parent.setLeftChild(replaceNode);
            } else if (ChildType.RIGHT == targetType) {
                parent.setRigthChild(replaceNode);
            } else {
                throw new Exception("wrong target child type!");
            }
            return true;
        }
    }

    /**
     * 查找最小值
     *
     * @return 最小值
     * @throws Exception
     */
    public int findMin() throws Exception {
        if (root == null) {
            throw new Exception("empty tree!");
        }
        Node curr = root;
        Node parent = null;
        while (curr != null) {
            parent = curr;
            curr = curr.getLeftChild();
        }
        return parent.getData();
    }

    /**
     * 查找最大值
     *
     * @return 最大值
     * @throws Exception
     */
    public int findMax() throws Exception {
        if (root == null) {
            throw new Exception("empty tree!");
        }
        Node curr = root;
        Node parent = null;
        while (curr != null) {
            parent = curr;
            curr = curr.getRigthChild();
        }
        return parent.getData();
    }

    /**
     * 初始化一个二叉树
     *
     * @param datas 数据数组
     * @return 二叉树实例
     */
    public static BinaryTree init(int... datas) {
        BinaryTree tree = new BinaryTree();
        if (datas == null || datas.length == 0) {
            return tree;
        }
        for (int data : datas) {
            tree.insert(data);
        }
        System.out.print("init tree success.");
        tree.levelIterator();
        return tree;
    }

    /**
     * 测试删除
     * @param datas 初始化数据数组
     * @param data 删除数据
     * @throws Exception
     */
    public static void testDelete(int[] datas, int data) throws Exception {
        BinaryTree tree = init(datas);
        System.out.println("delete " + data);
        tree.delete(data);
        tree.levelIterator();
    }

    public static void main(String[] args) throws Exception {
        testDelete(new int[]{-10, -1, 1, 2, 3, 4, 5, 6, 7, 18, 22, 9, 10, 12, 44, 102, 22}, 4);
    }

}
