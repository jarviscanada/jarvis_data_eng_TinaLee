package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortTest {

  @Test
  public void mergeSort() {
    MergeSort mergeSort = new MergeSort();
    int[] arr = {7, 5, 6, 9, 3};
    int[] expected = {3, 5, 6, 7, 9};
    mergeSort.mergeSort(arr, 5);
    assertArrayEquals(arr, expected);
  }
}