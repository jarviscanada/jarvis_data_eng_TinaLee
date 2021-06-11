package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/LinkedList-Cycle-b4645892d87c4d9aba38d79f0b4b6e41
 */
public class LinkedListCycle {

  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public boolean hasCycle(ListNode head) {
    if (head == null) return false;

    ListNode p1 = head;
    ListNode p2 = head;

    while (p1 != null && p1.next != null) {
      p1 = p1.next.next;
      p2 = p2.next;
      if (p1 == p2) return true;
    }
    return false;
  }
}
