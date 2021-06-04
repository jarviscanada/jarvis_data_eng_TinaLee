package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Before;
import org.junit.Test;

public class StringToIntTest {
  private StringToInt stringToInt;

  @Before
  public void setUp() throws Exception {
    stringToInt = new StringToInt();
  }

  @Test
  public void parseStringToInt() {
    assertEquals(stringToInt.parseStringToInt("42"), 42);
    assertEquals(stringToInt.parseStringToInt("   -42"), -42);
    assertEquals(stringToInt.parseStringToInt("4193 with words"), 4193);
    assertEquals(stringToInt.parseStringToInt("words and 987"), 0);
  }

  @Test
  public void asciiStringToInt() {
    assertEquals(stringToInt.parseStringToInt("42"), 42);
    assertEquals(stringToInt.parseStringToInt("   -42"), -42);
    assertEquals(stringToInt.parseStringToInt("4193 with words"), 4193);
    assertEquals(stringToInt.parseStringToInt("words and 987"), 0);
  }
}