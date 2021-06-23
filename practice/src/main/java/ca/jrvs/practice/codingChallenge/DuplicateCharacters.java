package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateCharacters {

  public List<Character> findDuplicates(String s) {
    Set<Character> set = new HashSet<>();
    List<Character> result = new ArrayList<>();

    for (int i = 0; i < s.length(); i++) {
      Character character = s.charAt(i);
      if (Character.isLetter(character)) {
        if (set.contains(s.charAt(i))) {
          result.add(s.charAt(i));
        } else {
          set.add(s.charAt(i));
        }
      }
    }
    Collections.sort(result);
    return result;
  }

  public static void main(String[] args) {
    DuplicateCharacters duplicateCharacters = new DuplicateCharacters();
    System.out.println(duplicateCharacters.findDuplicates("A black cat"));
  }
}
