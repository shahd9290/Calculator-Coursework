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

  // Test 2 - Check if correct value is returned from Addition.
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
    assertEquals(25, rpc.evaluate("7 3 + 15 +"));
  }

  // Test 6 - Check if correct value is returned from Subtraction.
  // Fixed with a switch/case for the operator + calling the next iteration
  // of the while loop after each calculation.
  // Without this it would've called another incorrect case.
  @Test
  void testSubtractionOne() throws EmptyStackException {
    assertEquals(2, rpc.evaluate("5 3 -"));
    assertEquals(12, rpc.evaluate("15 6 3 - -"));

  }

  // Test 7 - Check an expression can be evaluated with both + and -
  @Test
  void testSubtractionTwo() throws EmptyStackException {
    assertEquals(15, rpc.evaluate("12 8 + 5 -"));
  }

  // Test 8 - Test Multiply
  @Test
  void testMultiplyOne() throws EmptyStackException {
    assertEquals(24, rpc.evaluate("8 3 *"));
    assertEquals(72, rpc.evaluate("4 2 9 * *"));
  }
  
  // Test 9 - Test Multiply Addition and Subtraction
  @Test
  void testMultiplyTwo() throws EmptyStackException {
    assertEquals(rpc.evaluate("12 5 7 2 - + *"), 120);
  }
}
