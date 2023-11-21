package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestTypeStack {

  OpStack op;
  StrStack str;
  NumStack num;
  
  // Test 1 - Ensure the classes exist.
  @Test
  void testCreate() {
    assertNotNull(new OpStack());
    assertNotNull(new StrStack());
    assertNotNull(new NumStack());
  }


}
