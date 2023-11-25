package application;

/**
 * An Enum used to define the different possible symbols that can be recognised by the calculator
 * stack.
 *
 * @author Danyal Shah
 */
public enum Symbol {
  TIME("*"), DIVIDE("/"), PLUS("+"), MINUS("-"), LEFT_BRACKET("("), RIGHT_BRACKET(")"), INVALID(
      "!");

  private String symbol;

  private Symbol(String symbol) {
    this.symbol = symbol;
  }

  public String toString() {
    return symbol;
  }
}


