package uk.ac.rhul.cs2800;

/**
 * An Enum used to define the different possible symbols that can be recognised by the calculator
 * stack.
 *
 * @author Danyal Shah
 */
public enum Symbol {
  LEFT_BRACKET("("), RIGHT_BRACKET(")"), TIME("*"), DIVIDE("/"), PLUS("+"), MINUS("-"), INVALID(
      "!");

  private String symbol;

  private Symbol(String symbol) {
    this.symbol = symbol;
  }

  public String toString() {
    return symbol;
  }
}


