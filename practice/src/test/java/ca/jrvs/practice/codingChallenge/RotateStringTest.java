package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RotateStringTest {

  private RotateString rotateString;

  @Test
  public void rotateString() {
    rotateString = new RotateString();
    assertTrue(rotateString.rotateString("abcde", "deabc"));
    assertFalse(rotateString.rotateString("abcde", "dfref"));
  }
}