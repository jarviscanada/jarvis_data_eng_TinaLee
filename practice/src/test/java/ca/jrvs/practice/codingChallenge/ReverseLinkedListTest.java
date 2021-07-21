package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReverseLinkedListTest {

  private ReverseLinkedList reverseLinkedList;

  @Before
  public void setUp() throws Exception {
    reverseLinkedList = new ReverseLinkedList();
  }

  @Test
  public void reverseIteration() {
    ReverseLinkedList.ListNode five = reverseLinkedList.new ListNode(5, null);
    ReverseLinkedList.ListNode four = reverseLinkedList.new ListNode(4, five);
    ReverseLinkedList.ListNode three = reverseLinkedList.new ListNode(3, four);
    ReverseLinkedList.ListNode two = reverseLinkedList.new ListNode(2, three);
    ReverseLinkedList.ListNode one = reverseLinkedList.new ListNode(1, two);
    ReverseLinkedList.ListNode result = reverseLinkedList.reverseIteration(one);
    assertEquals(5, result.val);
    result = result.next;
    assertEquals(4, result.val);
  }
}