package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContainsDuplicateTest {

  private ContainsDuplicate containsDuplicate;

  @Before
  public void setUp() throws Exception {
    containsDuplicate = new ContainsDuplicate();
  }

  @Test
  public void containsDuplicateSort() {
    int[] nums = {1,2,3,1};
    assertTrue(containsDuplicate.containsDuplicateSort(nums));
    int[] nums1 = {1,2,3,4};
    assertFalse(containsDuplicate.containsDuplicateSort(nums1));
    int[] nums2 = {1,1,1,3,3,4,3,2,4,2};
    assertTrue(containsDuplicate.containsDuplicateSort(nums2));
  }

  @Test
  public void containDuplicateSet() {
    int[] nums = {1,2,3,1};
    assertTrue(containsDuplicate.containsDuplicateSort(nums));
    int[] nums1 = {1,2,3,4};
    assertFalse(containsDuplicate.containsDuplicateSort(nums1));
    int[] nums2 = {1,1,1,3,3,4,3,2,4,2};
    assertTrue(containsDuplicate.containsDuplicateSort(nums2));
  }
}