package ca.jrvs.practice.search;

import java.util.Arrays;
import java.util.Optional;


public class BinarySearch {

  /**
   * find the target index in a sorted array
   *
   * @param arr input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    return binarySearchHelper(arr, target, 0, arr.length);
  }

  public <E> Optional<Integer> binarySearchHelper(E[] arr, E target, int start, int end) {
    int mid = (start+end-1)/2;
    int comp = ((Comparable) target).compareTo(arr[mid]);
    if (comp == 0) {
      return Optional.of(mid);
    } else if (comp < 0 && start < end-1) {
      return binarySearchHelper(arr, target, start, mid);
    } else if (comp > 0 && start < end-1) {
      return binarySearchHelper(arr, target, mid+1, end);
    }
    return Optional.empty();
  }

  /**
   * find the target index in a sorted array
   *
   * @param arr input array is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not found
   */
  public <E> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int start = 0;
    int end = arr.length;
    while (start < end) {
      int mid = (start+end-1)/2;
      int comp = ((Comparable) target).compareTo(arr[mid]);
      if (comp == 0) {
        return Optional.of(mid);
      } else if (comp < 0) {
        end = mid;
      } else if (comp > 0) {
        start = mid + 1;
      }
    }
    return Optional.empty();
  }

  public static void main(String[] args) {
    BinarySearch binarySearch = new BinarySearch();
    Integer[] arr = {1, 5, 6, 7, 8};
    System.out.println(binarySearch.binarySearchIteration(arr, 6));
  }
}
