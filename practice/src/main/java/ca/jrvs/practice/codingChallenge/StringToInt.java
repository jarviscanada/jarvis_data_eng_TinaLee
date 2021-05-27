package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/String-to-Integer-atoi-e958beb990df4fa7a5a3123800aa939f
 */
public class StringToInt {

  public int parseStringToInt(String s) {
    int result = Integer.parseInt(s.trim());
    return result;
  }

  public int asciiStringToInt(String s) {
    int sum = 0;
    boolean isPositive = true;
    int count = 1;

    for (int i = s.length()-1; i >= 0; i--) {
      char curr = s.charAt(i);
      if (curr == '-') {
        isPositive = false;
        continue;
      }
      if (curr == ' ') {
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

  public static void main(String[] args) {
    StringToInt stringToInt = new StringToInt();
    int res = stringToInt.asciiStringToInt("-1934   ");
    System.out.println(res);
  }

}
