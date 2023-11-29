package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  void testAdditionOne() throws InvalidExpression {
    assertEquals(3, rpc.evaluate("1 2 +"));
    // Test 3 - Check the correct value is returned for different expression
    // Fixed by using a loop to read each integer in a string, and end when it hits the +.
    assertEquals(5, rpc.evaluate("2 3 +"));
  }

  // Test 4 - Check that extended expressions can be evaluated.
  // Fixed by refactoring method. Changed While condition for has Next() over NextInt().
  @Test
  void testAdditionTwo() throws InvalidExpression {
    assertEquals(12, rpc.evaluate("6 4 2 + +"));
    assertEquals(8, rpc.evaluate("1 5 2 + +"));
  }

  // Test 5 - Test Complex Addition
  // Passed First Try.
  @Test
  void testAdditionThree() throws InvalidExpression {
    assertEquals(25, rpc.evaluate("7 3 + 15 +"));
  }

  // Test 6 - Check if correct value is returned from Subtraction.
  // Fixed with a switch/case for the operator + calling the next iteration
  // of the while loop after each calculation.
  // Without this it would've called another incorrect case.
  @Test
  void testSubtractionOne() throws InvalidExpression {
    assertEquals(2, rpc.evaluate("5 3 -"));
    assertEquals(12, rpc.evaluate("15 6 3 - -"));

  }

  // Test 7 - Check an expression can be evaluated with both + and -
  @Test
  void testSubtractionTwo() throws InvalidExpression {
    assertEquals(15, rpc.evaluate("12 8 + 5 -"));
  }

  // Test 8 - Test Multiply
  @Test
  void testMultiplyOne() throws InvalidExpression {
    assertEquals(24, rpc.evaluate("8 3 *"));
    assertEquals(72, rpc.evaluate("4 2 9 * *"));
  }

  // Test 9 - Test Multiply, Addition and Subtraction
  @Test
  void testMultiplyTwo() throws InvalidExpression {
    assertEquals(rpc.evaluate("12 5 7 2 - + *"), 120);
  }

  // Test 10 - Test Division
  @Test
  void testDivisionOne() throws InvalidExpression {
    assertEquals(rpc.evaluate("15 3 / "), 5);
  }

  // Test 11 - Test all four operators
  @Test
  void testDivisionTwo() throws InvalidExpression {
    assertEquals(rpc.evaluate("25 4 8 10 5 / * + -"), 5);
  }

  // Test 12 - Test Division by Zero
  // Fixed by throwing InvalidExpression
  @Test
  void testDivisionThree() {
    InvalidExpression except = assertThrows(InvalidExpression.class, () -> rpc.evaluate("1 0 /"));
    assertEquals(except.getMessage(), RevPolishCalc.DIVZERO_MSG);
  }

  // Test 13 - Test Invalid Expressions (No operators)
  // Fixed by using a boolean variable to determine whether a calculation has taken place.
  // If not, then an exception is thrown at the end when trying to return an answer.
  @Test
  void testInvalidOne() throws InvalidExpression {
    InvalidExpression except = assertThrows(InvalidExpression.class, () -> rpc.evaluate("1 2 3"));
    assertEquals(except.getMessage(), RevPolishCalc.INVALID_MSG);
  }

  // Test 14 - Test Invalid Expressions (Too Many Operators)
  // Fixed by throwing an InvalidExpression when an EmptyStackException occurs.
  @Test
  void testInvalidTwo() throws InvalidExpression {
    InvalidExpression except = assertThrows(InvalidExpression.class, () -> rpc.evaluate("1 2 + +"));
    assertEquals(except.getMessage(), RevPolishCalc.INVALID_MSG);
  }

  // Test 15 - Test Invalid Expressions (Only Operators)
  @Test
  void testInvalidThree() throws InvalidExpression {
    InvalidExpression except = assertThrows(InvalidExpression.class, () -> rpc.evaluate("+ + +"));
    assertEquals(except.getMessage(), RevPolishCalc.INVALID_MSG);
  }
  
  // Test 16 - Test Invalid Expressions (Unknown Symbol)
  // Throws an EmptyStackException and gets handled immediately. No fix required.
  @Test
  void testInvalidFour() throws InvalidExpression {
    InvalidExpression except = assertThrows(InvalidExpression.class, () -> rpc.evaluate("1 2 + ("));
    assertEquals(except.getMessage(), RevPolishCalc.INVALID_MSG);
    InvalidExpression except2 = assertThrows(InvalidExpression.class, () -> rpc.evaluate("1 2 ("));
    assertEquals(except2.getMessage(), RevPolishCalc.INVALID_MSG);
  }
  
  // Test 17 - Testing Arithmetics using Decimal Values.
  // Fixed by Replacing NextInt with NextFloat where necessary.
  @Test
  void testDecimalArithmetic() throws InvalidExpression{
    assertEquals(5f, rpc.evaluate("2.5 2.5 +"));
    assertEquals(0f, rpc.evaluate("2.5 2.5 -"));
    assertEquals(5f, rpc.evaluate("2.5 2 *"));
    assertEquals(1.2f, rpc.evaluate("2.4 2 /"));

  }
  
  // Test 18 - Testing Overflow (when value is >= Float.MAX_VALUE
  // Added if clause in the program to detect.
  @Test
  void testOverflow() throws InvalidExpression {
    String expression = String.valueOf(Float.MAX_VALUE);
    InvalidExpression except = assertThrows(InvalidExpression.class, () -> rpc.evaluate(expression + " 1 +"));
    assertEquals(RevPolishCalc.OVERFLOW_MSG, except.getMessage());
  }
  
  // Test 19 - Test Single Value Input
  @Test
  void testSingle() throws InvalidExpression {
    assertEquals(1.0, rpc.evaluate("1.0"));
    assertEquals(1, rpc.evaluate("1"));
  }
}
