package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTypeStack {

  OpStack op;
  StrStack str;
  NumStack num;

  @BeforeEach
  void createStacks() {
    op = new OpStack(); // Pushes Symbols
    str = new StrStack(); // Pushes Strings
    num = new NumStack(); // Pushes Floats
  }

  // Test 1 - Ensure the classes exist.
  @Test
  void testCreate() {
    assertNotNull(new OpStack());
    assertNotNull(new StrStack());
    assertNotNull(new NumStack());
  }

  // Test 2 - Check Stacks were made with size 0.
  // Fixed by returning 0.
  @Test
  void testStackSizeOne() {
    assertEquals(0, op.size(), "Size should be 0 when creating a new stack");
    assertEquals(0, str.size(), "Size should be 0 when creating a new stack");
    assertEquals(0, num.size(), "Size should be 0 when creating a new stack");
  }

  // Test 3 - Check Stacks return correct amount after pushing item.
  // Fixed by calling size() from Stack class.
  @Test
  void testStackSizeTwo() {
    op.push(Symbol.DIVIDE);
    str.push("Test");
    num.push(14.0f);

    assertEquals(1, op.size(), "Size should increase to 1, when pushing on empty stack");
    assertEquals(1, str.size(), "Size should increase to 1, when pushing on empty stack");
    assertEquals(1, num.size(), "Size should increase to 1, when pushing on empty stack");
  }

  // Test 4 - Check the item that was last pushed is returned with top()
  // Implemented by returning stack.top() with the correct getter per stack
  // Example OpStack.top() returns stack.top().getSymbol()
  @Test
  void testStackTopOne() throws BadTypeException, EmptyStackException {
    op.push(Symbol.DIVIDE);
    str.push("Test");
    num.push(14.0f);

    assertEquals(Symbol.DIVIDE, op.top(), "Should return the last value inserted into the stack");
    assertEquals("Test", str.top(), "Should return the last value inserted into the stack");
    assertEquals(14.0f, num.top(), "Should return the last value inserted into the stack");
  }

  // Test 5 - Check the result of top() updates as new things are pushed.
  // Didn't need fixing.
  @Test
  void testStackTopTwo() throws BadTypeException, EmptyStackException {
    op.push(Symbol.DIVIDE);
    str.push("Test");
    num.push(14.0f);

    assertEquals(Symbol.DIVIDE, op.top(), "Should return the first value inserted into the stack");
    assertEquals("Test", str.top(), "Should return the first value inserted into the stack");
    assertEquals(14.0f, num.top(), "Should return the first value inserted into the stack");

    op.push(Symbol.MINUS);
    str.push("Testing");
    num.push(15.0f);

    assertEquals(Symbol.MINUS, op.top(), "Should return the second value inserted into the stack");
    assertEquals("Testing", str.top(), "Should return the second value inserted into the stack");
    assertEquals(15.0f, num.top(), "Should return the second value inserted into the stack");
  }

  // Test 6 - Check that EmptyStackException is thrown when attemption to run top() on an Empty
  // Stack.
  // Didn't need to be fixed.
  @Test
  void testStackTopEmpty() throws BadTypeException, EmptyStackException {
    EmptyStackException except;

    except = assertThrows(EmptyStackException.class, () -> op.top(),
        "Stack is empty, so there should be no value to view");
    assertEquals(except.getMessage(), Stack.EMPTY_TOP);
    except = assertThrows(EmptyStackException.class, () -> str.top(),
        "Stack is empty, so there should be no value to view");
    assertEquals(except.getMessage(), Stack.EMPTY_TOP);

    except = assertThrows(EmptyStackException.class, () -> num.top(),
        "Stack is empty, so there should be no value to view");
    assertEquals(except.getMessage(), Stack.EMPTY_TOP);
  }
  
  // Test 7 - Check that pop() returns the correct value after being pushed.
  // Similar fix to top(), by returning stack.top() with the correct getter
  // Example: NumStack.pop() returns stack.pop().getValue()
  @Test
  void testStackPopOne() throws BadTypeException, EmptyStackException {
    op.push(Symbol.DIVIDE);
    str.push("Test");
    num.push(14.0f);
    
    assertEquals(Symbol.DIVIDE, op.pop(), "Should return the second value pushed");
    assertEquals("Test", str.pop(), "Should return the second value pushed");
    assertEquals(14.0f, num.pop(), "Should return the second value pushed");
    
  }
  // Test 8 - Make sure pop() returns the last value that was pushed.
  // Didn't need to be fixed.
  @Test
  void testStackPopTwo() throws BadTypeException, EmptyStackException {
    // First batch push
    op.push(Symbol.MINUS);
    str.push("Testing");
    num.push(15.0f);
    // Second batch push
    op.push(Symbol.DIVIDE);
    str.push("Test");
    num.push(14.0f);
    
    assertEquals(Symbol.DIVIDE, op.pop(), "Should return the second value pushed");
    assertEquals("Test", str.pop(), "Should return the second value pushed");
    assertEquals(14.0f, num.pop(), "Should return the second value pushed");
    
    assertEquals(Symbol.MINUS, op.pop(), "Should return the first value pushed");
    assertEquals("Testing", str.pop(), "Should return the first value pushed");
    assertEquals(15.0f, num.pop(), "Should return the first value pushed");
  }
  
  // Test 9 - Make sure that an exception is thrown when trying to pop an empty stack
  // Didn't need to be fixed.
  @Test
  void testStackPopEmpty() throws BadTypeException, EmptyStackException{
    EmptyStackException except;

    except = assertThrows(EmptyStackException.class, () -> op.pop(),
        "Stack is empty, so there should be no value to remove");
    assertEquals(except.getMessage(), Stack.EMPTY_POP);
    except = assertThrows(EmptyStackException.class, () -> str.pop(),
        "Stack is empty, so there should be no value to remove");
    assertEquals(except.getMessage(), Stack.EMPTY_POP);

    except = assertThrows(EmptyStackException.class, () -> num.pop(),
        "Stack is empty, so there should be no value to remove");
    assertEquals(except.getMessage(), Stack.EMPTY_POP);
  }
}
