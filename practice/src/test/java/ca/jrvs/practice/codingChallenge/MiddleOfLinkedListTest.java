package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class MiddleOfLinkedListTest {

  private MiddleOfLinkedList middleOfLinkedList = new MiddleOfLinkedList();

  @Test
  public void findMid() {
    MiddleOfLinkedList.ListNode four = middleOfLinkedList.new ListNode(4, null);
    MiddleOfLinkedList.ListNode three = middleOfLinkedList.new ListNode(3, four);
    MiddleOfLinkedList.ListNode two = middleOfLinkedList.new ListNode(2, three);
    MiddleOfLinkedList.ListNode one = middleOfLinkedList.new ListNode(1, two);
    MiddleOfLinkedList.ListNode mid = middleOfLinkedList.findMid(one);
    assertEquals(mid.val, 3);
    MiddleOfLinkedList.ListNode five = middleOfLinkedList.new ListNode(5, null);
    four.next = five;
    mid = middleOfLinkedList.findMid(one);
    assertEquals(mid.val, 3);
  }
}