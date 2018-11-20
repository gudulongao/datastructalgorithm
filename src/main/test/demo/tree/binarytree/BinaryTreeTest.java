package demo.tree.binarytree;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    private Integer[] datas;
    private BinaryTree<Integer> tree = null;

    @Before
    public void init() throws Exception {
        tree = new BinaryTree<Integer>();
        datas = new Integer[]{9, -1, 24, 3, 6, 7, 12, 2, 4, 32, 0, 5, 1};
        for (Integer data : datas) {
            tree.insert(data);
        }
    }

    @After
    public void print() throws Exception {
        tree.preIterator();
        System.out.println();
        tree.midIterator();
        System.out.println();
        tree.postIterator();
        System.out.println();
        tree.levelIterator();
    }

    @Test
    public void insert() throws Exception {
        System.out.println("BinaryTreeTest.insert");
        tree.insert(78);
    }

    @Ignore
    @Test
    public void delete() throws Exception {
        System.out.println("BinaryTreeTest.delete");
        tree.delete(3);
    }
}