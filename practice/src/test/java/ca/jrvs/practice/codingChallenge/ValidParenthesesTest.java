package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidParenthesesTest {

  @Test
  public void isValid() {
    ValidParentheses validParentheses = new ValidParentheses();
    String s = "()[]{}";
    assertTrue(validParentheses.isValid(s));
    s = "(]";
    assertFalse(validParentheses.isValid(s));
    s = "([)]";
    assertFalse(validParentheses.isValid(s));
    s = "{[]}";
    assertTrue(validParentheses.isValid(s));
  }
}