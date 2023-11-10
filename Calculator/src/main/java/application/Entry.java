package uk.ac.rhul.cs2800;

import java.util.Objects;

/**
 * A class used to push different types of entries to the same stack.
 *
 * @author Danyal Shah
 */
public class Entry {


  public static final String VALUE_ERR = "Not an Entry of Type Number!";
  public static final String SYMBOL_ERR = "Not an Entry of Type Symbol!";
  public static final String STRING_ERR = "Not an Entry of Type String!";
  public static final String COMPARE_ERR = "Cannot compare entries of different types!";

  private float number;
  private Symbol symbol;
  private String str;
  private Type type;


  public Entry(float number) {
    this.number = number;
    this.type = Type.NUMBER;
  }


  public Entry(Symbol symbol) {
    this.symbol = symbol;
    this.type = Type.SYMBOL;
  }


  public Entry(String str) {
    this.str = str;
    this.type = Type.STRING;
  }

  /**
   * Checks the type of the other, and throws an exception if they don't match.
   * 
   * @param type The type of the other Entry object.
   * @param error The error messages depending on the current Entry type.
   * @throws BadTypeException When the types don't match.
   */
  // Written to prevent Code Duplication.
  private void checkType(Type type, String error) throws BadTypeException {
    if (this.type != type) {
      throw new BadTypeException(error);
    }
  }

  /**
   * Gets the value of the Entry, provided that it's of type Number. If not, it will throw a
   * BadTypeException.
   * 
   * @return Numeric Value of Entry instance.
   * @throws BadTypeException When Type is not Number.
   */
  public float getValue() throws BadTypeException {
    checkType(Type.NUMBER, VALUE_ERR);
    return number;
  }

  /**
   * Gets the symbol of the Entry, provided that it's of type Symbol. If not, it will throw a
   * BadTypeException.
   * 
   * @return Symbolic Value of Entry instance.
   * @throws BadTypeException When Type is not Symbol.
   */
  public Symbol getSymbol() throws BadTypeException {
    checkType(Type.SYMBOL, SYMBOL_ERR);
    return symbol;
  }

  /**
   * Gets the string value of the Entry, provided that it's of type String. If not, it will throw a
   * BadTypeException.
   * 
   * @return String Value of Entry instance.
   * @throws BadTypeException When Type is not String.
   */
  public String getString() throws BadTypeException {
    checkType(Type.STRING, STRING_ERR);
    return str;
  }


  public Type getType() {
    return type;
  }


  @Override
  public int hashCode() {
    return Objects.hash(number, symbol, str, type);
  }


  @Override
  public boolean equals(Object obj) {
    // Checks if the object is being compared to itself
    if (this == obj) {
      return true;
    }
    // Checks if the object is initialised.
    if (obj == null) {
      return false;
    }
    // Checks if the argument object is not an Entry object.
    if (getClass() != obj.getClass()) {
      return false;
    }
    Entry other = (Entry) obj;
    // Checks if the argument Entry has the same type as the current instance.
    if (type != other.getType()) {
      return false;
    }
    // We can expect two of these to be null as they are not used in certain Entry objects, so it
    // relies on the other value instead.
    return Float.floatToIntBits(number) == Float.floatToIntBits(other.number)
        && this.symbol == other.symbol && Objects.equals(str, other.str);
  }


  @Override
  public String toString() {
    // Output Format, the value and type will be substituted in based on the type.
    String output = "Entry Field\nType: %s \nValue: %s\n";
    
    switch (type) {
      case NUMBER:
        return String.format(output, type.toString(), Float.toString(number));

      case SYMBOL:
        return String.format(output, type.toString(), symbol.toString());
      // STRING is the only valid option remaining, so will be executed here.
      default:
        return String.format(output, type.toString(), str);

    }
  }

}
