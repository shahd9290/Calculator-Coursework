package uk.ac.rhul.cs2800;

/**
 * An Enum used to hold different Entry types which can be used in the calculator stack.
 *
 * @author Danyal Shah
 */
public enum Type {
  STRING("String"), NUMBER("Number"), SYMBOL("Symbol"), INVALID("Invalid");

  private String type;

  private Type(String message) {
    type = message;
  }

  public String toString() {
    return type;
  }
}
