package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DuplicateCharactersTest {

  private DuplicateCharacters duplicateCharacters;

  @Before
  public void setUp() {
    duplicateCharacters = new DuplicateCharacters();
  }

  @Test
  public void findDuplicates() {
    String s = "A black cat";
    List<Character> expected = new ArrayList<>();
    expected.add('a');
    expected.add('c');
    assertEquals(duplicateCharacters.findDuplicates(s), expected);
  }
}