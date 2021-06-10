package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveNthNodeTest {

  private RemoveNthNode removeNthNode = new RemoveNthNode();

  @Test
  public void removeNode() {
    RemoveNthNode.ListNode four = removeNthNode.new ListNode(4, null);
    RemoveNthNode.ListNode three = removeNthNode.new ListNode(3, four);
    RemoveNthNode.ListNode two = removeNthNode.new ListNode(2, three);
    RemoveNthNode.ListNode one = removeNthNode.new ListNode(1, two);
    removeNthNode.removeNode(one, 2);
    RemoveNthNode.ListNode test = one;
    //Expected [1, 2, 4]
    while (test != null) {
      System.out.println(test.val);
      test = test.next;
    }
  }

  @Test
  public void twoPointers() {
    RemoveNthNode.ListNode four = removeNthNode.new ListNode(4, null);
    RemoveNthNode.ListNode three = removeNthNode.new ListNode(3, four);
    RemoveNthNode.ListNode two = removeNthNode.new ListNode(2, three);
    RemoveNthNode.ListNode one = removeNthNode.new ListNode(1, two);
    removeNthNode.twoPointers(one, 2);
    RemoveNthNode.ListNode test = one;
    //Expected [1, 2, 4]
    while (test != null) {
      System.out.println(test.val);
      test = test.next;
    }
  }
}