package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CountPrimesTest {

  private CountPrimes countPrimes;

  @Before
  public void setUp() {
    countPrimes = new CountPrimes();
  }

  @Test
  public void countPrimes() {
    int expected = 4;
    assertEquals(countPrimes.countPrimes(10), expected);
    expected = 15;
    assertEquals(countPrimes.countPrimes(50), expected);
  }
}