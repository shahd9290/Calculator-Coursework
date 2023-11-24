package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPostFix {

  RevPolishCalc rpc;
  
  @BeforeEach
  void createRPC() {
    rpc = new RevPolishCalc();
  }
  
  // Test 1 - Create Class
  @Test
  void testExists() {
    assertNotNull(new RevPolishCalc());
  }

  // Test 2 - Check if correct value is returned.
  // Fixed by hard coding return value.
  @Test
  void testAdditionOne() throws EmptyStackException {
    assertEquals(3, rpc.evaluate("1 2 +"));
    // Test 3 - Check the correct value is returned for different expression
    // Fixed by using a loop to read each integer in a string, and end when it hits the +.
    assertEquals(5, rpc.evaluate("2 3 +"));
  }
  
  // Test 4 - Check that extended expressions can be evaluated.
  // Fixed by refactoring method. Changed While condition for has Next() over NextInt().
  @Test
  void testAdditionTwo() throws EmptyStackException {
    assertEquals(12, rpc.evaluate("6 4 2 + +"));
    assertEquals(8, rpc.evaluate("1 5 2 + +"));
  }
  
  // Test 5 - Test Complex Addition
  // Passed First Try.
  @Test
  void testAdditionThree() throws EmptyStackException {
    assertEquals(rpc.evaluate("7 3 + 15 +"), 25);
  }
}
