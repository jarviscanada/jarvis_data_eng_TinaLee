package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ticket: https://www.notion.so/jarvisdev/Find-Largest-Smallest-77167c31b9104983b34fe702f220922c
 */
public class FindLargest {

  /**
   * Big-O: O(n) loop through n times where n equals size of List
   */
  public int findLargestLoop(List<Integer> l) {
    int max = Integer.MIN_VALUE;
    for (Integer num: l) {
      if (num > max) {
        max = num;
      }
    }
    return max;
  }

  /**
   * Big-O: O(n) map each element in List to int (n times)
   */
  public int findLargestStream(List<Integer> l) {
    int max = l.stream()
        .mapToInt(num -> num)
        .max()
        .getAsInt();
    return max;
  }

  /**
   * Big-O: O(n) Collections.max() uses while loop
   */
  public int findLargest(List<Integer> l) {
    return Collections.max(l);
  }
}
