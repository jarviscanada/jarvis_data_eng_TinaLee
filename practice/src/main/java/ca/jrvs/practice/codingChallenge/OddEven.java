package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-3e5999e3c5824e2c86f63fde5eae1824
 */
public class OddEven {

  /**
   * Big-O: O(1);
   * Justification: it's an arithmetic operation
   */
  public String oddEvenMod(int i) {
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Big-O: O(1)
   * Justification: bitwise operation
   */
  public String oddEvenBit(int i) {
    return (i & 1) == 1 ? "odd" : "even";
  }
}
