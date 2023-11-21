package application;

/**
 * 
 */
public class StrStack {
  
  Stack stack = new Stack();

  public int size() {
    return stack.size();
  }

  public void push(String string) {
    stack.push(new Entry(string));
  }

  public String top() throws BadTypeException, EmptyStackException {
    return stack.top().getString();
  }

}
