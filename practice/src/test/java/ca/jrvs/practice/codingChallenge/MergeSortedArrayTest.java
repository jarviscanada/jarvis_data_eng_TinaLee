package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MergeSortedArrayTest {

  private MergeSortedArray mergeSortedArray;

  @Before
  public void setUp() throws Exception {
    mergeSortedArray = new MergeSortedArray();
  }

  @Test
  public void mergeSortedSort() {
    int [] nums1 = {1,2,3,0,0,0}, nums2 = {2,5,6};
    int m = 3, n = 3;
    int[] expected = {1,2,2,3,5,6};
    assertArrayEquals(mergeSortedArray.mergeSortedSort(nums1,m,nums2,n), expected);
    int[] nums3 = {1}, nums4 = {};
    m = 1;
    n = 0;
    int[] expected1 = {1};
    assertArrayEquals(mergeSortedArray.mergeSortedSort(nums3,m,nums4,n), expected1);
  }
}