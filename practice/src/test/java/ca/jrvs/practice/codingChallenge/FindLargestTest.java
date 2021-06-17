package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FindLargestTest {

  private FindLargest findLargest;

  @Before
  public void setUp() throws Exception {
    findLargest = new FindLargest();
  }

  @Test
  public void findLargestLoop() {
    List<Integer> l = Arrays.asList(12, 23, 54, 2, 6);
    int max = findLargest.findLargestLoop(l);
    assertEquals(54, max);
  }

  @Test
  public void findLargestStream() {
    List<Integer> l = Arrays.asList(12, 23, 54, 2, 6);
    int max = findLargest.findLargestStream(l);
    assertEquals(54, max);
  }

  @Test
  public void findLargest() {
    List<Integer> l = Arrays.asList(12, 23, 54, 2, 6);
    int max = findLargest.findLargest(l);
    assertEquals(54, max);
  }
}