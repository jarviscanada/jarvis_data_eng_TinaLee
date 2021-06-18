package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Swap-two-numbers-5416ee59bcc946658084d83f8defed5e
 */
public class SwapTwoNumbers {

  /**
   * Big-O: O(n) bit-wise operations
   */
  public int[] swapTwoNumbersBit(int[] nums) {
    nums[0] = nums[0] ^ nums[1];
    nums[1] = nums[0] ^ nums[1];
    nums[0] = nums[0] ^ nums[1];
    return nums;
  }

  /**
   * Big-O: O(n) Arithmetic operations
   */
  public int[] swapTwoNumbersArithmetic(int[] nums) {
    nums[0] = nums[0] + nums[1];
    nums[1] = nums[0] - nums[1];
    nums[0] = nums[0] - nums[1];
    return nums;
  }
}