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

  public String top() throws BadTypeException, EmptyStackException {
    return stack.top().getString();
  }

  public String pop() throws BadTypeException, EmptyStackException {
    return stack.pop().getString();
  }

}
