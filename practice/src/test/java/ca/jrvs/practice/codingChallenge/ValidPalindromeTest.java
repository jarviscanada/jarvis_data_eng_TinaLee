package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidPalindromeTest {
  private ValidPalindrome validPalindrome;
  @Before
  public void setUp() throws Exception {
    validPalindrome = new ValidPalindrome();
  }

  @Test
  public void twoPointers() {
    String s = "A man, a plan, a canal: Panama";
    assertTrue(validPalindrome.twoPointers(s));
    s = "race a car";
    assertFalse(validPalindrome.twoPointers(s));
  }

  @Test
  public void validRecursion() {
    String s = "A man, a plan, a canal: Panama";
    assertTrue(validPalindrome.validRecursion(s));
    s = "race a car";
    assertFalse(validPalindrome.validRecursion(s));
  }

}