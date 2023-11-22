package application;

/**
 * A class used to create a Stack Data Structure for Numbers using a previously defined Stack Class.
 * 
 * @author Danyal Shah
 */
public class NumStack {

  private Stack stack = new Stack();

  public int size() {
    return stack.size();
  }

  public void push(float num) {
    stack.push(new Entry(num));
  }

  /**
   * Reads the stack, and will return the value that was last inserted into the Stack.
   * 
   * @return The last value inserted into the stack.
   * @throws EmptyStackException If trying to view an item in an empty stack.
   */
  public float top() throws EmptyStackException {
    return checkException(stack.top());
  }

  /**
   * Removes the last Entry from the stack, and returns it to the user.
   * 
   * @return The Entry previously added to the Stack.
   * @throws EmptyStackException If trying to remove from an empty stack.
   */
  public float pop() throws EmptyStackException {
    return checkException(stack.pop());
  }
  
  private float checkException(Entry numEntry) {
    try {
      return numEntry.getValue();
    }
    catch (BadTypeException badType) {
      // This shouldn't occur. 
      // The correct getter is called directly in the return line.
      return 0f;
    }
  }
}
