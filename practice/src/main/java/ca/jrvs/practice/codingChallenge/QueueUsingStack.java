package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class QueueUsingStack {
  public static class MyQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
      while (!stack1.isEmpty()) {
        stack2.add(stack1.pop());
      }
      stack1.add(x);
      while (!stack2.isEmpty()) {
        stack1.add(stack2.pop());
      }
      System.out.println(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      return stack1.pop();
    }

    /** Get the front element. */
    public int peek() {
      return stack1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return stack1.size() == 0 && stack2.size() == 0;
    }
  }

  public static class MyAmortizedQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    private int front;

    /** Initialize your data structure here. */
    public MyAmortizedQueue() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
      if (stack1.isEmpty()) {
        front = x;
      }
      stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      if (!stack2.isEmpty()){
        return stack2.pop();
      } else {
        while (!stack1.isEmpty()) {
          stack2.push(stack1.pop());
        }
        return stack2.pop();
      }
    }

    /** Get the front element. */
    public int peek() {
      return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return stack1.size() == 0 && stack2.size() == 0;
    }
  }
}
