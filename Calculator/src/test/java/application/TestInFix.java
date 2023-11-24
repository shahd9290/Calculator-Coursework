package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
  void testArithmeticTwo() throws InvalidExpression {
    assertEquals(10, sc.evaluate("12 - 2"));
    assertEquals(10, sc.evaluate("5 * 2"));
    assertEquals(10, sc.evaluate("20 / 2"));

  }
}
