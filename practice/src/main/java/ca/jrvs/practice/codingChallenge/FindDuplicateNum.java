package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindDuplicateNum {

  /**
   * Big-O: O(nlogn)
   * Justification: Arrays.sort cost O(nlogn)
   */
  public int findDuplicateSort(int[] nums) {
    Arrays.sort(nums);
    for (int i=1; i < nums.length; i++) {
      if (nums[i] == nums[i-1]) {
        return nums[i];
      }
    }
    return Integer.MIN_VALUE;
  }

  /**
   * Big-O: O(n)
   * Justification: for-loop
   */
  public int findDuplicatesSet(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        return nums[i];
      } else {
        set.add(nums[i]);
      }
    }
    return Integer.MIN_VALUE;
  }


}
