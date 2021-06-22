package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckOnlyDigitsTest {

  private CheckOnlyDigits checkOnlyDigits;

  @Before
  public void setUp() throws Exception {
    checkOnlyDigits = new CheckOnlyDigits();
  }

  @Test
  public void checkOnlyDigitAscii() {
    String s = "123000";
    assertTrue(checkOnlyDigits.checkOnlyDigitAscii(s));
    s = "123,000";
    assertFalse(checkOnlyDigits.checkOnlyDigitAscii(s));
  }

  @Test
  public void checkOnlyDigitJavaApi() {
    String s = "123000";
    assertTrue(checkOnlyDigits.checkOnlyDigitJavaApi(s));
    s = "123,000";
    assertFalse(checkOnlyDigits.checkOnlyDigitJavaApi(s));
  }

  @Test
  public void checkOnlyDigitRegex() {
    String s = "123000";
    assertTrue(checkOnlyDigits.checkOnlyDigitRegex(s));
    s = "123,000";
    assertFalse(checkOnlyDigits.checkOnlyDigitRegex(s));
  }
}