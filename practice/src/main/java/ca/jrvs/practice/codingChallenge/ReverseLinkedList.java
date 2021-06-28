package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Reverse-Linked-List-4150bbbcda4b4b5da18018a5cbc0c6ac
 */
public class ReverseLinkedList {

  public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

  /**
   * Big-O: O(n)
   * Justification: while-loop
   */
  public ListNode reverseIteration(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode tmp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = tmp;
    }
    return prev;
  }

}
