package application;

/**
 * 
 */
public class OpStack {
  Stack stack = new Stack();
  
  public int size() {
    return stack.size();
  }

  public void push(Symbol symbol) {
    stack.push(new Entry(symbol));
  }

  public Symbol top() throws BadTypeException, EmptyStackException {
    return stack.top().getSymbol();
  }

  public Symbol pop() throws BadTypeException, EmptyStackException {
    return stack.pop().getSymbol();
  }

}
