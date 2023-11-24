package application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestInFix {

  RevPolishCalc sc;

  @BeforeEach
  void createSC() {
    sc = new RevPolishCalc();
  }

  // Test 1 - Create Class
  @Test
  void testExists() {
    assertNotNull(new StandardCalc());
  }

}
