package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidAnagramTest {

  private ValidAnagram validAnagram;

  @Before
  public void setUp() throws Exception {
    validAnagram = new ValidAnagram();
  }

  @Test
  public void validAnagramSort() {
    String s = "anagram";
    String t = "nagaram";
    assertTrue(validAnagram.validAnagramSort(s, t));
    s = "rat";
    t = "cat";
    assertFalse(validAnagram.validAnagramSort(s, t));
  }

  @Test
  public void validAnagramTable() {
    String s = "anagram";
    String t = "nagaram";
    assertTrue(validAnagram.validAnagramTable(s, t));
    s = "rat";
    t = "cat";
    assertFalse(validAnagram.validAnagramTable(s, t));
  }
}