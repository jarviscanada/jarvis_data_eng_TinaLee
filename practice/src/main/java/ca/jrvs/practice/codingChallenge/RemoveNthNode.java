package ca.jrvs.practice.codingChallenge;

public class RemoveNthNode {

  //Definition for singly-linked list.
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  public ListNode removeNode(ListNode head, int n) {
    int counter1 = 0;
    ListNode p1 = head;
    while (p1.next != null) {
      counter1++;
      p1 = p1.next;
    }

    int position = counter1 - n;
    ListNode p2 = head;
    if (position == 0) {
      return head.next;
    }
    while (position > 0) {
      p2 = p2.next;
      position--;
    }

    p2.next = p2.next.next;

    return head;
  }

  public ListNode twoPointers(ListNode head, int n) {
    ListNode p1 = head;
    ListNode p2 = head;

    for (int i = 0; i <= n; i++) {
      p1 = p1.next;
    }

    while (p1 != null) {
      p1 = p1.next;
      p2 = p2.next;
    }

    p2.next = p2.next.next;

    return head;
  }

}
