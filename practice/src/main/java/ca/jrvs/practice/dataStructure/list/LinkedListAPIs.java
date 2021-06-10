package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListAPIs {

  public static void main(String[] args) {
    //add element to last position
    List<String> numbers = new LinkedList<>();
    numbers.add("one");
    numbers.add("three");
    numbers.add("seven");

    //size of list
    int s = numbers.size();

    //get
    String fistElement = numbers.get(0);

    //remove
    String removedElement = numbers.remove(2);

    //index
    int threeIndex = numbers.indexOf("three");

    //peek
    String first = ((LinkedList<String>) numbers).poll();

    //convert list to array
    System.out.println(Arrays.toString(numbers.toArray()));
  }
}
