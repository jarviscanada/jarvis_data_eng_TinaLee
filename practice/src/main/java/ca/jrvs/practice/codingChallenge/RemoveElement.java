package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Remove-Element-f562ecef8c6549b4ba95c871a8efcd8a
 */
public class RemoveElement {

  /**
   * Big-O: O(n)
   * Justification: for-loop
   */
  public int twoPointer(int[] nums, int val) {
    int pointer = 0;
    for (int i=0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[pointer] = nums[i];
        pointer++;
      }
    }
    return pointer;
  }
}
