package binarytree.demo;

import binarytree.demo.bean.ChildType;
import binarytree.demo.bean.Node;

import java.util.*;

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
        //深度优先遍历，找到目标节点
        TargetNode deleteTarget = findDeleteTarget(data);
        Node target = deleteTarget.getTarget();
        Node parent = deleteTarget.getTargetParent();
        ChildType targetType = deleteTarget.getTargetType();
        ChildType targetChildType = target.getChildType();
        //目标节点为叶子节点（无子节点）
        if (ChildType.NONE == targetChildType) {
            return deleteNoChild(parent, targetType);
        }
        //目标节点有一个子节点
        else if (ChildType.LEFT == targetChildType || ChildType.RIGHT == targetChildType) {
            return deleteSingleChild(parent, target, targetType, targetChildType);
        }
        //目标有两个子节点
        else {
            return deleteBothChild(parent, target, targetType);
        }
    }

    /**
     * 查找目标节点
     *
     * @param data 目标数据值
     * @return 目标节点
     * @throws Exception
     */
    private TargetNode findDeleteTarget(int data) throws Exception {
        ChildType targetType = null, targetChildType;
        Node target = null, curr = root, parent = curr;
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
        return new TargetNode(target, parent, targetType);
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
        Node targetRCild = target.getRigthChild();
        //如果目标节点的右子节点无左子树，即目标的右子节点为后继节点
        if (targetRCild.getLeftChild() == null) {
            return deleteReplaceNoLChild(parent, target, targetType);
        }
        //如果目标即诶单的右子节点有左子树，则需要遍历找到后继节点（目标节点右子树中的最小值--右子树的最左节点）
        else {
            return deleteReplaceHasLChild(parent, target, targetType);
        }
    }

    /**
     * 删除后继节点为目标节点的右子节点（目标节点的右子树无左子树时）
     *
     * @param parent     目标节点的父节点
     * @param target     目标节点
     * @param targetType 目标节点类型
     * @return 删除结果
     */
    private boolean deleteReplaceNoLChild(Node parent, Node target, ChildType targetType) {
        Node targetLChild = target.getLeftChild(), targetRCild = target.getRigthChild();
        //后继节点为父节点的子节点
        parent.setRigthChild(targetRCild);
        if (ChildType.LEFT == targetType) {
            parent.setLeftChild(targetRCild);
        } else if (ChildType.RIGHT == targetType) {
            parent.setRigthChild(targetRCild);
        }
        //目标节点的左子树为后继节点的左子树
        targetRCild.setLeftChild(targetLChild);
        return true;
    }

    /**
     * 删除后继节点为目标节点右子树的左子节点（目标节点的右子节点有左子树）
     *
     * @param parent     目标节点的父级节点
     * @param target     目标节点
     * @param targetType 目标节点类型
     * @return 删除结果
     * @throws Exception
     */
    private boolean deleteReplaceHasLChild(Node parent, Node target, ChildType targetType) throws Exception {
        Node targetLChild = target.getLeftChild(), targetRCild = target.getRigthChild();
        //查找后继节点信息
        TargetNode replaceTarget = findReplaceNode(targetLChild);
        //获取后继节点以及后继节点的父级节点
        Node replaceNode = replaceTarget.getTarget();
        Node replaceParent = replaceTarget.getTargetParent();
        //后继节点的右子树作为后继节点的父节点的左子节点（整个查找机制就是要找到最左节点，故后继节点没有左子节点）
        replaceParent.setLeftChild(replaceNode.getRigthChild());
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

    private TargetNode findReplaceNode(Node targetRChild) {
        Node curr = targetRChild, repalceParent = null, replaceNode = null, replaceChild = null;
        while (curr != null) {
            replaceChild = curr.getLeftChild();
            if (replaceChild == null) {
                replaceNode = curr;
                break;
            }
            repalceParent = curr;
            curr = curr.getLeftChild();
        }
        return new TargetNode(repalceParent, replaceNode, ChildType.LEFT);
    }

    class TargetNode {
        Node target;
        Node targetParent;
        ChildType targetType;

        public TargetNode(Node target, Node targetParent, ChildType targetType) {
            this.target = target;
            this.targetParent = targetParent;
            this.targetType = targetType;
        }

        public TargetNode() {
        }

        public Node getTarget() {
            return target;
        }

        public void setTarget(Node target) {
            this.target = target;
        }

        public Node getTargetParent() {
            return targetParent;
        }

        public void setTargetParent(Node targetParent) {
            this.targetParent = targetParent;
        }

        public ChildType getTargetType() {
            return targetType;
        }

        public void setTargetType(ChildType targetType) {
            this.targetType = targetType;
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
     * 获取深度
     *
     * @param target 目标数据
     * @return 深度
     */
    public int getDeepth(int target) {
        if (root == null) {
            return -1;
        }
        Node curr = root;
        int deep = 0;
        while (curr != null) {
            if (target == curr.getData()) {
                return deep;
            } else {
                deep++;
                if (target < curr.getData()) {
                    curr = curr.getLeftChild();
                } else {
                    curr = curr.getRigthChild();
                }
            }
        }
        return -1;
    }

    public int getWith() {
        if (root == null) {
            return -1;
        }
        List<Node> list = new ArrayList<Node>();
        list.add(root);
        return getWith(list, 1);
    }

    private int getWith(List<Node> list, Integer max) {
        int count = 0;
        ChildType childType;
        //构建新的子节点队列
        List<Node> childNodeList = new ArrayList<Node>();
        for (Node node : list) {
            childType = node.getChildType();
            if (ChildType.NONE != childType) {
                count += node.directChildSize();
                if (node.getLeftChild() != null) {
                    childNodeList.add(node.getLeftChild());
                }
                if (node.getRigthChild() != null) {
                    childNodeList.add(node.getRigthChild());
                }
            }
        }
        if (max < count) {
            max = count;
        }
        if (!childNodeList.isEmpty()) {
            return getWith(childNodeList, max);
        } else {
            return max;
        }
    }

    /**
     * 获取高度
     *
     * @param data 指定数据
     * @return 指定数据的高度（距离叶子节点的最大距离）
     * @throws Exception
     */
    public int getHeight(int data) throws Exception {
        if (root == null) {
            throw new Exception("empty tree!");
        }
        //查找目标节点
        Node target = findTarget(data);
        //递归查找目标节点的深度
        return getHeight(target, 0);
    }

    /**
     * 查找目标节点
     *
     * @param data 目标值
     * @return 目标节点
     * @throws Exception
     */
    private Node findTarget(int data) throws Exception {
        Node curr = root, target = null;
        while (curr != null) {
            if (data == curr.getData()) {
                target = curr;
                break;
            } else if (data < curr.getData()) {
                curr = curr.getLeftChild();
            } else {
                curr = curr.getRigthChild();
            }
        }
        if (target == null) {
            throw new Exception("could not find target:" + data);
        }
        return target;
    }

    /**
     * 获取高度（递归）
     *
     * @param curr   当前节点
     * @param height 当前累计高度
     * @return 当前高度
     * @throws Exception
     */
    private int getHeight(Node curr, int height) throws Exception {
        ChildType childType = curr.getChildType();
        if (ChildType.NONE == childType) {
            return height;
        } else {
            height++;
            if (ChildType.LEFT == childType) {
                return getHeight(curr.getLeftChild(), height);
            } else if (ChildType.RIGHT == childType) {
                return getHeight(curr.getRigthChild(), height);
            } else if (ChildType.BOTH == childType) {
                int heightL = getHeight(curr.getLeftChild(), height);
                int heightR = getHeight(curr.getRigthChild(), height);
                return heightL > heightR ? heightL : heightR;
            } else {
                throw new Exception("wrong child type!");
            }
        }
    }

    public Node[] toArray() {
        if (root == null) {
            return null;
        }
        Node curr = root;
        List<Node> result = new ArrayList<Node>();
        List<Node> childList = new ArrayList<Node>();
        result.add(curr);
        childList.add(curr);
        toArray(childList, result);
        return result.toArray(new Node[result.size()]);
    }

    private void toArray(List<Node> childList, List<Node> result) {
        if (childList.isEmpty()) {
            return;
        }
        //下一层子节点队列
        List<Node> newChildList = new ArrayList<Node>();
        //下一层数组数据队列
        List<Node> newResult = new ArrayList<Node>();
        for (Node node : childList) {
            if (node == null) {
                continue;
            }
            if (node.getLeftChild() != null) {
                newChildList.add(node.getLeftChild());
                newResult.add(node.getLeftChild());
            } else {
                newResult.add(new Node());
            }
            if (node.getRigthChild() != null) {
                newChildList.add(node.getRigthChild());
                newResult.add(node.getRigthChild());
            } else {
                newResult.add(new Node());
            }
        }
        //下一层子节点为空，则表明当前是最后一层即高度为0的层，深度最大的层
        if (!newChildList.isEmpty()) {
            result.addAll(newResult);
            toArray(newChildList, result);
        } else {
            return;
        }
    }

    /**
     * 输出二叉树
     *
     * @param tree
     */
    public static void print(BinaryTree tree) {
        tree.preIterator();
        System.out.println();
        tree.middleIterator();
        System.out.println();
        tree.postIterator();
        System.out.println();
        tree.levelIterator();
        System.out.println();
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
        System.out.println("init tree success.");
        print(tree);
        return tree;
    }

    /**
     * 测试删除
     *
     * @param datas 初始化数据数组
     * @param data  删除数据
     * @throws Exception
     */
    public static void testDelete(int[] datas, int data) throws Exception {
        BinaryTree tree = init(datas);
        System.out.println("delete " + data);
        tree.delete(data);
        tree.levelIterator();
    }

    /**
     * 测试获取节点的深度
     *
     * @param datas
     * @param target
     * @throws Exception
     */
    public static void testGetDeep(int[] datas, int target) throws Exception {
        BinaryTree tree = init(datas);
        System.out.println(target + " deep :" + tree.getDeepth(target));
//        print(tree);
    }

    public static void testGetWith(int[] datas) {
        BinaryTree tree = init(datas);
        System.out.println("with: " + tree.getWith());
    }

    public static void testGetHeight(int[] datas) throws Exception {
        BinaryTree tree = init(datas);
        System.out.println("getHeight: " + tree.getHeight(9));
    }

    public static void testToArray(int[] datas) throws Exception {
        BinaryTree tree = init(datas);
        Node[] nodes = tree.toArray();
        System.out.print("to array: ");
        for (Node node : nodes) {
            if (node == null) {
                System.out.print(" null ");
            } else {
                System.out.print((node.getData() == null) ? " null " : " " + node.getData() + " ");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int[] datas = new int[]{9, 23, 6, 8, -20, 78, 45, 22, 10, -10};
//        testDelete(datas, 22);
//        testGetDeep(datas, 22);
//        testGetWith(datas);
//        testGetHeight(datas);
        testToArray(datas);
    }


}
