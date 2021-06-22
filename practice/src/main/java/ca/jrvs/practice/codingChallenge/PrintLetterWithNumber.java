package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Print-letter-with-number-c2ac527990a24d89ad1d334965f9cfc6
 */
public class PrintLetterWithNumber {

  /**
   * Big-O: O(n) loop through string with length n
   */
  public String printWithNum(String s) {
    // check if string contains only letters
    if (!s.matches("[a-zA-z]+")) {
      throw new IllegalArgumentException("String should only contain letters");
    }

    //build string
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      int ascii = (int) s.charAt(i);
      int num;
      if (Character.isUpperCase(s.charAt(i))) {
        num = ascii % (int) 'A' + 27;
      } else {
        num = ascii % (int) 'a' + 1;
      }
      builder.append(s.charAt(i));
      builder.append(num);
    }
    return builder.toString();
  }
}
