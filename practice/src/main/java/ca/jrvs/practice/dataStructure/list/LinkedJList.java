package ca.jrvs.practice.dataStructure.list;

import java.util.Collection;



public class LinkedJList<E> implements JList<E>{

  transient int size = 0;

  transient Node<E> first;

  transient Node<E> last;

  private static  class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }
  /**
   * Appends the specified element to the end of this list (optional operation).
   *
   * @param e element to be appended to this list
   * @return <tt>true</tt> (as specified by {@link Collection#add})
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean add(E e) {
    if (e == null) {
      throw new NullPointerException("Object is null");
    }
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null) {
      first = newNode;
    } else {
      l.next = newNode;
    }
    size++;
    return true;
  }

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to
   * last element).
   *
   * <p>This method acts as bridge between array-based and collection-based
   * APIs
   *
   * @return an array containing all of the elements in this list in proper sequence
   */
  @Override
  public Object[] toArray() {
    Object[] arr = new Object[size];
    int i = 0;
    Node<E> tmp = first;
    while (tmp != null) {
      arr[i] = tmp.item;
      tmp = tmp.next;
      i++;
    }
    return arr;
  }

  /**
   * The size of this List (the number of elements it contains)
   *
   * @return the size of this list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns <tt>true</tt> if this list contains no elements.
   *
   * @return <tt>true</tt> if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the index of the first occurence of the specified element in this list, or -1 of this
   * list does not contain the element. More formally, returns the lowest index <tt>i</tt> such
   * that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
   * or -1 if there is not such index.
   *
   * @param o
   */
  @Override
  public int indexOf(Object o) {
    int i = 0;
    Node<E> tmp = first;
    while (tmp != null) {
      if (o.equals(tmp.item)) {
        return i;
      }
      i++;
      tmp = tmp.next;
    }
    return -1;
  }

  /**
   * Returns <tt>true</tt> if this list contains the specified element. More formally, returns
   * <tt>true</tt> if and only if this list contains at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presense in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean contains(Object o) {
    if (o == null) {
      throw new NullPointerException("Object is null");
    }
    Node<E> tmp = first;
    while (tmp != null) {
      if (o.equals(tmp.item)) {
        return true;
      }
      tmp = tmp.next;
    }
    return false;
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range (<tt>index &lt; 0 || index &gt;=
   *                                   size()</tt>)
   */
  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index number");
    }
    Node<E> tmp;
    if (index < (size >> 1)) {
      tmp = first;
      for (int i = 0; i < index; i++) {
        tmp = tmp.next;
      }
    } else {
      tmp = last;
      for (int i = size - 1; i > index; i--) {
        tmp = tmp.prev;
      }
    }
    return (E) tmp.item;
  }

  /**
   * Removes the element at the specified position in this list. Shifts any subsequent elements to
   * the left (subtracts one from their indices).
   *
   * @param index the index of the element to be removed
   * @return the element that was remove from the list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index number");
    }

    int count = 0;
    Node<E> curr = first;
    while (count < index) {
      curr = curr.next;
      count++;
    }

    final E element = curr.item;
    final Node<E> next = curr.next;
    final Node<E> prev = curr.prev;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
      curr.prev = null;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
      curr.next = null;
    }
    curr.item = null;
    size--;
    return element;
  }

  /**
   * Removes all of the elements from this list (optional operation). The list will be empty after
   * this call returns.
   */
  @Override
  public void clear() {
    for (Node<E> x = first; x != null; ) {
      Node<E> next = x.next;
      x.item = null;
      x.prev = null;
      x.next = null;
      x = next;
    }
    first = last = null;
    size = 0;
  }
}
