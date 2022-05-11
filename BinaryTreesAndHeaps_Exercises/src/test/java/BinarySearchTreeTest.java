import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> binarySearchTree;


    @Before
    public void setup() {
        this.binarySearchTree = new BinarySearchTree<>();
        this.binarySearchTree.insert(7);
        this.binarySearchTree.insert(5);
        this.binarySearchTree.insert(9);
        this.binarySearchTree.insert(4);
        this.binarySearchTree.insert(3);
        this.binarySearchTree.insert(12);
        this.binarySearchTree.insert(8);
        this.binarySearchTree.insert(6);
    }

    @Test
    public void testInsertMethod() {
        BinarySearchTree.Node<Integer> root = binarySearchTree.getRoot();

        System.out.println();

        Assert.assertEquals(Integer.valueOf(7), root.getValue());
        Assert.assertEquals(Integer.valueOf(5), root.getLeft().getValue());
        Assert.assertEquals(Integer.valueOf(9), root.getRight().getValue());
    }

    @Test
    public void testContainsMethod() {
        System.out.println();
        boolean contains = this.binarySearchTree.contains(Integer.valueOf(7));
        boolean vaue = this.binarySearchTree.contains(Integer.valueOf(8));
        boolean contains1 = this.binarySearchTree.contains(Integer.valueOf(25));

        assertTrue(contains);
        assertTrue(vaue);
        assertFalse(contains1);
    }

    @Test
    public void testDfs() {

        BinarySearchTree.Node<Integer> root = binarySearchTree.getRoot();

        List<BinarySearchTree.Node<Integer>> values = new ArrayList<>();
        binarySearchTree.findAllNodesDfs(root, values);

        Assert.assertEquals(8, values.size());
    }

    @Test
    public void testRange() {
        List<Integer> range = binarySearchTree.range(3, 7);

        System.out.println();

        Assert.assertEquals(5, range.size());
    }

    @Test
    public void testRemoveMin() {

        System.out.println();

        boolean contains = binarySearchTree.contains(Integer.valueOf(3));

        assertEquals(true, contains);
        binarySearchTree.deleteMin();

        assertFalse(binarySearchTree.contains(Integer.valueOf(3)));
    }

    @Test
    public void testRemoveMax() {

        System.out.println();

        boolean contains = binarySearchTree.contains(Integer.valueOf(12));

        assertEquals(true, contains);
        binarySearchTree.deleteMax();

        assertFalse(binarySearchTree.contains(Integer.valueOf(12)));
    }

    @Test
    public void testRankMethod() {
        int rank = binarySearchTree.rank(Integer.valueOf(9));

        Assert.assertEquals(3, rank);
    }

    @Test
    public void testCountWhenDeleteMin() {
        binarySearchTree.deleteMin();

        int rankRoot = binarySearchTree.rank(Integer.valueOf(7));
        int rank1 = binarySearchTree.rank(Integer.valueOf(5));
        int rank2 = binarySearchTree.rank(Integer.valueOf(4));

        Assert.assertEquals(7, rankRoot);
        Assert.assertEquals(3, rank1);
        Assert.assertEquals(1, rank2);



    }
    @Test
    public void testCountWhenDeleteMax() {
        binarySearchTree.deleteMax();

        int rankRoot = binarySearchTree.rank(Integer.valueOf(7));
        int rank1 = binarySearchTree.rank(Integer.valueOf(9));


        Assert.assertEquals(7, rankRoot);
        Assert.assertEquals(2, rank1);




    }

    @Test
    public void testFloorMethod() {
        System.out.println();
        Integer floor = binarySearchTree.floor(Integer.valueOf(3));

        Assert.assertEquals(Integer.valueOf(4), floor);
    }
    @Test
    public void testCeilMethod() {
        System.out.println();
        Integer floor = binarySearchTree.ceil(Integer.valueOf(9));

        Assert.assertEquals(Integer.valueOf(12), floor);
    }




}