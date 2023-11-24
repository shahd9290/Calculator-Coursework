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
  @Test
  void testAdditionOne() throws InvalidExpression {
    assertEquals(8, sc.evaluate("5 + 3"));
    assertEquals(6, sc.evaluate("3 + 3"));
  }
}
