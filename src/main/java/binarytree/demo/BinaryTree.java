package binarytree.demo;

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
        System.out.print(root.getData()+" ");
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

    public static void main(String[] args) throws Exception {
        BinaryTree tree = new BinaryTree();
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(1);
        tree.insert(10);
        tree.insert(-1);
        tree.middleIterator();
        System.out.println();
        tree.preIterator();
        System.out.println();
        tree.postIterator();
        System.out.println();
        tree.levelIterator();
        System.out.println();
        System.out.println("max value: " + tree.findMax());
        System.out.println("min value: " + tree.findMin());
    }

}
