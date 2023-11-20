package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEntry {
  Entry entFloat;
  Entry entSymbol;
  Entry entString;

  @BeforeEach
  void createEntries() {
    entFloat = new Entry(14.0f);
    entSymbol = new Entry(Symbol.DIVIDE);
    entString = new Entry("Test");
  }

  // Test 1 - Testing Entry Constructors
  // Test 2 - Testing Hard Coded Values for Getters.
  @Test
  void testEntry() throws BadTypeException {
    assertEquals(14.0, entFloat.getValue(),
        "Should return the value that the Entry was instantiated with");
    assertEquals(Symbol.DIVIDE, entSymbol.getSymbol(),
        "Should return the value that the Entry was instantiated with");
    assertEquals("Test", entString.getString(),
        "Should return the value that the Entry was instantiated with ");

    assertEquals(Type.NUMBER, entFloat.getType(),
        "Should return the NUMBER type as instantiated with 14.0");
    assertEquals(Type.SYMBOL, entSymbol.getType(),
        "Should return the SYMBOL type as instantiated with the DIVIDE symbol");
    assertEquals(Type.STRING, entString.getType(),
        "Should return the STRING type as instantiated with the String \"Test\"");
  }

  static float randDouble;
  static Symbol randSymbol;
  static String randString;

  // Created to override the @BeforeEach Entry objects with new, randomly generated values.
  void createRandomEntries() {
    Random rand = new Random();

    Symbol[] symbols = {Symbol.LEFT_BRACKET, Symbol.RIGHT_BRACKET, Symbol.TIME, Symbol.DIVIDE,
        Symbol.PLUS, Symbol.MINUS, Symbol.INVALID};

    String[] strings = {"Test", "Test1", "Test2", "Test3"};

    randDouble = rand.nextFloat();
    randSymbol = symbols[rand.nextInt(symbols.length)];
    randString = strings[rand.nextInt(strings.length)];

    entFloat = new Entry((float) randDouble);
    entSymbol = new Entry(randSymbol);
    entString = new Entry(randString);
  }


  // Test 3 - Testing Getters where Entries are random, to ensure the correct value is retrieved.
  @Test
  void testEntryGettersRandom() throws BadTypeException {
    createRandomEntries();
    assertEquals(randDouble, entFloat.getValue(),
        "Checking to make sure that the random value generated is returned");
    assertEquals(randSymbol, entSymbol.getSymbol(),
        "Checking to make sure that the random value generated is returned");
    assertEquals(randString, entString.getString(),
        "Checking to make sure that the random value generated is returned");
  }

  // Test 4 - Testing that Getters return a BadTypeException when trying to call a getter for the
  // wrong Type.
  @Test
  void testBadTypeException() {
    BadTypeException except1 = assertThrows(BadTypeException.class, () -> entFloat.getString(),
        "Should be thrown when using the wrong getter");
    BadTypeException except2 = assertThrows(BadTypeException.class, () -> entString.getSymbol(),
        "Should be thrown when using the wrong getter");
    BadTypeException except3 = assertThrows(BadTypeException.class, () -> entSymbol.getValue(),
        "Should be thrown when using the wrong getter");

    assertEquals(Entry.STRING_ERR, except1.getMessage(),
        "Making sure the correct exception message is displayed when caught");
    assertEquals(Entry.SYMBOL_ERR, except2.getMessage(),
        "Making sure the correct exception message is displayed when caught");
    assertEquals(Entry.VALUE_ERR, except3.getMessage(),
        "Making sure the correct exception message is displayed when caught");
  }

  // Test 5 - Check to see if two entries are the same.
  @Test
  void testEqualEntries() throws BadTypeException {
    // Entries with duplicate values
    Entry testEntFloat = new Entry(14.0f);
    Entry testEntSymbol = new Entry(Symbol.DIVIDE);
    Entry testEntString = new Entry("Test");

    assertTrue(testEntFloat.equals(entFloat),
        "Making sure that two different entries with the same value are equal "
            + "when compared against each other");
    assertTrue(testEntSymbol.equals(entSymbol),
        "Making sure that two different entries with the same value are equal "
            + "when compared against each other");
    assertTrue(testEntString.equals(entString),
        "Making sure that two different entries with the same value are equal "
            + "when compared against each other");
  }

  // Test 6 - Check that the correct output is given when two entries are not the same.
  @Test
  void testNotEqualEntries() throws BadTypeException {
    Entry testEntFloat = new Entry(16.0f);
    Entry testEntSymbol = new Entry(Symbol.LEFT_BRACKET);
    Entry testEntString = new Entry("Testing");

    assertFalse(testEntFloat.equals(entFloat),
        "Making sure that two entries with different values are not equal"
            + " when compared against each other");
    assertFalse(testEntSymbol.equals(entSymbol),
        "Making sure that two entries with different values are not equal"
            + " when compared against each other");
    assertFalse(testEntString.equals(entString),
        "Making sure that two entries with different values are not equal"
            + " when compared against each other");
  }

  // Test 7 - Compare Entry objects with themselves, and with different Entry types.
  @SuppressWarnings("unlikely-arg-type")
  @Test
  void testOtherEntries() {
    Entry test = null;

    assertTrue(entSymbol.equals(entSymbol),
        "Testing that an entry is equal when compared to itself.");
    assertFalse(entString.equals(test),
        "Testing that an entry is not equal when compared to a null Entry");
    assertFalse(entFloat.equals("String"),
        "Testing that an entry is not equal if compared with an instance of another class");
    assertFalse(entSymbol.equals(entFloat),
        "Testing that an entry is not equal to that of a different type.");
  }

  // Test 8 - Check if the hashcodes match if two Entry objects are equal.
  @Test
  void testHashcode() {
    Entry testEntFloat = new Entry(14.0f);

    assertTrue(entFloat.equals(testEntFloat), "Testing that the two entries are the same in value");
    assertEquals(entFloat.hashCode(), testEntFloat.hashCode(),
        "Testing that both entries (given they are the same) have the same hashcode.");

  }

  // Test 9 - Checking the outputs of the toString() so that the Entry class is in a readable state.
  @Test
  void testToString() {
    String template = "Entry Field\nType: %s \nValue: %s\n";

    assertEquals(String.format(template, "Number", "14.0"), entFloat.toString());
    assertEquals(String.format(template, "Symbol", "/"), entSymbol.toString());
    assertEquals(String.format(template, "String", "Test"), entString.toString());
  }
}
