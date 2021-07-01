package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MissingNumberTest {

  private MissingNumber missingNumber;

  @Before
  public void setUp() throws Exception {
    missingNumber = new MissingNumber();
  }

  @Test
  public void findMissingNumSum() {
    int[] nums = {3,4,1,2,7,5,6,0};
    int expected = 8;
    assertEquals(missingNumber.findMissingNumSum(nums), expected);
  }

  @Test
  public void findMissingNumSet() {
    int[] nums = {3,4,1,2,7,5,6,0};
    int expected = 8;
    assertEquals(missingNumber.findMissingNumSet(nums), expected);
  }

  @Test
  public void findMissingNumGauss() {
    int[] nums = {3,4,1,2,7,5,6,0};
    int expected = 8;
    assertEquals(missingNumber.findMissingNumGauss(nums), expected);
  }
}