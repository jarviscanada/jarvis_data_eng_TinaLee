package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {

  private QuickSort quickSort;

  @Before
  public void setUp() throws Exception {
    quickSort = new QuickSort();
  }

  @Test
  public void quickSort() {
    int[] arr = {2, 4, 6, 9, 5};
    int[] expected = {2, 4, 5, 6, 9};
    quickSort.quickSort(arr, 0, 4);
    assertArrayEquals(arr, expected);
  }
}