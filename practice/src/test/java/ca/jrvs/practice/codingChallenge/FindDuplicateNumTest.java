package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FindDuplicateNumTest {

  private FindDuplicateNum findDuplicateNum;

  @Before
  public void setUp() throws Exception {
    findDuplicateNum = new FindDuplicateNum();
  }

  @Test
  public void findDuplicateSort() {
    int[] nums = {1,3,4,2,2};
    int expected = 2;
    assertEquals(findDuplicateNum.findDuplicateSort(nums), expected);
    int[] nums1 = {1, 1};
    expected = 1;
    assertEquals(findDuplicateNum.findDuplicateSort(nums1), expected);
  }

  @Test
  public void findDuplicatesSet() {
    int[] nums = {1,3,4,2,2};
    int expected = 2;
    assertEquals(findDuplicateNum.findDuplicateSort(nums), expected);
    int[] nums1 = {1, 1};
    expected = 1;
    assertEquals(findDuplicateNum.findDuplicateSort(nums1), expected);
  }
}