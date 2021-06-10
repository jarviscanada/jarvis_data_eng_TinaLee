package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompareTwoMapsTest {

  private CompareTwoMaps compareMaps;

  @Before
  public void setUp() throws Exception {
    System.out.println("--@Before method runs before each @Test method");
    compareMaps = new CompareTwoMaps();
  }

  @Test
  public void compareMaps1() {
    System.out.println("Test case: test compareMaps1 method from the test class");
    boolean expected = true;
    Map<String, Integer> map1 = new HashMap<>();
    map1.put("one", 1);
    map1.put("two", 2);
    Map<String, Integer> map2 = new HashMap<>();
    map2.put("one", 1);
    map2.put("two", 2);
    Assert.assertEquals(expected, compareMaps.compareMaps1(map1, map2));
    map2.put("three", 3);
    expected = false;
    Assert.assertEquals(expected, compareMaps.compareMaps1(map1, map2));
  }

  @Test
  public void compareMaps2() {
    System.out.println("Test case: test compareMaps2 method from the test class");
    boolean expected = true;
    Map<String, Integer> map1 = new HashMap<>();
    map1.put("one", 1);
    map1.put("two", 2);
    Map<String, Integer> map2 = new HashMap<>();
    map2.put("one", 1);
    map2.put("two", 2);
    Assert.assertEquals(expected, compareMaps.compareMaps1(map1, map2));
    map2.put("three", 3);
    expected = false;
    Assert.assertEquals(expected, compareMaps.compareMaps1(map1, map2));
  }
}