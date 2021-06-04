package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackUsingQueueTest {

  @Test
  public void twoQueues() {
    StackUsingQueue.TwoQueues myStack = new StackUsingQueue.TwoQueues();
    myStack.push(1);
    myStack.push(2);
    assertEquals(myStack.top(), 2);
    assertEquals(myStack.pop(), 2);
    assertFalse(myStack.empty());
  }

  @Test
  public void oneQueue() {
    StackUsingQueue.TwoQueues myStack = new StackUsingQueue.TwoQueues();
    myStack.push(1);
    myStack.push(2);
    assertEquals(myStack.top(), 2); // return 2
    assertEquals(myStack.pop(), 2); // return 2
    assertFalse(myStack.empty());
  }
}