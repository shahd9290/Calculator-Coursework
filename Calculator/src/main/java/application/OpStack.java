package application;

/**
 * A class used to create a Stack Data Structure for Operator Symbols using a previously defined
 * Stack Class.
 * 
 * @author Danyal Shah
 */
public class OpStack {
  private Stack stack = new Stack();

  public int size() {
    return stack.size();
  }

  public void push(Symbol symbol) {
    stack.push(new Entry(symbol));
  }

  /**
   * Reads the stack, and will return the value that was last inserted into the Stack.
   * 
   * @return The last value inserted into the stack.
   * @throws EmptyStackException If trying to view an item in an empty stack.
   */
  public Symbol top() throws EmptyStackException {
    return checkException(stack.top());
  }

  /**
   * Removes the last Entry from the stack, and returns it to the user.
   * 
   * @return The Entry previously added to the Stack.
   * @throws EmptyStackException If trying to remove from an empty stack.
   */
  public Symbol pop() throws EmptyStackException {
    return checkException(stack.pop());
  }
  
  private Symbol checkException(Entry strEntry) {
    try {
      return strEntry.getSymbol();
    }
    catch (BadTypeException badType) {
      // This shouldn't occur. 
      // The correct getter is called directly in the return line.
      return Symbol.INVALID;
    }
  }

}
