package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-e179fa3736334137b7ab81b1d047a381
 */
public class CompareTwoMaps {

  /**
   * Big-O: O(n)
   * Justification: Compare every key and value pairs
   */
  public <K,V> boolean compareMaps1(Map<K,V> m1, Map<K,V> m2) {
    return m1.equals(m2);
  }

  /**
   * Big-O: O(n)
   * Justification: Compare every key and value pairs
   */
  public <K,V> boolean compareMaps2(Map<K,V> m1, Map<K,V> m2) {
    if (m1 == m2) {
      return true;
    } else if (m1 == null || m2 == null || m1.size() != m2.size()) {
      return false;
    } else {
      return m1.values() == m2.values() && m1.keySet() == m2.keySet();
    }
  }
}
