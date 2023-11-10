package application;

import java.util.ArrayList;

/**
 * A class used to create the stack data structure.
 * 
 * @author Danyal Shah
 */
public class Stack {
  private int size;
  private ArrayList<Entry> stackList;
  public static final String EMPTY_TOP = "There are no entries to view in the stack!";
  public static final String EMPTY_POP = "There are no entries to pop in the stack!";

  public Stack() {
    size = 0;
    stackList = new ArrayList<Entry>();
  }

  public int size() {
    return size;
  }

  /**
   * Adds a new item to the top of the Stack.
   * 
   * @param entry The Entry item that is to be added to the stack.
   */
  public void push(Entry entry) {
    stackList.add(entry);
    size++;
  }

  /**
   * Reads the stack, and will return the value that was last inserted into the Stack.
   * 
   * @return The last value inserted into the stack.
   * @throws EmptyStackException If trying to view an item in an empty stack.
   */
  public Entry top() throws EmptyStackException {
    checkEmptyException(EMPTY_TOP);
    // Size is used as the index, and will point to the next free index.
    return stackList.get(size - 1);
  }

  /**
   * Removes the last Entry from the stack, and returns it to the user.
   * 
   * @return The Entry previously added to the Stack.
   * @throws EmptyStackException If trying to remove from an empty stack.
   */
  public Entry pop() throws EmptyStackException {
    checkEmptyException(EMPTY_POP);
    size--;
    return stackList.remove(size);
  }

  // Written to prevent Code Duplication.
  private void checkEmptyException(String message) throws EmptyStackException {
    if (size == 0) {
      throw new EmptyStackException(message);
    }
  }
}
