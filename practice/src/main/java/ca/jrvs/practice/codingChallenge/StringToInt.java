package ca.jrvs.practice.codingChallenge;

import java.time.temporal.ChronoField;

/**
 * ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-e958beb990df4fa7a5a3123800aa939f
 */
public class StringToInt {

  /**
   * Big-O:
   * time O(n) Iterate through String s at most once
   * space O(1)
   */
  public int parseStringToInt(String s) {
    String noSpace = s.trim();
    int index = 0;
    boolean isPositive = true;
    if (noSpace.charAt(index) == '-' || noSpace.charAt(index) == '+') {
      if (noSpace.charAt(index) == '-') {
        isPositive = false;
      }
      index++;
    }

    while (index < noSpace.length() && Character.isDigit(noSpace.charAt(index))) {
      index++;;
    }

    String onlyDigit = noSpace.substring(0, index);

    if (onlyDigit.length() == 0) {
      return 0;
    }

    try {
      int result = Integer.parseInt(onlyDigit);
      return result;
    } catch (NumberFormatException e) {
      if (isPositive == false) {
        return Integer.MIN_VALUE;
      } else {
        return Integer.MAX_VALUE;
      }
    }
  }

  /**
   * Big-O:
   * time O(n) Iterate through String s at most once
   * space O(1)
   *
   */
  public int asciiStringToInt(String s) {
    int sum = 0;
    boolean isPositive = true;
    int count = 1;
    int index = 0;

    String noSpace = s.trim();
    if (noSpace.charAt(index) == '-' || noSpace.charAt(index) == '+') {
      if (noSpace.charAt(index) == '-') {
        isPositive = false;
      }
      index++;
    }

    while (index < noSpace.length() && Character.isDigit(noSpace.charAt(index))) {
      index++;;
    }

    String onlyDigit = noSpace.substring(0, index);
    if (onlyDigit.length() == 0) {
      return 0;
    }

    for (int i = onlyDigit.length()-1; i >= 0; i--) {
      char curr = onlyDigit.charAt(i);
      if (curr == '-' || curr == '+') {
        continue;
      }
      int ascii = (int) s.charAt(i);
      sum += (ascii % 48) * count;
      count *= 10;
    }

    if (!isPositive) {
      sum *= -1;
    }

    return sum;
  }
}
