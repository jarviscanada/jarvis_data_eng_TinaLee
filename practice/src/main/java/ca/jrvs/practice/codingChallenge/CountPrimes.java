package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Count-Primes-4f9f3d6cab794794a12dc68f70c5cfea
 */
public class CountPrimes {

  /**
   * Big-O: O(n^2)
   * Justification: nested for loop
   */
  public int countPrimes(int n) {
    int count = 0;
    boolean flag = false;
    if (n == 0 | n == 1) return 0;

    for (int j = 2; j <= n; j++) {
      if (isPrime(j)) {
        count++;
      }
    }
    return count;
  }

  public boolean isPrime(int n) {
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    CountPrimes countPrimes = new CountPrimes();
    System.out.println(countPrimes.countPrimes(50));
  }
}
