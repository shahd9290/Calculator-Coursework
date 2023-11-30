package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestInFix {

  StandardCalc sc;

  @BeforeEach
  void createSC() {
    sc = new StandardCalc();
  }

  // Test 1 - Create Class
  @Test
  void testExists() {
    assertNotNull(new StandardCalc());
  }

  // Test 2 - Test Basic Addition
  // Fixed by implementing basic Shunting Yard algorithm
  @Test
  void testArithmeticOne() throws InvalidExpression {
    assertEquals(8, sc.evaluate("5 + 3"));
    assertEquals(6, sc.evaluate("3 + 3"));
  }

  // Test 3 - Test Other Operators
  // Fixed by implementing Switch/Case block.
  @Test
  void testArithmeticTwo() throws InvalidExpression {
    assertEquals(10, sc.evaluate("12 - 2"));
    assertEquals(10, sc.evaluate("5 * 2"));
    assertEquals(10, sc.evaluate("20 / 2"));
  }

  // Test 4 - Test Precedence (* > / > + > -)
  // Fixed by checking priority via enum ordinals & popping accordingly.
  @Test
  void testPrecedence() throws InvalidExpression {
    assertEquals(10, sc.evaluate("4 * 2 + 2"));
    assertEquals(8, sc.evaluate("4 + 2 * 2"));
    assertEquals(5, sc.evaluate("18 * 2 / 4 + 1 - 5"));
    assertEquals(17, sc.evaluate("18 + 2 - 15 / 1 * 5"));

  }

  // Test 5 - Test Brackets
  // Fixed by added pushing for left bracket, and popping until last item popped is left bracket
  // when the right bracket is read.
  @Test
  void testBrackets() throws InvalidExpression {
    assertEquals(13, sc.evaluate("3 + ( 5 * 2 )"));
    assertEquals(63, sc.evaluate("( 5 * ( 6 + 7 ) ) - 2"));
    assertEquals(60, sc.evaluate("( 2 + 4 ) * ( 3 + 7 )"));
    assertEquals(70, sc.evaluate("( 2 + ( 25 / 5 ) ) * ( 3 + ( 3 + 4 ) )"));

  }
  
  // Test 6 - Test Invalids
  @Test
  void testInvalids() throws InvalidExpression {
    InvalidExpression except = assertThrows(InvalidExpression.class, () -> sc.evaluate("5 + + 2"));
    assertEquals(except.getMessage(), RevPolishCalc.INVALID_MSG);
    
    InvalidExpression except2 = assertThrows(InvalidExpression.class, () -> sc.evaluate("1 / 0"));
    assertEquals(except2.getMessage(), RevPolishCalc.DIVZERO_MSG);
    
    InvalidExpression except3 = assertThrows(InvalidExpression.class, () -> sc.evaluate("5 2"));
    assertEquals(except3.getMessage(), RevPolishCalc.INVALID_MSG);
  }
  
  // Test 7 - Test Single Numbers
  @Test
  void testSingle() throws InvalidExpression {
    assertEquals(1, sc.evaluate("1"));
  }
  
  // Test 8 - Test Rev Polish Input
  @Test
  void testRevPolishInput() throws InvalidExpression{
    InvalidExpression except;
    except = assertThrows(InvalidExpression.class, () -> sc.evaluate("1 2 +"));
    assertEquals(except.getMessage(), StandardCalc.INVALID_MSG);
    
    except = assertThrows(InvalidExpression.class, () -> sc.evaluate("1 2 -"));
    assertEquals(except.getMessage(), StandardCalc.INVALID_MSG);
    
    except = assertThrows(InvalidExpression.class, () -> sc.evaluate("1 2 *"));
    assertEquals(except.getMessage(), StandardCalc.INVALID_MSG);
    
    except = assertThrows(InvalidExpression.class, () -> sc.evaluate("1 2 /"));
    assertEquals(except.getMessage(), StandardCalc.INVALID_MSG);
  }
}
