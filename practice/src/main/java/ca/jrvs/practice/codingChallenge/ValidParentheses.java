package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-52f3240d7c13487f81b5455f52c4d3e5
 */
public class ValidParentheses {

  /**
   * Big-O:
   * time O(n) iterate through the string of length n
   * space O(n) at most store n brackets on Stack
   */
  public boolean isValid(String s) {
    Map<String, String> map = new HashMap<>();
    map.put("(", ")");
    map.put(")", "(");
    map.put("{", "}");
    map.put("}", "{");
    map.put("[", "]");
    map.put("]", "[");
    Stack<String> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      String curr = Character.toString(s.charAt(i));
      if (stack.isEmpty()) {
        stack.push(curr);
        continue;
      }
      if (stack.peek().equals(map.get(curr))) {
        stack.pop();
      } else {
        stack.push(curr);
      }
    }
    return stack.isEmpty();
  }
}
