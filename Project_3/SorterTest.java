/**
 * Project 3 - SorterTest           Ali Tayeh           CMSC-256-001
 *
 * This is a Junit test class that checks if our class method in sorter behaves as described in the specs PDF.
 */

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SorterTest {

    @Test
    public void sortIntArray1() {
        int[] arr = {1,2,2,55,55,11,1,3,2};
        int [] expected = {1,1,2,2,2,3,11,55,55};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test
    public void sortIntArray2() {
        int[] arr = {0,0,0,0,0,0};
        int [] expected = {0,0,0,0,0,0};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void sortIntArray3() {
        int[] arr = {0,0,0,1,0,0,0};
        int [] expected = {0,0,0,0,0,0,1};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void sortIntArray4() {

        int[] arr = {0,0,0,1,-1,0,0};
        int [] expected = {0,0,0,0,0,0,1};
         Sorter.sortIntArray(arr);
    }

    @Test
    public void sortIntArray5() {
        int[] arr = {0};
        int [] expected = {0};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void sortIntArray6() {
        int[] arr = {-1,-1,-1,-1};
         Sorter.sortIntArray(arr);
    }
    @Test
    public void sortIntArray7() {
        int[] arr = {1,2,3,4,5};
        int [] expected = {1,2,3,4,5};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test
    public void sortIntArray8() {
        int[] arr = {9,9,8,8,7,6,54,4,3,2,0,0};
        int [] expected = {0,0,2,3,4,6,7,8,8,9,9,54};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test
    public void sortIntArray9() {
        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        int [] expected = {1,2,3,4,5,6,7,8,9,10};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test
    public void sortIntArray10() {
        int[] arr = {4,2,1,3,4,1,2,1,0,4};
        int [] expected = {0,1,1,1,2,2,3,4,4,4};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test
    public void sortIntArray11() {
        int[] arr = {};
        int [] expected = {};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }
    @Test(expected = IllegalArgumentException.class)
    public void sortIntArray12() {
        int[] arr = null;
        Sorter.sortIntArray(arr);
    }

}