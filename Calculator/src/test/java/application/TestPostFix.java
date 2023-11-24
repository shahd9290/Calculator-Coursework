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
  void testAdditionOne() {
    assertEquals(3, rpc.evaluate("1 2 +"));
  }
  
  // Test 3 - Check the correct value is returned for different expression
  // Fixed by using a loop to read each integer in a string, and end when it hits the +.
  @Test
  void testAdditionTwo() {
    assertEquals(5, rpc.evaluate("2 3 +"));
  }
}
