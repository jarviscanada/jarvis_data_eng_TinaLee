package ca.jrvs.practice.search;

import static org.junit.Assert.*;

import java.util.Optional;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinarySearchTest {

  private BinarySearch binarySearch;

  @Before
  public void setUp() {
    binarySearch = new BinarySearch();
  }

  @Test
  public void binarySearchRecursion() {
    Integer[] arr = {1, 5, 6, 7, 8};
    assertEquals(binarySearch.binarySearchRecursion(arr, 6), Optional.of(2));
    assertEquals(binarySearch.binarySearchRecursion(arr, 9), Optional.empty());
  }

  @Test
  public void binarySearchIteration() {
    Integer[] arr = {1, 5, 6, 7, 8};
    assertEquals(binarySearch.binarySearchIteration(arr, 6), Optional.of(2));
    assertEquals(binarySearch.binarySearchIteration(arr, 9), Optional.empty());
  }
}