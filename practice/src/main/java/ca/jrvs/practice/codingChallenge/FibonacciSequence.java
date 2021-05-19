package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-68ef9aa9db794472b00bcf6ff8fb9233
 */
public class FibonacciSequence {

  /**
   * Big-O: O(2^n)
   * Justification: The amount of operation needed, for each level of recursion,
   * grow exponentially as the depth approach n.
   */
  public int fibonacciRecursion(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    } else {
      return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);
    }
  }

  /**
   * Big-O: O(n)
   * Justification: we only calculate each F(i) once
   */
  public int fibonacciDynamic(int n) {
    int[] arr = new int[n+1];
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i-1] + arr[i-2];
    }
    return arr[n];
  }
}
