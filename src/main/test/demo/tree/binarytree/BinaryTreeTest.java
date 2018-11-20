package demo.tree.binarytree;

import org.junit.*;

public class BinaryTreeTest {
    private BinaryTree<Integer> tree = null;

    @Before
    public void init() throws Exception {
        tree = new BinaryTree<Integer>();
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
        System.out.println("size: " + tree.size() + " width: " + tree.width());
    }

    @Ignore
    @Test
    public void testDelete() throws Exception {
        System.out.println("BinaryTreeTest.delete before size: " + tree.size());
        boolean delResult = tree.delete(3);
        System.out.println("BinaryTreeTest.delete after size: " + tree.size());
        Assert.assertTrue(delResult);
    }

    @Test
    public void testDeep() throws Exception {
        int deep = tree.deep(5);
        Assert.assertEquals(deep, 5);
        deep = tree.deep(9);
        Assert.assertEquals(deep, 0);
        deep = tree.deep(3);
        Assert.assertEquals(deep, 2);
    }
}