package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FibonacciSequenceTest {

  private FibonacciSequence fiboSequence;

  @Before
  public void setUp() throws Exception {
    System.out.println("--@Before method runs before each @Test method");
    fiboSequence = new FibonacciSequence();
  }

  @Test
  public void fibonacciRecursion() {
    System.out.println("Test case: test fibonacciRecursion method from test class");
    int expected = 3;
    Assert.assertEquals(expected, fiboSequence.fibonacciRecursion(4));
    expected = 55;
    Assert.assertEquals(expected, fiboSequence.fibonacciRecursion(10));
  }

  @Test
  public void fibonacciDynamic() {
    System.out.println("Test case: test fibonacciDynamic method from test class");
    int expected = 2;
    Assert.assertEquals(expected, fiboSequence.fibonacciRecursion(3));
    expected = 21;
    Assert.assertEquals(expected, fiboSequence.fibonacciRecursion(8));
  }
}