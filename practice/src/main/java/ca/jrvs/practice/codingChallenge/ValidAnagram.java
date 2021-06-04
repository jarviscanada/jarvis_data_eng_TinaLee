package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Anagram-cbb88b7d460640bc820dca8cdceb7800
 */
public class ValidAnagram {

  /**
   * Big-O
   * time O(nlogn): sorting time
   * space O(n)
   */
  public boolean validAnagramSort(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    char[] arr1 = s.toCharArray();
    char[] arr2 = t.toCharArray();
    Arrays.sort(arr1);
    Arrays.sort(arr2);

    return Arrays.equals(arr1, arr2);
  }

  /**
   * Big-O
   * time O(n): for loop iterates n times
   * space O(1): table array size is always the same not matter how large n is.
   */
  public boolean validAnagramTable(String s, String t) {
    if (s.length() != t.length())
      return false;

    int[] table = new int[26];
    for (int i = 0; i < s.length(); i++) {
      table[s.charAt(i) - 'a'] += 1;
    }

    for (int j = 0; j < t.length(); j++) {
      int tmp = table[t.charAt(j) - 'a'];
      tmp -= 1;
      if (tmp < 0)
        return false;
    }

    return true;
  }
}
