package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrintLetterWithNumberTest {

  private PrintLetterWithNumber printLetterWithNumber;

  @Before
  public void setUp() throws Exception {
    printLetterWithNumber = new PrintLetterWithNumber();
  }

  @Test
  public void printWithNum() {
    String s = "abcee";
    String expected = "a1b2c3e5e5";
    assertEquals(printLetterWithNumber.printWithNum(s), expected);
    s = "aBcee";
    expected = "a1B28c3e5e5";
    assertEquals(printLetterWithNumber.printWithNum(s), expected);
  }
}