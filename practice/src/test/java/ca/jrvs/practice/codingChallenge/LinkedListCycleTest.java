package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListCycleTest {

  private LinkedListCycle linkedListCycle = new LinkedListCycle();

  @Test
  public void hasCycle() {
    LinkedListCycle.ListNode five = linkedListCycle.new ListNode(5, null);
    LinkedListCycle.ListNode four = linkedListCycle.new ListNode(4, five);
    LinkedListCycle.ListNode three = linkedListCycle.new ListNode(3, four);
    LinkedListCycle.ListNode two = linkedListCycle.new ListNode(2, three);
    LinkedListCycle.ListNode one = linkedListCycle.new ListNode(1, two);
    assertFalse(linkedListCycle.hasCycle(one));
    five.next = two;
    assertTrue(linkedListCycle.hasCycle(one));
    LinkedListCycle.ListNode six = linkedListCycle.new ListNode(6, null);
    five.next = six;
    assertFalse(linkedListCycle.hasCycle(one));
    six.next = four;
    assertTrue(linkedListCycle.hasCycle(one));
  }
}