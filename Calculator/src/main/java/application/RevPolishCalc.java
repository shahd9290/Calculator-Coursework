package application;

import java.util.Scanner;

/**
 * This class will be used to evaluate a calculation using the Reverse Polish Notation (PostFix).
 * Each Operator will affect any two preceding Operands, e.g. 1 5 + -> 6.
 * 
 * @author Danyal Shah
 */
public class RevPolishCalc {

  public static final String DIVZERO_MSG = "Cannot Divide By Zero!";
  public static final String INVALID_MSG =
      "Invalid Expression! Perhaps in the wrong format or unbalanced?";
  public static final String OVERFLOW_MSG = "Answer is too big!";

  private NumStack numStack = new NumStack();

  /**
   * Performs a calculation on a provided string using the Reverse Polish Notation.
   * 
   * @param string The expression to be evaluated.
   * @return A calculated value based on the expression provided.
   * @throws InvalidExpression When an expression is entered incorrectly and cannot be performed.
   */
  public float evaluate(String string) throws InvalidExpression {
    try (Scanner scan = new Scanner(string);) {
      while (scan.hasNext()) {
        if (scan.hasNextFloat()) {
          float num = scan.nextFloat();

          // Detect if number is too large for calculation.
          if (num >= Float.MAX_VALUE || num < Float.MAX_VALUE * -1) {
            throw new InvalidExpression(OVERFLOW_MSG);
          }
          numStack.push(num);
        } else {
          // Pop previous two numbers for the next operator.
          float arg2 = numStack.pop();
          float arg1 = numStack.pop();

          calculate(scan.next(), arg1, arg2);
        }
      }
      if (numStack.size() > 1) {
        // If there are multiple items in numStack, it's likely to be an invalid expression.
        throw new InvalidExpression(INVALID_MSG);
      }
      return numStack.pop();
    } catch (EmptyStackException empty) {
      // For a stack to be empty it means that there was an error in the input string.
      throw new InvalidExpression(INVALID_MSG);
    }
  }

  private void calculate(String op, float arg1, float arg2) throws InvalidExpression {
    switch (op) {
      case "+":
        numStack.push(arg1 + arg2);
        break;
      case "-":
        numStack.push(arg1 - arg2);
        break;
      case "*":
        numStack.push(arg1 * arg2);
        break;
      case "/":
        // Attempting to divide by zero.
        if (arg2 == 0) {
          throw new InvalidExpression(DIVZERO_MSG);
        }
        numStack.push(arg1 / arg2);
        break;
      default:
        // This will only occur when neither of the four aforementioned symbols are used as an
        // operator.
        // This will throw a new InvalidExpression.
        throw new InvalidExpression(INVALID_MSG);
    }
  }

}
