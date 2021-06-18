package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-17dab695f2d34f22ab182eb695cac51b
 */
public class CheckOnlyDigits {

  /**
   * Big-O: O(n)
   */
  public boolean checkOnlyDigitAscii(String s) {
    for (int i = 0; i < s.length(); i++) {
      int asciiNum = (int) s.charAt(i);
      if (asciiNum < 48 || asciiNum > 57) {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O: O(n)
   */
  public boolean checkOnlyDigitJavaApi(String s) {
    if (s.charAt(0) == '-') {
      return false;
    }

    int num;
    try {
      num = Integer.valueOf(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Big-O: O(n)
   * check each character one by one
   */
  public boolean checkOnlyDigitRegex(String s) {
    String regex = "[0-9]+";
    return s.matches(regex);
  }
}
