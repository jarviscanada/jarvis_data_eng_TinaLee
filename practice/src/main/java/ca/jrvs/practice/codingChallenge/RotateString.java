package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Rotate-String-ced9bcef39984236ba6288acc7181519
 */
public class RotateString {

  /**
   * Big-O: O(n)
   * Justification: for loop runs at most n (length of string s) times
   */
  public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) {
      return false;
    }

    for (int i = 0; i < s.length(); i++) {
      if (s.equals(goal)) {
        return true;
      }
      char first = s.charAt(0);
      s = s.substring(1);
      s += first;
    }
    return false;
  }
}
