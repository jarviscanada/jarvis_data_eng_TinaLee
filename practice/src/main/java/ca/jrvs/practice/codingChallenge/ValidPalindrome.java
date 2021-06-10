package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-5b51b2b0a4e048c9af745a41a539194c
 */
public class ValidPalindrome {

  /**
   * Big-O: O(n)
   * Justification: replaceAll at most O(n) iterate through entire String the replace characters.
   */
  public boolean twoPointers(String s) {
    String onlyLetter = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
    int mid = onlyLetter.length()/2;
    int pointer1 = 0;
    int pointer2 = onlyLetter.length() - 1;

    while (pointer1 < mid) {
      if (onlyLetter.charAt(pointer1) != onlyLetter.charAt(pointer2)) {
        return false;
      }
      pointer1++;
      pointer2--;
    }
    return true;
  }

  /**
   * Big-O: O(n)
   * Justification: replaceAll iterate through String with length n and
   * recursion helper gets called at most n/2 times.
   */
  public boolean validRecursion(String s) {
    String onlyLetter = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
    return recursionHelper(onlyLetter);
  }

  public boolean recursionHelper(String s){
    if (s.length() <= 1) {
      return true;
    }
    if (s.charAt(0) != s.charAt(s.length()-1)) {
      return false;
    }
    return recursionHelper(s.substring(1, s.length() -1));
  }
}
