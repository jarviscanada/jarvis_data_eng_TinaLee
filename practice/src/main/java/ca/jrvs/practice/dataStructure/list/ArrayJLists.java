package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;

public class ArrayJLists<E> implements JList<E>{

  /**
   * Default initial capacity
   */
  private static final int DEFAULT_CAPACITY = 10;

  /**
   * The array buffer into which the elements of the ArrayList are stored.
   * The capacity of the ArrayList is the length of this array buffer.
   */
  transient Object[] elementData;

  /**
   * The size of the ArrayList (the number of elements it contains)
   */
  private int size;

  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param initialCapacity the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity
   *         is negative
   */
  public ArrayJLists(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " +
          initialCapacity);
    }
  }

  /**
   * Construct an empty list with an initial capacity of ten.
   */
  public ArrayJLists() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Appends the specified element to the ened of the list (optional
   * operation)
   *
   * Double elementData size if elementData if full.
   */
  @Override
  public boolean add(E e) {
    if (e == null) {
      throw new NullPointerException("Value is null");
    }
    if (elementData.length == this.size) {
      int newCapacity = size*2;
      elementData = Arrays.copyOf(elementData, newCapacity);
    }
    elementData[size++] = e;
    return true;
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < size; i++) {
      if (elementData[i].equals(o)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean contains(Object o) {
    if (o == null) {
      throw new NullPointerException("Object is null");
    }
    for (int i = 0; i < size; i ++) {
      if (elementData[i].equals(o)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    return (E) elementData[index];
  }

  /**
   * Removes the element at the specified position in this list.
   * Shifts any subsequent elements to the left (subtracts one from their indices);
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {inheritDoc}
   */
  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bound");
    }
    E oldElement = (E) elementData[index];
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(elementData, index+1, elementData, index,
          numMoved);
    }
    elementData[size--] = null;
    return oldElement;
  }

  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      elementData[i] = null;
    }
    size = 0;
  }
}
