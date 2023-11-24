package application;

import java.util.Scanner;

/**
 * This class will be used to evaluate a calculation using the Standard Infix Notation. Each
 * operator will be in between the two operands, e.g. 1 + 5 -> 6.
 * 
 * @author Danyal Shah
 */
public class StandardCalc {

  RevPolishCalc rpc = new RevPolishCalc();
  OpStack opStack = new OpStack();

  /**
   * Performs a calculation on a provided string using the Standard Infix method.
   * 
   * @param string The expression to be evaluated.
   * @return A calculated value based on the expression provided.
   * @throws InvalidExpression When an expression is entered incorrectly and cannot be performed.
   */

  public float evaluate(String string) throws InvalidExpression {
    String expression = "";
    try (Scanner scan = new Scanner(string)) {
      while (scan.hasNext()) {
        String arg = scan.next();
        if (arg.equals("+")) {
          opStack.push(Symbol.PLUS);
        } else {
          expression = expression + arg + " ";
        }
      }
      while (opStack.size() != 0) {
        expression = expression + opStack.pop();
      }
    } catch (EmptyStackException e) {
      // TODO Auto-generated catch block
      return 0;
    }
    return rpc.evaluate(expression);
  }

}
