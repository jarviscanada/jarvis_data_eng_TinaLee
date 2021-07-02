package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-e92c78b9f3fc4929b0fba66882e0439d
 */
public class RemoveDuplicates {

  /**
   * Big-O: O(n)
   * Justification: for-loop
   */
  public int removeDuplicates(int[] nums) {
    int k = 0;
    for (int i=1; i < nums.length; i++) {
      if (nums[i] != nums[k]) {
        k++;
        nums[k] = nums[i];
      }
    }
    return ++k;
  }
}
