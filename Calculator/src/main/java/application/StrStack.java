package application;

/**
 * A class used to create a Stack Data Structure for String using a previously defined Stack Class.
 * 
 * @author Danyal Shah
 */
public class StrStack {

  private Stack stack = new Stack();

  public int size() {
    return stack.size();
  }

  public void push(String string) {
    stack.push(new Entry(string));
  }

  /**
   * Reads the stack, and will return the value that was last inserted into the Stack.
   * 
   * @return The last value inserted into the stack.
   * @throws EmptyStackException If trying to view an item in an empty stack.
   */
  public String top() throws EmptyStackException {
    return checkException(stack.top());
  }

  /**
   * Removes the last Entry from the stack, and returns it to the user.
   * 
   * @return The Entry previously added to the Stack.
   * @throws EmptyStackException If trying to remove from an empty stack.
   */
  public String pop() throws EmptyStackException {
    return checkException(stack.pop());
  }
  
  private String checkException(Entry strEntry) {
    try {
      return strEntry.getString();
    }
    catch (BadTypeException badType) {
      // This shouldn't occur. 
      // The correct getter is called directly in the return line.
      return null;
    }
  }

}
