package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-a1ec02e222b3404c8fc51a8625c5375c
 */
public class TwoSum {
  public int[] twoSumBruteForce(int[] arr, int target) {
    int[] result = new int[2];
    for (int i = 0; i < arr.length; i++) {
      int remainder = target - arr[i];
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] == remainder && i != j) {
          return new int[]{i, j};
        }
      }
    }
    return new int[0];
  }

  public int[] twoSumSort(int[] arr, int target) {
    Arrays.sort(arr);
    int mid = arr[arr.length/2];
    for (int i = 0; i < arr.length; i++) {
      int remainder = target -arr[i];
      if (remainder < mid) {
        for (int j = i + 1; j < arr.length/2; j++) {
          if (arr[j] == remainder && j != i) {
            return new int[]{i, j};
          }
        }
      } else {
        for (int j = arr.length/2; j < arr.length; j++) {
          if (arr[j] == remainder && j != i) {
            return new int[]{i, j};
          }
        }
      }
    }
    return new int[0];
  }

  public int[] twoSumHashMap(int[] arr, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      int remainder = target - arr[i];
      if (map.containsKey(remainder) && i != map.get(remainder)) {
        return new int[]{map.get(remainder), i};
      }
      map.put(arr[i], i);
    }
    return new int[0];
  }
}
