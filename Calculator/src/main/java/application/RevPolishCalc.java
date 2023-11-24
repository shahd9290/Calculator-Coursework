package application;

import java.util.Scanner;

/**
 * This class will be used to evaluate a calculation using the Reverse Polish Notation (PostFix).
 * Each Operator will affect any two preceding Operands, e.g. 1 5 + -> 6.
 * 
 * @author Danyal Shah
 */
public class RevPolishCalc {

  NumStack numStack = new NumStack();

  public float evaluate(String string) throws EmptyStackException, InvalidExpression {
    Scanner scan = new Scanner(string);
    while (scan.hasNext()) {
      if (scan.hasNextInt()) {
        numStack.push(scan.nextInt());
      } else {
        float arg2 = numStack.pop();
        float arg1 = numStack.pop();

        switch (scan.next()) {
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
            if (arg2 == 0) {
              throw new InvalidExpression("Cannot Divide By 0!");
            }
            numStack.push(arg1 / arg2);
            break;
        }
      }
    }
    return numStack.pop();
  }

}
