package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SwapTwoNumbersTest {

  private SwapTwoNumbers swapTwoNumbers;

  @Before
  public void setUp() {
    swapTwoNumbers = new SwapTwoNumbers();
  }

  @Test
  public void swapTwoNumbersBit() {
    int[] nums = {7, 8};
    int[] result = swapTwoNumbers.swapTwoNumbersBit(nums);
    assertEquals(8, result[0]);
    assertEquals(7, result[1]);
  }

  @Test
  public void swapTwoNumbersArithmetic() {
    int[] nums = {7, 8};
    int[] result = swapTwoNumbers.swapTwoNumbersBit(nums);
    assertEquals(8, result[0]);
    assertEquals(7, result[1]);
  }
}