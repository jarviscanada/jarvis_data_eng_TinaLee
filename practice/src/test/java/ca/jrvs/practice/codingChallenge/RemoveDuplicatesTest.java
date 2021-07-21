package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RemoveDuplicatesTest {

  private RemoveDuplicates removeDuplicates;

  @Before
  public void setUp() throws Exception {
    removeDuplicates = new RemoveDuplicates();
  }

  @Test
  public void removeDuplicates() {
   int[] nums = {1,1,2};
   int expected = 2;
   assertEquals(removeDuplicates.removeDuplicates(nums), expected);
   int[] nums1 = {0,0,1,1,1,2,2,3,3,4};
   expected = 5;
    assertEquals(removeDuplicates.removeDuplicates(nums1), expected);
  }
}