package application;

/**
 * 
 */
public class NumStack {
  
  Stack stack = new Stack();

  public int size() {
    return stack.size();
  }

  public void push(float num) {
    stack.push(new Entry(num));
  }

  public float top() throws BadTypeException, EmptyStackException {
    return stack.top().getValue();
  }

}
