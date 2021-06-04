package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-17581004c843439baf6018a1f9a3f4a8
 */
public class StackUsingQueue {

  /**
   * Big-O:
   * time O(n) at most add n elements to queue 2
   * space O(1)
   */
  public static class TwoQueues {
    private Queue<Integer> queue1;
    private int top;

    public TwoQueues() {
      this.queue1 = new LinkedList();
    }

    /** Push element x onto stack. */
    public void push(int x) {
      queue1.add(x);
      top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
      if (queue1.size() == 0) {
        return -1;
      }
      Queue<Integer> queue2 = new LinkedList();
      int removed = top;
      while (queue1.peek() != removed) {
        top = queue1.remove();
        queue2.add(top);
      }
      queue1 = queue2;
      return removed;
    }

    /** Get the top element. */
    public int top() {
      return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
      return queue1.size() == 0;
    }
  }

  /**
   * Big-O:
   * time O(n) at most add n elements to end of queue 1;
   * space O(1)
   */
  public static class OneQueue {
    private Queue<Integer> queue1;
    private int top;

    public OneQueue() {
      this.queue1 = new LinkedList();
    }

    /** Push element x onto stack. */
    public void push(int x) {
      queue1.add(x);
      top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
      if (queue1.size() == 0) {
        return -1;
      }
      int removed = top;
      while (queue1.peek() != removed) {
        top = queue1.remove();
        queue1.add(top);
      }
      return queue1.remove();
    }

    /** Get the top element. */
    public int top() {
      return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
      return queue1.size() == 0;
    }

  }
}
