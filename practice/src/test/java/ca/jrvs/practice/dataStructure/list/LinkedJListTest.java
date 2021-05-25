package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.*;

import org.junit.Test;
import sun.awt.image.ImageWatched.Link;

public class LinkedJListTest {

  @Test
  public void add() {
    JList<String> list = new LinkedJList<>();
    list.add("first");
    assertEquals(list.get(0), "first");
  }

  @Test
  public void toArray() {
    JList<String> list = new LinkedJList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    assertEquals(list.size(), 3);
    assertEquals(list.get(1), "two");
  }

  @Test
  public void size() {
    JList<String> list = new LinkedJList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    list.add("four");
    assertEquals(list.size(), 4);
  }

  @Test
  public void isEmpty() {
    JList<String> list = new LinkedJList<>();
    assertEquals(list.isEmpty(), true);
  }

  @Test
  public void indexOf() {
    JList<String> list = new LinkedJList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    assertEquals(list.indexOf("one"), 0);
    assertEquals(list.indexOf("three"), 2);
  }

  @Test
  public void contains() {
    JList<String> list = new LinkedJList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    assertEquals(list.contains("one"), true);
    assertFalse(list.contains("four"));
  }

  @Test
  public void get() {
    JList<String> list = new LinkedJList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    assertEquals(list.get(1), "two");
    assertEquals(list.get(2), "three");
  }

  @Test
  public void remove() {
    JList<String> list = new LinkedJList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    assertEquals(list.get(1), "two");
    list.remove(1);
    assertEquals(list.get(1), "three");
  }

  @Test
  public void clear() {
    JList<String> list = new LinkedJList<>();
    list.add("one");
    list.add("two");
    list.add("three");
    list.clear();
    assertTrue(list.isEmpty());
  }
}