package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket: https://www.notion.so/jarvisdev/Merge-Sorted-Array-2549aa7957a74360bcf078fe53ac0006
 */
public class MergeSortedArray {

  /**
   * Big-O: O(nlogn)
   * Justification: Arrays.sort takes O(nlogn)
   */
  public int[] mergeSortedSort(int[] nums1, int m, int[] nums2, int n) {
    int idx = m+n-1;
    while (m > 0 && n > 0) {
      if (nums1[m-1] > nums2[n-1]) {
        nums1[idx--] = nums1[m-1];
        m--;
      } else {
        nums1[idx--] = nums2[n-1];
        n--;
      }
    }
    while (n > 0) {
      nums1[idx--] = nums1[n-1];
      n--;
    }
    return nums1;
  }
}
