package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

  /**
   * Big-O: O(nlogn)
   * Justification: Arrays.sort cost O(nlogn)
   */
  public boolean containsDuplicateSort(int[] nums) {
    Arrays.sort(nums);
    for (int i=1; i<nums.length; i++) {
      if (nums[i] == nums[i-1]) {
        return true;
      }
    }
    return false;
  }

  /**
   * Big-O: O(n)
   * Justification: for-loop
   */
  public boolean containDuplicateSet(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i=0; i<nums.length; i++) {
      if (set.contains(nums[i])) {
        return true;
      }
      set.add(nums[i]);
    }
    return false;
  }
}
