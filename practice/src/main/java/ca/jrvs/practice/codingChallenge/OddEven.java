package ca.jrvs.practice.codingChallenge;

public class OddEven {

  /**
   * Big-O: O(1);
   * Justification: it's an arithmetic operation
   */
  public String oddEvenMod(int i) {
    return i % 2 == 0 ? "even" : "odd";
  }

  /**
   * Big-O: 1
   * Justification: bitwise operation
   */
  public String oddEvenBit(int i) {
    return (i & 1) == 1 ? "odd" : "even";
  }
}
