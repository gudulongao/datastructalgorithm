package binarytree.demo;

import binarytree.demo.bean.Node;

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
     * @param data 数据
     * @return 插入结果
     */
    public boolean insert(int data){
        Node node = new Node(data);
        if (root == null){
            root = node;
            return true;
        }
        Node curr = root;
        Node parent;
        while (curr != null){
            parent = curr;
            if(curr.getData()>data){
                curr = curr.getLeftChild();
                if(curr == null){
                    parent.setLeftChild(node);
                    return true;
                }
            }else{
                curr = curr.getRigthChild();
                if(curr == null){
                    parent.setRigthChild(node);
                }
            }
        }
        return false;
    }

    /**
     * 中序遍历
     */
    public void middleIterator(){
        if(root == null){
            System.out.println("empty tree");
            return;
        }
        middleIterator(root);
    }
    private void middleIterator(Node node){
        if (node == null){
            return;
        }
        middleIterator(node.getLeftChild());
        System.out.print(node.getData()+" ");
        middleIterator(node.getRigthChild());
    }

    /**
     * 先序遍历
     */
    public void preIterator(){
        if(root == null){
            System.out.println("empty tree");
            return;
        }
        preIterator(root);
    }
    private void preIterator(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.getData()+" ");
        preIterator(node.getLeftChild());
        preIterator(node.getRigthChild());
    }

    /**
     * 后序遍历
     */
    public void postIterator(){
        if(root == null){
            System.out.println("empty tree");
            return;
        }
        postIterator(root);
    }
    private void postIterator(Node node){
        if (node == null){
            return;
        }
        postIterator(node.getLeftChild());
        postIterator(node.getRigthChild());
        System.out.print(node.getData()+" ");
    }

    public static void main(String[] args) {
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
    }
}
