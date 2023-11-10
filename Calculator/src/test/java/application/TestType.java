package application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestType {

  @Test
  void test() {
    assertEquals("String", Type.STRING.toString());
    assertEquals("Number", Type.NUMBER.toString());
    assertEquals("Symbol", Type.SYMBOL.toString());
    assertEquals("Invalid", Type.INVALID.toString());
  }

}
