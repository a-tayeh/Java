/**
 * Project 3 - SorterTest           Ali Tayeh           CMSC-256-001        03/15/2018
 *
 * This is a Junit test class that checks if our class method in sorter behaves as described in the specs PDF.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SorterTest {

    /**
     * Testing a CRAZY-LONG array
     */
    @Test
    public void sortIntArray1() {
        int[] arr =      {1 ,2 ,2 , 55, 55, 11, 1, 3, 2, 88, 88, 17, 35, 2, 14, 53, 85, 12, 75, 12, 5643, 12, 65, 86, 2, 65, 12, 967,
                         124, 8654, 123, 532, 633, 62363, 12, 43, 75, 97, 12, 21, 43, 65765, 4, 1214, 463, 754, 85,
                         967, 235, 23, 124, 123, 654, 745, 234, 123, 643, 1242, 532, 623, 263, 2356, 124, 124
                        ,124, 51, 532, 63,1 , 26, 43, 75, 12, 743, 854, 234, 2365, 364, 7543, 23, 523, 574, 5865, 985,
                         1241, 52, 12754, 123, 64};

        int [] expected = {1, 1, 1, 2, 2, 2, 2, 2, 3, 4, 11, 12, 12, 12, 12, 12, 12, 12, 14, 17, 21, 23, 23, 26,
                            35, 43, 43, 43, 51, 52, 53, 55, 55, 63, 64, 65, 65, 75, 75, 75, 85, 85, 86, 88, 88, 97, 123, 123,
                            123, 123, 124, 124, 124, 124, 124, 234, 234, 235, 263, 364, 463, 523, 532, 532, 532, 574, 623, 633,
                            643, 654, 743, 745, 754, 854, 967, 967, 985, 1214, 1241, 1242, 2356, 2365, 5643, 5865, 7543, 8654,
                            12754, 62363, 65765};

        int [] actual = Sorter.sortIntArray(arr);

        Assert.assertArrayEquals(expected,actual);
    }

    /**
     *  if all elements in array are the same test
     */
    @Test
    public void sortIntArray2() {
        int[] arr = {0,0,0,0,0,0};
        int [] expected = {0,0,0,0,0,0};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    /**
     * if all elements but one are the same
     */
    @Test
    public void sortIntArray3() {
        int[] arr = {0,0,0,1,0,0,0};
        int [] expected = {0,0,0,0,0,0,1};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    /**
     * testing if one element is negative test
      */
    @Test(expected = IllegalArgumentException.class)
    public void sortIntArray4() {
        int[] arr = {0,0,0,1,-1,0,0};
         Sorter.sortIntArray(arr);
    }

    /**
     *  testing a single entry array
      */
    @Test
    public void sortIntArray5() {
        int[] arr = {0};
        int [] expected = {0};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    /**
     * if all entires are negative test
      */
    @Test(expected = IllegalArgumentException.class)
    public void sortIntArray6() {
        int[] arr = {-1,-1,-1,-1};
         Sorter.sortIntArray(arr);
    }

    /**
     * testing an already sorted array
     */
    @Test
    public void sortIntArray7() {
        int[] arr = {1,2,3,4,5};
        int [] expected = {1,2,3,4,5};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    /**
     * reverse array test
      */
    @Test
    public void sortIntArray8() {
        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        int [] expected = {1,2,3,4,5,6,7,8,9,10};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    /**
     *  random test
     */
    @Test
    public void sortIntArray9() {
        int[] arr = {4,2,1,3,4,1,2,1,0,4};
        int [] expected = {0,1,1,1,2,2,3,4,4,4};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    /**
     * testing an empty array test
     */
    @Test
    public void sortIntArray10() {
        int[] arr = {};
        int [] expected = {};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

    /**
     * testing a null array
     */
    @Test(expected = IllegalArgumentException.class)
    public void sortIntArray11() {
        int[] arr = null;
        Sorter.sortIntArray(arr);
    }

    /**
     * Testing a fractions array
     */
    @Test
    public void sortIntArray12() {
        int[] arr = {12/5,3/2,13/4};
        int [] expected = {3/2,12/5,13/4};
        int [] actual = Sorter.sortIntArray(arr);
        Assert.assertArrayEquals(expected,actual);
    }

}