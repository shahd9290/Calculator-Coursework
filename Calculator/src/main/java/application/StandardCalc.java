package application;

import java.util.Scanner;

/**
 * This class will be used to evaluate a calculation using the Standard Infix Notation. Each
 * operator will be in between the two operands, e.g. 1 + 5 -> 6.
 * 
 * @author Danyal Shah
 */
public class StandardCalc {

  private RevPolishCalc rpc = new RevPolishCalc();
  private OpStack opStack = new OpStack();
  private String output;

  /**
   * Performs a calculation on a provided string using the Standard Infix method.
   * 
   * @param string The expression to be evaluated.
   * @return A calculated value based on the expression provided.
   * @throws InvalidExpression When an expression is entered incorrectly and cannot be performed.
   */

  public float evaluate(String string) throws InvalidExpression {
    checkValid(string);
    output = "";
    try (Scanner scan = new Scanner(string)) {
      // Read each character in the string.
      while (scan.hasNext()) {
        String arg = scan.next();
        // If an operator: push symbol to stack.
        // Operators ordered by priority: * -> / -> + -> -
        switch (arg) {
          case "*":
            isPriority(Symbol.TIME);
            opStack.push(Symbol.TIME);
            break;

          case "/":
            isPriority(Symbol.DIVIDE);
            opStack.push(Symbol.DIVIDE);
            break;

          case "+":
            isPriority(Symbol.PLUS);
            opStack.push(Symbol.PLUS);
            break;

          case "-": // Least priority so don't need to check.
            opStack.push(Symbol.MINUS);
            break;

          case "(":
            opStack.push(Symbol.LEFT_BRACKET);
            break;

          case ")":
            Symbol sym;
            // Pop operators until the symbol popped is a left bracket.
            while ((sym = opStack.pop()) != Symbol.LEFT_BRACKET) {
              addToOutput(sym);
            }
            break;
          default:
            addToOutput(arg);
        }
      }
      // Pop all operators from the opStack
      if (opStack.size() == 0 && string.contains(" ")) {
        throw new EmptyStackException(RevPolishCalc.INVALID_MSG);
      }
      while (opStack.size() != 0) {
        addToOutput(opStack.pop());
      }
      return rpc.evaluate(output);

    } catch (EmptyStackException e) {
      throw new InvalidExpression(RevPolishCalc.INVALID_MSG);
    }
  }

  /**
   * Used to identify the whether the current symbol is a priority based on its ordinal value.
   * 
   * @param sym The symbol to compare with what's at the top of the stack.
   * @throws EmptyStackException If the stack is empty.
   */
  private void isPriority(Symbol sym) throws EmptyStackException {
    if (opStack.size() > 0 && sym.ordinal() > opStack.top().ordinal()) {
      addToOutput(opStack.pop());
    }
  }

  private void addToOutput(String added) {
    output = output + added + " ";
  }

  private void addToOutput(Symbol added) {
    addToOutput(added.toString());
  }

  /**
   * Checks that the expression provided doesn't end with an operator. If it did, it is most
   * possibly a reverse polish input and so throws an error.
   * 
   * @param exp The expression that is to be checked.
   * @throws InvalidExpression If the expression is determined to be in Reverse Polish Notation.
   */
  private void checkValid(String exp) throws InvalidExpression {
    String[] symbols = {"+", "-", "*", "/"};
    for (String sym : symbols) {
      if (exp.endsWith(sym)) {
        throw new InvalidExpression(RevPolishCalc.INVALID_MSG);
      }
    }
  }

}
