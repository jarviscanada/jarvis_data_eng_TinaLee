package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueUsingStackTest {

  @Test
  public void twoStacks() {
    QueueUsingStack.MyQueue myQueue = new QueueUsingStack.MyQueue();
    myQueue.push(1);
    myQueue.push(2);
    assertEquals(myQueue.peek(),1);
    assertEquals(myQueue.pop(), 1);
    assertFalse(myQueue.empty());
  }

  @Test
  public void twoStacksAmortized() {
    QueueUsingStack.MyAmortizedQueue myQueue = new QueueUsingStack.MyAmortizedQueue();
    myQueue.push(1);
    myQueue.push(2);
    assertEquals(myQueue.peek(),1);
    assertEquals(myQueue.pop(), 1);
    assertFalse(myQueue.empty());
  }
}