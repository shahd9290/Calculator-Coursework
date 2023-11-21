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

  public float top() throws BadTypeException, EmptyStackException {
    return stack.top().getValue();
  }

  public float pop() throws BadTypeException, EmptyStackException {
    return stack.pop().getValue();
  }

}
