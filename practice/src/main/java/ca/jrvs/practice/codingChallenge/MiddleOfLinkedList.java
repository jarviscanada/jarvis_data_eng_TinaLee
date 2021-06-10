package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-e9bd584bd3c54ac889e7face9971e4dc
 */
public class MiddleOfLinkedList {

  //Definition for singly-linked list.
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode findMid(ListNode head) {
    ListNode p1 = head;
    ListNode p2 = head;

    while (p1 != null && p1.next != null) {
      p1 = p1.next.next;
      p2 = p2.next;
    }

    return p2;
  }
}
