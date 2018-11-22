package demo.tree.redblacktree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {
    private RedBlackTree<Integer> tree;

    @Before
    public void init() throws Exception {
        tree = new RedBlackTree<Integer>();
        Integer[] datas = new Integer[]{9, -1, 24, 3, 6, 7, 12, 2, 4, 32, 0, 5, 1};
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
        System.out.println();
    }

    @Test
    public void testInsert() throws Exception {
        tree.insert(100);
    }
}