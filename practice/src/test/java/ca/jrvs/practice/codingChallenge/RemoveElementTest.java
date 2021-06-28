package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RemoveElementTest {

  RemoveElement removeElement;

  @Before
  public void setUp() throws Exception {
    removeElement = new RemoveElement();
  }

  @Test
  public void twoPointer() {
    int[] nums = {3,2,2,3};
    int val = 3;
    int expected = 2;
    assertEquals(removeElement.twoPointer(nums, val), expected);
    int[] nums1 = {0,1,2,2,3,0,4,2};
    val = 2;
    expected = 5;
    assertEquals(removeElement.twoPointer(nums1, val), expected);
  }
}