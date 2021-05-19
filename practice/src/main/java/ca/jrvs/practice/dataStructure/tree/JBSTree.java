package ca.jrvs.practice.dataStructure.tree;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E> implements JTree<E>{
  static final class Node<E> {
    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E insert(Comparator<E> comparator, E value) {
      if (comparator.compare(value, this.value) == 0) {
        throw new IllegalArgumentException("Object already exists");
      }
      else if (comparator.compare(value, this.value) > 0) {
        if (this.right == null) {
          this.right = new Node<>(value, this);
          return value;
        } else {
          return this.right.insert(comparator, value);
        }
      } else {
        if (this.left == null) {
          this.left = new Node<>(value, this);
          return value;
        } else {
          return this.left.insert(comparator, value);
        }
      }
    }

    public E search(Comparator comparator, E value) {
      if (comparator.compare(value, this.value) == 0) {
        return value;
      } else if (comparator.compare(value, this.value) > 0 && left != null) {
        return this.right.search(comparator, value);
      } else if (right != null){
        return this.left.search(comparator, value);
      } else {
        return null;
      }
    }

    public int findHeight() {
      int left = 0;
      int right = 0;
      if (this.getLeft() != null) {
        left = this.getLeft().findHeight();
      }
      if (this.getRight() != null) {
        right = this.getRight().findHeight();
      }
      return Math.max(left, right) + 1;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return Objects.equals(value, node.value) && Objects.equals(left, node.left)
          && Objects.equals(right, node.right) && Objects
          .equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, left, right, parent);
    }
  }
  private Node<E> root;
  /**
   * The comparator used to maintain order in this tree map
   * Comparator cannot be null
   */
  private Comparator<E> comparator;

  /**
   * Create a new BST
   *
   * @param comparator the comparator that will be used to order this map.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    if (comparator == null) {
      throw new IllegalArgumentException("Comparator cannot be null");
    } else {
      this.comparator = comparator;
      root = null;
    }
  }

  /**
   * Insert an object into the tree.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    if (root == null) {
      root = new Node<>(object, null);
      return object;
    } else {
      return root.insert(comparator, object);
    }
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */
  @Override
  public E search(E object) {
    if (root == null) {
      return null;
    } else {
      return root.search(comparator, object);
    }
  }

  /**
   * Remove an object from the tree
   *
   * @param object to be removed
   * @return removed object
   * @throws IllegalArgumentException if the object does not exists
   */
  @Override
  public E remove(E object) {
    Node<E> curr = this.root;
    int flag = 0;

    if (curr == null) {
      return null;
    }
    while (curr != null && comparator.compare(curr.getValue(), object) != 0) {
      if (comparator.compare(object, curr.value) < 0) {
        curr = curr.getLeft();
        flag = 0;
      } else {
        curr = curr.getRight();
        flag = 1;
      }
    }

    if (curr == null) {
      return null;
    }

    // case 1: leaf node
    if (curr.getLeft() == null && curr.getRight() == null) {
      if (curr == root) {
        root = null;
      } else {
        if (flag == 0) {
          curr.getParent().setLeft(null);
        } else {
          curr.getParent().setRight(null);
        }
      }
      return object;
    //case 2: Node with one child
    } else if (curr.getLeft() == null) {
      if (curr == root) {
        root = root.getRight();
      } else if (flag == 0){
        curr.getParent().setLeft(curr.getRight());
      } else {
        curr.getParent().setRight(curr.getRight());
      }
      return curr.getValue();
    } else if (curr.getRight() == null) {
      if (curr == root) {
        root = root.getLeft();
      } else if (flag == 0){
        curr.getParent().setLeft(curr.getLeft());
      } else {
        curr.getParent().setRight(curr.getLeft());
      }
      return curr.getValue();
    //case3: Node with two children
    } else if (curr.getLeft() != null && curr.getRight() != null) {
      Node<E> successor = getSuccessor(curr);
      if (curr == root) {
        root = successor;
      } else if (flag == 0) {
        curr.getParent().setLeft(successor);
      } else {
        curr.getParent().setRight(successor);
      }
      successor.setLeft(curr.getLeft());
      return curr.getValue();
    }
    return object;
  }

  private Node<E> getSuccessor(Node<E> node) {
    Node<E> successor = node;
    Node<E> curr = node.getRight();
    while (curr != null) {
      successor = curr;
      curr = curr.getLeft();
    }
    if (successor != node.getRight() && curr != null) {
      curr.getParent().setLeft(successor.getRight());
      successor.setRight(node.getRight());
    }
    return successor;
  }
  /**
   * traverse the tree recursively
   *
   * @return all objects in pre-order
   */
  @Override
  public E[] preOrder() {
    return (E[]) preorderTraverse(root).toArray();
  }

  public List<E> preorderTraverse(Node root) {
    List<E> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    result.add((E) root.value);
    result.addAll(preorderTraverse(root.getLeft()));
    result.addAll(preorderTraverse(root.getRight()));
    return result;
  }
  /**
   * traverse the tree recursively
   *
   * @return all objects in-order
   */
  @Override
  public E[] inOrder() {
    return (E[]) inorderTraverse(root).toArray();
  }

  public List<E> inorderTraverse(Node root) {
    List<E> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    result.addAll(inorderTraverse(root.getLeft()));
    result.add((E) root.value);
    result.addAll(inorderTraverse(root.getRight()));
    return result;
  }
  /**
   * traverse the tree recursively
   *
   * @return all objects post-order
   */
  @Override
  public E[] postOrder() {
    return (E[]) postorderTraverse(root).toArray();
  }

  public List<E> postorderTraverse(Node root) {
    List<E> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    result.addAll(postorderTraverse(root.getLeft()));
    result.addAll(postorderTraverse(root.getRight()));
    result.add((E) root.value);
    return result;
  }
  /**
   * traverse through the tree and find out the tree height
   *
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    if (root == null) {
      return 0;
    } else {
      return root.findHeight();
    }
  }
}
