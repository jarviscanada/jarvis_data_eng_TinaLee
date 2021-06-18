package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * ticket: https://www.notion.so/jarvisdev/Missing-Number-c6fafd60f95f4fffbd5193cb06fec121
 */
public class MissingNumber {

  /**
   * Big-O: O(n)
   * Justification: for loop
   */
  public int findMissingNumSum(int[] nums) {
    int n = nums.length;
    int total = 0;
    for (int i = 1; i <= n; i++) {
      total += i;
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += nums[i];
    }

    return total - sum;
  }

  /**
   * Big-O: O(n)
   * Justification: for loop
   */
  public int findMissingNumSet(int[] nums) {
    Set<Integer> set = new HashSet<>();
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      set.add(nums[i]);
    }

    int missingNum = Integer.MIN_VALUE;
    for (int i = 0; i <= n; i++) {
      if (!set.contains(i)) missingNum = i;
    }
    return missingNum;
  }

  /**
   * Big-O: O(n)
   * Justification: for loop
   */
  public int findMissingNumGauss(int[] nums) {
    int n = nums.length;
    int total = n*(n+1)/2;
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += nums[i];
    }
    return total - sum;
  }
}
