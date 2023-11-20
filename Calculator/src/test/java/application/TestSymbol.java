package application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestSymbol {

  @Test
  void test() {
    assertEquals("(", Symbol.LEFT_BRACKET.toString());
    assertEquals(")", Symbol.RIGHT_BRACKET.toString());
    assertEquals("*", Symbol.TIME.toString());
    assertEquals("/", Symbol.DIVIDE.toString());
    assertEquals("+", Symbol.PLUS.toString());
    assertEquals("-", Symbol.MINUS.toString());
    assertEquals("!", Symbol.INVALID.toString());
  }

}
