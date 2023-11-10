package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStack {

  Stack stack;
  Entry entFloat;
  Entry entSymbol;
  Entry entString;

  @BeforeEach
  void createStack() {
    stack = new Stack();
    entFloat = new Entry(10);
    entSymbol = new Entry(Symbol.DIVIDE);
    entString = new Entry("Test");
  }

  // Test 1 - Checking that a stack was created with a size of 0.
  // Fixed by returning 0
  @Test
  void testEmptyStack() {
    assertEquals(0, stack.size(), "Size should be 0 when creating a new stack");
  }

  // Test 2 - Checking that an item can be pushed and increments the size.
  // Fixed by creating a size variable and setting to 1 in push().
  @Test
  void testStackSizeOne() {
    stack.push(entFloat);
    assertEquals(1, stack.size(), "Size should increase to 1, when pushing on empty stack");
  }

  // Test 3 - Checking that pushing multiple items should increase the size, to 1 and then 2.
  // Fixed by incrementing size variable using size++.
  @Test
  void testStackSizeTwo() {
    stack.push(entString);
    assertEquals(1, stack.size());
    stack.push(entFloat);
    assertEquals(2, stack.size(),
        "Size should increase to 1, then 2, after pushing on empty stack");
  }

  // Test 4 - Checks that the entry at the top of the stack is the same as the last one that was
  // pushed.
  // Fixed by creating a variable for the last item pushed.
  @Test
  void testStackTopOne() throws EmptyStackException {
    stack.push(entFloat);
    assertEquals(entFloat, stack.top(), "Should return the last value inserted into the stack");
  }

  // Test 5 - Ensures that the value returned by top() changes after pushing another item to the
  // stack.
  // Fixed by updating the variable for the last item pushed.
  @Test
  void testStackTopTwo() throws EmptyStackException {
    stack.push(entFloat);
    assertEquals(entFloat, stack.top(), "Should return the first value inserted into the stack");
    stack.push(entSymbol);
    assertEquals(entSymbol, stack.top(), "Should return the second value inserted into the stack");
  }

  // Test 6 - Checks that the stack throws an EmptyStackException if trying to view an empty stack
  // Fixed by throwing an exception if the size == 0
  @Test
  void testStackTopEmpty() {
    EmptyStackException except = assertThrows(EmptyStackException.class, () -> stack.top(),
        "Stack is empty, so there should be no value to view");
    assertEquals(except.getMessage(), Stack.EMPTY_TOP);
  }

  // Test 7 - When popped, checks the returned entry matches what was pushed, and the size decreases
  // by one.
  @Test
  void testStackPopOne() throws EmptyStackException {
    stack.push(entFloat);
    assertEquals(1, stack.size(), "Check the size was increased by 1");
    assertEquals(entFloat, stack.pop(), "Pop should return the last Entry that was pushed.");
    assertEquals(0, stack.size(), "The size should decrease by one after an Entry was popped.");
  }

  // Test 8 - Checks that after popping an entry from the list, when trying to call top() it returns
  // the previous value in the stack.
  // Fixed by creating an Array List to store values in, to retain all pushed values without a fixed
  // size.
  @Test
  void testStackPopTop() throws EmptyStackException {
    stack.push(entFloat);
    stack.push(entString);
    assertEquals(entString, stack.pop(), "Pop should return the last entry that was pushed.");
    assertEquals(entFloat, stack.top(),
        "Top should return the last item in the stack (Now entFloat with entString popped)");
  }

  // Test 9 - Checks that the correct item is returned when pushing and popping more than one item
  // Fixed by returning the item from the ArrayList, and decrementing the size (used as the index).
  // Removed the value variable created in Test 4 to simplify code.
  @Test
  void testStackPopTwo() throws EmptyStackException {
    stack.push(entFloat);
    stack.push(entSymbol);
    assertEquals(entSymbol, stack.pop(),
        "Symbol was the last to be pushed, so should be the first to be popped.");
    assertEquals(entFloat, stack.pop(),
        "Float was the first to be pushed, so should be the last to be popped.");
  }

  // Test 10 - Checks that an EmptyStackException is thrown when attempting to pop from an empty
  // stack.
  @Test
  void testStackPopEmpty() {
    EmptyStackException except = assertThrows(EmptyStackException.class, () -> stack.pop(),
        "Cannot pop an empty stack, so exception should be thrown");
    assertEquals(Stack.EMPTY_POP, except.getMessage());
  }

  // Test 11 - Testing the final stack by pushing and popping, expecting an exception if popped too
  // many times.
  @Test
  void testStackPushPopEmpty() throws EmptyStackException {
    stack.push(entFloat);
    stack.push(entString);
    stack.push(entSymbol);

    assertEquals(3, stack.size(), "3 items pushed, size should be 3.");
    assertEquals(entSymbol, stack.pop(), "Expect the last item that was pushed");
    assertEquals(entString, stack.pop(), "Expect the second item that was pushed.");
    assertEquals(entFloat, stack.pop(), "Expect the first item that was pushed");
    assertThrows(EmptyStackException.class, () -> stack.pop(),
        "Fourth pop, should throw an exception as the stack is currently empty.");
  }
}
